package me.nullicorn.minekartz.kart;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.NonNull;
import me.nullicorn.minekartz.MineKartz;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * @author Nullicorn
 */
public class KartManager implements Listener {

    protected final MineKartz game;

    /**
     * The karts managed by this instance (mapped to their owners' UUIDs)
     */
    private final Map<UUID, Kart> karts;

    /**
     * Contains the listeners that let the players control their karts
     */
    private final KartListener controllerListener;

    /**
     * ID of the bukkit scheduler task that updates karts' positions
     */
    private final Integer kartTickTaskId;

    public KartManager(MineKartz game) {
        this.game = game;
        this.karts = new HashMap<>();
        this.controllerListener = new KartListener(this);

        // Register listeners
        Bukkit.getPluginManager().registerEvents(this, game);
        Bukkit.getPluginManager().registerEvents(controllerListener, game);

        kartTickTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(game, new KartTickTask(), 0, 1);
    }

    /**
     * Deregister any tasks and listeners created by this instance. This will cause any karts managed by this instance to stop functioning.
     */
    public void deregister() {
        deleteAllKarts();
        Bukkit.getScheduler().cancelTask(kartTickTaskId);
        HandlerList.unregisterAll(this);
        controllerListener.deregister();
    }

    /**
     * Get the kart owned by this player. If none exists, a new one is created (but not spawned; see {@link #spawnKartFor(UUID, Location)})
     *
     * @return The player's kart
     */
    public Kart getKartFor(@NotNull UUID playerUuid) {
        return karts.computeIfAbsent(
            playerUuid,
            uuid -> new Kart(this, uuid, game.getConfig().getConfigurationSection("physics"))
        );
    }

    /**
     * Spawn the player's kart and put them in the driver seat. If the player does not already have a kart, one will be created for them.
     */
    public void spawnKartFor(@NonNull UUID playerUuid, @NotNull Location location) {
        Kart kart = getKartFor(playerUuid);
        kart.spawn(location);
        kart.pickupDriver();
    }

    /**
     * Despawn all karts recognized by this manager. These can still be respawned later
     */
    public void despawnAllKarts() {
        karts.keySet().forEach(this::despawnKartFor);
    }

    /**
     * Despawn the player's kart. It can be respawned later via {@link #spawnKartFor(UUID, Location)}
     */
    public void despawnKartFor(@NonNull UUID playerUuid) {
        Kart kart = karts.get(playerUuid);
        if (kart != null) {
            kart.removeDriver();
            kart.despawn();
        }
    }

    /**
     * Delete all karts stored in this manager. Any karts that are currently spawned in wil lbe despawned.
     */
    public void deleteAllKarts() {
        karts.keySet().forEach(this::deleteKartFor);
    }

    /**
     * Delete the player's kart from this manager. If the kart is currently spawned in, it will be despawned.
     */
    public void deleteKartFor(UUID playerUuid) {
        despawnKartFor(playerUuid);
        karts.remove(playerUuid);
    }

    /**
     * Runnable task responsible for updating each (spawned) kart's state
     */
    private final class KartTickTask implements Runnable {

        @Override
        public void run() {
            karts.forEach((uuid, kart) -> {
                // Ignore despawned karts
                if (!kart.isSpawned()) {
                    return;
                }
                kart.tick();
            });
        }
    }
}
