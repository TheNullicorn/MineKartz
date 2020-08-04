package me.nullicorn.minekartz.state;

import me.nullicorn.minekartz.MineKartz;
import me.nullicorn.minekartz.config.PregameStateConfig;
import me.nullicorn.minigame.event.roster.PlayerAddedToRosterEvent;
import me.nullicorn.minigame.event.roster.PlayerRemovedFromRosterEvent;
import me.nullicorn.minigame.state.GameStateAdapter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

/**
 * Game state where players are waiting in the pregame lobby for enough players to join
 *
 * @author Nullicorn
 */
public class PregameState extends GameStateAdapter {

    private final PregameStateConfig config;

    public PregameState(MineKartz game, ConfigurationSection config) {
        super(game);
        this.config = new PregameStateConfig(config);
    }

    @Override
    public String getName() {
        return "PregameState";
    }

    @Override
    public boolean isReadyToEnd() {
        return getPlayers().size() >= config.getMinPlayers();
    }

    @EventHandler
    private void onPlayerJoin(PlayerAddedToRosterEvent.Post event) {
        game.broadcast(String.format(config.getJoinMessageFormat(),
            event.getPlayer().getDisplayName(),
            getPlayers().size(),
            config.getMaxPlayers()
        ));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerQuit(PlayerRemovedFromRosterEvent event) {
        game.broadcast(String.format(config.getQuitMessageFormat(),
            event.getPlayer().getDisplayName()
        ));
    }
}
