package me.nullicorn.minigame;

import lombok.NonNull;
import me.nullicorn.minigame.command.RosterCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A game/minigame plugin
 *
 * @author Nullicorn
 */
public abstract class Game<P extends GamePlayer> extends JavaPlugin implements Listener {

    /**
     * @return The game's player roster
     */
    public abstract Roster<P> getRoster();

    /**
     * Called when the game is ready to start
     */
    public abstract void onStart();

    /**
     * Send a chat message to all players on this game's roster
     */
    public void broadcast(@NonNull String message) {
        getRoster().players.values().forEach(player -> player.sendMessage(message));
    }

    @Override
    public void onEnable() {
        onStart();

        // If the plugin was reloaded (and players are already online) load existing players into the roster
        Bukkit.getOnlinePlayers().forEach(getRoster()::addPlayer);

        // Register command executors
        new RosterCommand<>(getRoster()).register(this);

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(this, this);
    }
}
