package me.nullicorn.minekartz.state;

import me.nullicorn.minekartz.MineKartz;
import me.nullicorn.minekartz.kart.KartManager;
import me.nullicorn.minigame.state.GameStateAdapter;

/**
 * Game state where the karts are at the start line ready to go
 *
 * @author Nullicorn
 */
public class ReadyState extends GameStateAdapter {

    private final KartManager kartManager;

    public ReadyState(MineKartz game) {
        super(game);
        this.kartManager = game.getKartManager();
    }

    @Override
    public String getName() {
        return "ReadySetGoState";
    }

    @Override
    protected void onStart() {
        getPlayers().forEach(player -> kartManager.spawnKartFor(player.getUniqueId(), player.getLocation()));
        broadcast("§cStarting game...");
    }

    @Override
    protected void onEnd() {
        kartManager.deleteAllKarts();
    }

    @Override
    public boolean isReadyToEnd() {
        return false;
    }
}
