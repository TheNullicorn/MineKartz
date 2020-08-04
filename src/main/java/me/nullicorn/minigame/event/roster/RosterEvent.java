package me.nullicorn.minigame.event.roster;

import lombok.Getter;
import lombok.NonNull;
import me.nullicorn.minigame.Game;
import me.nullicorn.minigame.Roster;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * A generic event related to a {@link Game game}'s {@link Roster roster}
 *
 * @author Nullicorn
 */
public abstract class RosterEvent extends PlayerEvent {

    @Getter
    private final Roster<?> roster;

    public RosterEvent(@NonNull Roster<?> roster, @NotNull Player player) {
        super(player);
        this.roster = roster;
    }
}
