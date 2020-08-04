package me.nullicorn.minekartz.config;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Configuration for the pre-game state
 *
 * @author Nullicorn
 */
@Getter
public class PregameStateConfig extends Config {

    // Messages
    private final String joinMessageFormat;
    private final String quitMessageFormat;

    // Player count
    private final int minPlayers;
    private final int maxPlayers;

    public PregameStateConfig(ConfigurationSection rawConfig) {
        super(rawConfig);

        joinMessageFormat = getString("messages.join");
        quitMessageFormat = getString("messages.quit");

        minPlayers = getInt("minPlayers");
        maxPlayers = getInt("maxPlayers");
    }
}
