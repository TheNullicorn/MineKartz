package me.nullicorn.minigame.event.roster;

import lombok.NonNull;
import me.nullicorn.minigame.Roster;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired after a player has been removed from a game's roster
 *
 * @author Nullicorn
 */
public class PlayerRemovedFromRosterEvent extends RosterEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerRemovedFromRosterEvent(@NonNull Roster<?> roster, Player player) {
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
