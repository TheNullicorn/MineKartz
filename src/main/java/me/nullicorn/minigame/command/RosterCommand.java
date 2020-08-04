package me.nullicorn.minigame.command;

import java.util.StringJoiner;
import me.nullicorn.minigame.Roster;
import me.nullicorn.minigame.util.UuidUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A command that allows ops to modify and read the current game's roster
 *
 * @param <P> Type of players stored in the roster
 * @author Nullicorn
 */
public class RosterCommand<P extends Player> extends CommandBase {

    private final Roster<P> roster;

    public RosterCommand(@NotNull Roster<P> roster) {
        this.roster = roster;
    }

    /**
     * @return The name of this command
     */
    @NotNull
    @Override
    public String getName() {
        return "roster";
    }

    /**
     * @return Whether or not this command can be executed from the console
     */
    @Override
    public boolean canConsoleSend() {
        return true;
    }

    /**
     * Called when this command is executed (by a player, console, command block, etc)
     *
     * @param sender The command-sender that executed this command; if {@link #canConsoleSend()} returns false, this will not be a {@link ConsoleCommandSender}
     * @param args   The arguments passed to this command
     * @return Whether or not the command's syntax was valid; if false,
     */
    @Override
    public boolean onExecute(@NotNull CommandSender sender, @NotNull String[] args) {
        // TODO: 8/4/20 Clean this command up

        if (args.length == 0) {
            return false;
        }

        // Add a player to the roster
        if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            String playerName = args[1];
            Player target = parsePlayerArg(playerName);

            if (target != null) {
                if (roster.has(target.getUniqueId())) {
                    error(sender, "\"%s\" is already on the roster", playerName);
                } else {
                    roster.addPlayer(target);
                    success(sender, "Added \"%s\" to the roster", playerName);
                }
            } else {
                error(sender, "Player not found: %s", playerName);
            }
            return true;
        }

        // Remove a player from the roster
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            String playerName = args[1];
            Player target = parsePlayerArg(playerName);

            if (target != null) {
                if (roster.has(target.getUniqueId())) {
                    roster.remove(target);
                    success(sender, "Removed \"%s\" from the game", playerName);
                } else {
                    error(sender, "Player \"%s\" is not on the roster", playerName);
                }
            } else {
                error(sender, "Player not found: %s", playerName);
            }
            return true;
        }

        // Print the current roster
        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            StringBuilder sb = new StringBuilder();
            sb.append(ChatColor.WHITE)
                .append("Current game roster (")
                .append(ChatColor.GREEN).append(roster.size()).append(ChatColor.WHITE)
                .append("): ");

            StringJoiner sj = new StringJoiner(ChatColor.WHITE + ", " + ChatColor.GREEN, ChatColor.GREEN.toString(), "");
            roster.asList().forEach(player -> sj.add(player.getName()));
            sb.append(sj.toString());

            success(sender, sb.toString());
            return true;
        }

        // Invalid syntax
        return false;
    }

    private Player parsePlayerArg(String arg) {
        if (UuidUtil.isUuid(arg)) {
            return Bukkit.getPlayer(UuidUtil.fromTrimmed(arg));
        } else {
            return Bukkit.getPlayer(arg);
        }
    }
}
