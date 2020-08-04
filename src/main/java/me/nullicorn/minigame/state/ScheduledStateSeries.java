package me.nullicorn.minigame.state;

import java.util.LinkedList;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * A modified version of ScheduledStateSeries.java from the FSMgasm <a href=https://www.spigotmc.org/threads/235786>spigot thread</a>
 *
 * @author Nullicorn
 */
public class ScheduledStateSeries extends StateSeries {

    private final Plugin     plugin;
    private final long       interval;
    protected     BukkitTask scheduledTask;

    protected List<Runnable> onUpdate = new LinkedList<>();

    public ScheduledStateSeries(Plugin plugin) {
        this(plugin, 1);
    }

    public ScheduledStateSeries(Plugin plugin, long interval) {
        this.plugin = plugin;
        this.interval = interval;
    }

    @Override
    public final void onStart() {
        super.onStart();
        scheduledTask = plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            update();
            onUpdate.forEach(Runnable::run);
        }, 0L, interval);
    }

    @Override
    public final void onEnd() {
        super.onEnd();
        scheduledTask.cancel();
    }

    public final void addOnUpdate(Runnable runnable) {
        onUpdate.add(runnable);
    }
}
