package me.nullicorn.minigame.state;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import me.nullicorn.minigame.Game;
import net.minikloon.fsmgasm.State;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

/**
 * A modified version of GameState.java from the FSMgasm <a href=https://www.spigotmc.org/threads/235786>spigot thread</a>
 *
 * @author Nullicorn
 */
public abstract class GameState extends State implements Listener {

    protected final Game<?> game;

    protected final Set<Listener>   listeners = new HashSet<>();
    protected final Set<BukkitTask> tasks     = new HashSet<>();

    public GameState(Game<?> game) {
        this.game = game;
    }

    /**
     * @return The name of this state
     */
    public abstract String getName();

    @Override
    public final void start() {
        super.start();
        registerListeners(this);
        game.getLogger().info("Started state: " + getName());
    }

    @Override
    public final void end() {
        super.end();
        if (!super.getEnded()) {
            return;
        }
        listeners.forEach(HandlerList::unregisterAll);
        tasks.forEach(BukkitTask::cancel);
        listeners.clear();
        tasks.clear();
        game.getLogger().info("Ended state: " + getName());
    }

    /**
     * @return A collection of players recognized by this state
     */
    protected Collection<? extends Player> getPlayers() {
        return game.getRoster().asList();
    }

    /**
     * Send a message to all players recognized by this state (returned from {@link #getPlayers()})
     */
    protected void broadcast(String message) {
        getPlayers().forEach(p -> p.sendMessage(message));
    }

    /**
     * Register listeners used for the duration of this state. These will deregister when the state ends
     */
    protected void registerListeners(Listener listener) {
        listeners.add(listener);
        game.getServer().getPluginManager().registerEvents(listener, game);
    }

    /**
     * Schedule a task to be run during this state. If the state ends before the task, the task will be cancelled
     */
    protected void schedule(Runnable runnable, long delay) {
        BukkitTask task = game.getServer().getScheduler().runTaskLater(game, runnable, delay);
        tasks.add(task);
    }

    /**
     * Schedule a repeating task to be run during this state. When the state ends, the task will stop repeating
     */
    protected void scheduleRepeating(Runnable runnable, long delay, long interval) {
        BukkitTask task = game.getServer().getScheduler()
            .runTaskTimer(game, runnable, delay, interval);
        tasks.add(task);
    }
}
