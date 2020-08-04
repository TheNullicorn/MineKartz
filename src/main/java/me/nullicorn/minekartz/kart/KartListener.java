package me.nullicorn.minekartz.kart;

import com.comphenix.protocol.PacketType.Play.Client;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import me.nullicorn.minigame.event.roster.PlayerRemovedFromRosterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Event listeners relating to Karts and their owners
 *
 * @author Nullicorn
 */
public class KartListener implements Listener {

    private final KartManager kartManager;

    /**
     * @see SteeringListener
     */
    private final PacketListener steeringListener;

    public KartListener(KartManager kartManager) {
        this.kartManager = kartManager;
        steeringListener = new SteeringListener(kartManager);
        ProtocolLibrary.getProtocolManager().addPacketListener(steeringListener);
    }

    /**
     * Block players from dismounting their karts
     */
    @EventHandler
    private void onDismount(EntityDismountEvent event) {
        event.setCancelled(Kart.isEntityKart(event.getDismounted()));
    }

    /**
     * Block players from adding/removing items from a kart's armor stand
     */
    @EventHandler
    private void onPlayerInteractWithArmorStand(PlayerInteractAtEntityEvent event) {
        if (Kart.isEntityKart(event.getRightClicked())) {
            event.setCancelled(true);
        }
    }

    /**
     * Delete a player's kart when they are removed from the roster
     */
    @EventHandler
    private void onPlayerQuit(PlayerRemovedFromRosterEvent event) {
        kartManager.deleteKartFor(event.getPlayer().getUniqueId());
    }

    /**
     * Deregister this instance's listeners
     */
    public void deregister() {
        HandlerList.unregisterAll(this);
        ProtocolLibrary.getProtocolManager().removePacketListener(steeringListener);
    }

    /**
     * Packet listener responsible for reading a player's keyboard inputs whilst they are riding a kart
     */
    private class SteeringListener extends PacketAdapter {

        public SteeringListener(KartManager kartManager) {
            super(kartManager.game, Client.STEER_VEHICLE);
        }

        @Override
        public void onPacketReceiving(PacketEvent event) {
            if (Kart.isEntityKart(event.getPlayer().getVehicle())) {
                float sideways = event.getPacket().getFloat().read(0);
                float forward = event.getPacket().getFloat().read(1);
                // Move the player's kart accordingly
                kartManager
                    .getKartFor(event.getPlayer().getUniqueId())
                    .processInputs(forward, sideways);
            }
        }
    }
}
