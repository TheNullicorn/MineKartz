package me.nullicorn.minigame.state;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import net.minikloon.fsmgasm.State;
import net.minikloon.fsmgasm.StateHolder;
import org.jetbrains.annotations.NotNull;

/**
 * Fixed version of {@link net.minikloon.fsmgasm.StateSeries} that doesn't call {@link State#end()} twice
 */
public abstract class StateSeries extends StateHolder {

    protected int     current  = 0;
    protected boolean skipping = false;

    public StateSeries(State... states) {
        super(Arrays.asList(states));
    }

    public void addNext(State state) {
        getStates().add(current + 1, state);
    }

    public void addNext(List<State> newStates) {
        for (int i = 0; i < newStates.size(); i++) {
            getStates().add(current + i, newStates.get(i));
        }
    }

    public void skipCurrent() {
        skipping = true;
    }

    @Override
    protected void onStart() {
        if (getStates().isEmpty()) {
            end();
            return;
        }

        getStates().get(current).start();
    }

    @Override
    public void onUpdate() {
        State currentState = getStates().get(current);
        currentState.update();

        if ((currentState.isReadyToEnd() && !currentState.getFrozen()) || currentState.getEnded() || skipping) {
            if (skipping) {
                skipping = false;
            }
            if (!currentState.getEnded()) {
                currentState.end();
            }

            // If the last state just ended, end this series
            current++;
            if (current >= getStates().size()) {
                end();
                return;
            }

            // Start the next state
            getStates().get(current).start();
        }
    }

    @Override
    public boolean isReadyToEnd() {
        return (current == getStates().size() - 1 && getStates().get(current).isReadyToEnd());
    }

    @Override
    protected void onEnd() {
        if (current < getStates().size()) {
            getStates().get(current).end();
        }
    }

    @NotNull
    @Override
    public Duration getDuration() {
        long totalSeconds = 0;
        for (State state : getStates()) {
            totalSeconds += state.getDuration().getSeconds();
        }
        return Duration.ofSeconds(totalSeconds);
    }
}