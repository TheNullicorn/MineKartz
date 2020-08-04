package me.nullicorn.minigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import me.nullicorn.minigame.event.roster.PlayerAddedToRosterEvent;
import me.nullicorn.minigame.event.roster.PlayerRemovedFromRosterEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Stores a list of active players participating in a {@link Game}
 * <p>
 * This list may differ from {@link Bukkit#getOnlinePlayers()} when you have staff or other players joining the game to spectate but not to play
 *
 * @author Nullicorn
 */
public abstract class Roster<P extends Player> {

    @Getter
    private final   Game<? extends Player> game;
    protected final Map<UUID, P>           players;

    protected Roster(Game<? extends Player> game) {
        this.game = game;
        players = new HashMap<>();
    }

    /**
     * This may return false for vanished players, spectators, and other non-participants in a game
     *
     * @return Whether or not the player should be added to the roster
     */
    protected abstract boolean shouldPlayerBeAdded(P player);

    /**
     * Try to add a player to this roster using their raw player object
     *
     * @return Whether or not the player was able to be added
     */
    public abstract boolean addPlayer(Player player);

    /**
     * Try to add a player to this roster
     *
     * @param player Player to add to the roster
     * @return Whether or not the player was able to be added
     */
    public boolean add(@NonNull P player) {
        if (shouldPlayerBeAdded(player)) {
            PlayerAddedToRosterEvent event = new PlayerAddedToRosterEvent(this, player);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                players.put(player.getUniqueId(), player);
                Bukkit.getPluginManager().callEvent(new PlayerAddedToRosterEvent.Post(this, player));
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a player from the roster
     *
     * @param player Player to be removed
     */
    public void remove(@NonNull Player player) {
        players.remove(player.getUniqueId());
        Bukkit.getPluginManager().callEvent(new PlayerRemovedFromRosterEvent(this, player));
    }

    /**
     * @return Player on the roster with the matching UUID, or null if none was found
     */
    @Nullable
    public P get(@NonNull Player player) {
        return get(player.getUniqueId());
    }

    /**
     * @return Player on the roster with the UUID, or null if none was found
     */
    @Nullable
    public P get(@NonNull UUID playerUuid) {
        return players.get(playerUuid);
    }

    /**
     * @return Whether or not the roster has that player on it
     */
    public boolean has(@NonNull Player player) {
        return has(player.getUniqueId());
    }

    /**
     * @return Whether or not the roster has a player with that UUID on it
     */
    public boolean has(@NonNull UUID playerUuid) {
        return players.containsKey(playerUuid);
    }

    /**
     * @return The number of players on the roster
     */
    public int size() {
        return players.size();
    }

    /**
     * @return A list of players stored on this roster
     */
    public List<P> asList() {
        return new ArrayList<>(players.values());
    }
}
