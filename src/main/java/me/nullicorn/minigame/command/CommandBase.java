package me.nullicorn.minigame.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a basic command that can be registered by a Bukkit plugin
 *
 * @author Nullicorn
 */
public abstract class CommandBase implements CommandExecutor {

    /**
     * @return The name of this command
     */
    @NotNull
    public abstract String getName();

    /**
     * @return Whether or not this command can be executed from the console
     */
    public abstract boolean canConsoleSend();

    /**
     * Called when this command is executed (by a player, console, command block, etc)
     *
     * @param sender The command-sender that executed this command; if {@link #canConsoleSend()} returns false, this will not be a {@link ConsoleCommandSender}
     * @param args   The arguments passed to this command
     * @return Whether or not the command's syntax was valid; if false,
     */
    public abstract boolean onExecute(@NotNull CommandSender sender, @NotNull String[] args);

    /**
     * Send the command-sender an error message in response to their command (optionally formatted using {@link String#format(String, Object...)})
     */
    protected void error(@NotNull CommandSender sender, String message, String... params) {
        //noinspection ConfusingArgumentToVarargsMethod
        sender.sendMessage(ChatColor.RED + "Error! " + String.format(message, params));
    }


    /**
     * Send the command-sender a success message in response to their command (optionally formatted using {@link String#format(String, Object...)})
     */
    protected void success(@NotNull CommandSender sender, String message, String... params) {
        //noinspection ConfusingArgumentToVarargsMethod
        sender.sendMessage(ChatColor.GREEN + String.format(message, params));
    }

    /**
     * Attempt to register this command executor; be sure that the command is declared in plugin.yml first
     *
     * @param plugin Plugin to register this command under
     */
    public void register(@NotNull JavaPlugin plugin) {
        PluginCommand command = plugin.getCommand(getName());
        if (command != null) {
            command.setExecutor(this);
        } else {
            plugin.getLogger().warning("Failed to register command \"" + getName() + "\": command not found");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmdLabel, @NotNull String[] args) {
        if (!canConsoleSend() && sender instanceof ConsoleCommandSender) {
            // If a console tries to execute a non-console command, send an error message
            error(sender, "That command cannot be executed from the console!");

        } else if (!onExecute(sender, args)) {
            // If the syntax was invalid, send an error message
            error(sender, "Invalid usage. Try: %s", command.getUsage());
        }
        return true;
    }
}
