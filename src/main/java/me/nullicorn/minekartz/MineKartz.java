package me.nullicorn.minekartz;

import lombok.Getter;
import me.nullicorn.minekartz.kart.KartManager;
import me.nullicorn.minekartz.roster.MineKartzPlayer;
import me.nullicorn.minekartz.roster.MineKartzRoster;
import me.nullicorn.minekartz.state.PregameState;
import me.nullicorn.minekartz.state.ReadyState;
import me.nullicorn.minigame.Game;
import me.nullicorn.minigame.Roster;
import me.nullicorn.minigame.state.ScheduledStateSeries;

/**
 * Main plugin class for the MineKartz minigame
 *
 * @author Nullicorn
 */
public class MineKartz extends Game<MineKartzPlayer> {

    private MineKartzRoster      roster;
    @Getter
    private KartManager          kartManager;
    private ScheduledStateSeries mainState;

    @Override
    public void onStart() {
        saveDefaultConfig();

        roster = new MineKartzRoster(this);
        kartManager = new KartManager(this);

        mainState = new ScheduledStateSeries(this);
        mainState.add(new PregameState(this, getConfig().getConfigurationSection("states.pregame")));
        mainState.add(new ReadyState(this));
        mainState.start();
    }

    @Override
    public void onDisable() {
        mainState.end();
        kartManager.deregister();
    }

    @Override
    public Roster<MineKartzPlayer> getRoster() {
        return roster;
    }
}
