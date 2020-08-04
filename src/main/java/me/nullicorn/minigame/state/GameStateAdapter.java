package me.nullicorn.minigame.state;

import java.time.Duration;
import me.nullicorn.minigame.Game;
import org.jetbrains.annotations.NotNull;

/**
 * Adapter class for {@link GameState}
 *
 * @author Nullicorn
 */
public abstract class GameStateAdapter extends GameState {

    protected GameStateAdapter(Game<?> game) {
        super(game);
    }

    @Override
    public String getName() {
        return null;
    }

    @NotNull
    @Override
    public Duration getDuration() {
        return Duration.ZERO;
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    protected void onEnd() {

    }
}
