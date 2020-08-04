package me.nullicorn.minekartz.config;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Configuration values for the game's kart physics
 *
 * @author Nullicorn
 */
@Getter
public class PhysicsConfig extends Config {

    // Movement
    private final float forwardAcceleration;
    private final float backwardAcceleration;
    private final float maxForwardVelocity;
    private final float maxBackwardVelocity;
    private final float friction;

    // Steering
    private final float rotAcceleration;
    private final float maxRotVelocity;
    private final float rotFriction;

    public PhysicsConfig(ConfigurationSection rawConfig) {
        super(rawConfig);

        forwardAcceleration = (float) getDouble("movement.forward_acceleration");
        backwardAcceleration = (float) getDouble("movement.backward_acceleration");
        maxForwardVelocity = (float) getDouble("movement.max_forward_velocity");
        maxBackwardVelocity = (float) getDouble("movement.max_backward_velocity");
        friction = (float) getDouble("movement.friction");

        rotAcceleration = (float) getDouble("steering.rotational_acceleration");
        maxRotVelocity = (float) getDouble("steering.max_rotational_velocity");
        rotFriction = (float) getDouble("steering.rotational_friction");
    }
}
