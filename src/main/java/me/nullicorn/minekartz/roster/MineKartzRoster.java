package me.nullicorn.minekartz.roster;

import me.nullicorn.minekartz.MineKartz;
import me.nullicorn.minigame.Roster;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Stores the current list of players playing this game of MineKartz
 *
 * @author Nullicorn
 */
public class MineKartzRoster extends Roster<MineKartzPlayer> implements Listener {

    public MineKartzRoster(MineKartz game) {
        super(game);
        Bukkit.getPluginManager().registerEvents(this, game);
    }

    @Override
    protected boolean shouldPlayerBeAdded(MineKartzPlayer player) {
        return true;
    }

    @Override
    public boolean addPlayer(Player player) {
        return add(new MineKartzPlayer(player));
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        add(new MineKartzPlayer(event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerQuit(PlayerQuitEvent event) {
        remove(event.getPlayer());
    }
}
