package me.nullicorn.minigame.event.roster;

import lombok.NonNull;
import me.nullicorn.minigame.Roster;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired right before a player is added to a game's roster
 *
 * @author Nullicorn
 */
public class PlayerAddedToRosterEvent extends RosterEvent implements Cancellable {

    private static final HandlerList HANDLERS    = new HandlerList();
    private              boolean     isCancelled = false;

    public PlayerAddedToRosterEvent(@NonNull Roster<?> roster, Player player) {
        super(roster, player);
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * This event is fired after a player has been added to a game's roster
     */
    public static class Post extends RosterEvent {

        private static final HandlerList HANDLERS = new HandlerList();

        public Post(@NonNull Roster<?> roster, Player player) {
            super(roster, player);
        }

        @NotNull
        @Override
        public HandlerList getHandlers() {
            return HANDLERS;
        }

        @SuppressWarnings("unused")
        public static HandlerList getHandlerList() {
            return HANDLERS;
        }
    }
}
