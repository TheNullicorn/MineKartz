package me.nullicorn.minekartz.kart;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import lombok.NonNull;
import me.nullicorn.minekartz.config.PhysicsConfig;
import me.nullicorn.minekartz.util.MathUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Slab.Type;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Represents a Kart that is owned by a player
 *
 * @author Nullicorn
 */
public class Kart {

    /**
     * The metadata key used for entities that are part of a kart (the bats & armor stands)
     */
    public static final String IS_KART_METADATA_KEY = "minekarts:isKart";

    /**
     * The KartManager that created this kart
     */
    private final KartManager kartManager;

    /**
     * Minecraft UUID of the player who owns this kart
     */
    private final UUID ownerUuid;

    /**
     * The game's physics configuration (stores acceleration, friction, etc)
     */
    private final PhysicsConfig physics;

    /**
     * This kart's random hover offset (so that karts don't hover in sync)
     */
    private final int randomHoverOffset;

    /**
     * The last-known controls pressed by the player
     */
    private final AtomicReference<PressedControls> lastPressedControls = new AtomicReference<>(PressedControls.NONE);

    /**
     * The invisible armor stand
     * <ul>
     *     <li>This is the entity that the player rides</li>
     *     <li>This armor stand is used to display the kart's 3d model</li>
     * </ul>
     */
    private ArmorStand armorStandEntity;

    /**
     * The invisible bat entity used to keep track of the kart's actual position
     */
    private Bat batEntity;

    /**
     * The kart's current velocity
     */
    private float velocity = 0.0f;

    /**
     * The kart's current rotational velocity (steering speed)
     */
    private float rotVelocity = 0.0f;

    protected Kart(KartManager kartManager, UUID ownerUuid, ConfigurationSection physicsConfig) {
        this.kartManager = kartManager;
        this.ownerUuid = ownerUuid;
        this.physics = new PhysicsConfig(physicsConfig);
        this.randomHoverOffset = new Random().nextInt(10000);
    }

    /**
     * @return Whether or not the specified entity is part of a kart
     */
    public static boolean isEntityKart(Entity vehicleEntity) {
        if (vehicleEntity instanceof ArmorStand || vehicleEntity instanceof Bat) {
            List<MetadataValue> metadataValues = vehicleEntity.getMetadata(Kart.IS_KART_METADATA_KEY);
            if (!metadataValues.isEmpty()) {
                return metadataValues.get(0).asBoolean();
            }
        }
        return false;
    }

    /**
     * Spawn the kart at the specified location. If it was already spawned in, this will delete the existing kart and spawn it again
     */
    protected void spawn(@NonNull Location location) {
        // If this kart (or any of its parts) are already spawned in, delete them
        despawn();

        World world = location.getWorld();
        if (world == null) {
            throw new NullPointerException("World was null for Kart spawn location");
        }

        batEntity = (Bat) world.spawnEntity(location, EntityType.BAT);
        batEntity.setAI(false);
        batEntity.setGravity(false);
        batEntity.setSilent(true);
        batEntity.setInvulnerable(true);
        batEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000000, 100, false, false));

        armorStandEntity = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStandEntity.setGravity(false);
        armorStandEntity.setVisible(false);
        if (armorStandEntity.getEquipment() != null) {
            armorStandEntity.getEquipment().setHelmet(new ItemStack(Material.IRON_ORE));
        }

        batEntity.setMetadata(IS_KART_METADATA_KEY, new FixedMetadataValue(kartManager.game, true));
        armorStandEntity.setMetadata(IS_KART_METADATA_KEY, new FixedMetadataValue(kartManager.game, true));
    }

    /**
     * Despawn the entities that make up this kart
     */
    protected void despawn() {
        if (armorStandEntity != null) {
            if (armorStandEntity.isValid()) {
                armorStandEntity.remove();
            }
            armorStandEntity = null;
        }
        if (batEntity != null) {
            if (batEntity.isValid()) {
                batEntity.remove();
            }
            batEntity = null;
        }
    }

    /**
     * @return Whether or not this kart is currently spawned in the world
     */
    public boolean isSpawned() {
        return armorStandEntity != null
            && armorStandEntity.isValid()
            && batEntity != null
            && batEntity.isValid();
    }

    /**
     * Eject this kart's current driver
     */
    protected void removeDriver() {
        if (isSpawned()) {
            armorStandEntity.eject();
        }
    }

    /**
     * Put this kart's owner in the driver seat
     */
    protected void pickupDriver() {
        Player player = Bukkit.getPlayer(ownerUuid);
        if (player == null || !player.isValid()) {
            kartManager.game.getLogger().warning("Unable to mount driver (" + ownerUuid + ")");
            return;
        }

        // Spawn the kart if not already spawned
        if (!isSpawned()) {
            spawn(player.getLocation());
        }

        // If player is null, eject any existing drivers
        if (isSpawned()) {
            armorStandEntity.addPassenger(player);
            kartManager.game.getLogger().info("Mounted driver: " + player.getName());
        }
    }

    /**
     * Process the keyboard inputs most recently pressed by the driver (e.g. "W" to go forward). The "forward" and "sideways" values come from the STEER_VEHICLE packet sent by the client
     */
    public void processInputs(float forward, float sideways) {
        lastPressedControls.set(PressedControls.from(forward, sideways));
    }

    /**
     * Update this Kart's position & state based on the last pressed inputs
     */
    protected void tick() {
        PressedControls controls = lastPressedControls.get();

        Location prevLoc = batEntity.getLocation();
        Location newLoc = prevLoc.clone();
        float acceleration = 0.0f;
        float rotAcceleration = 0.0f;

        // Set acceleration based on the current pressed controls
        if (controls.isForward()) {
            acceleration = physics.getForwardAcceleration();
        }
        if (controls.isBackward()) {
            acceleration = velocity < 0 ? -physics.getBackwardAcceleration() : -physics.getForwardAcceleration();
        }
        if (controls.isLeft()) {
            rotAcceleration = -physics.getRotAcceleration();
        }
        if (controls.isRight()) {
            rotAcceleration = physics.getRotAcceleration();
        }

        // Slow down if not driving forwards/backwards
        if (!controls.isForward() && !controls.isBackward()) {
            acceleration = 0;
            velocity = MathUtil.moveTowardsZero(velocity, physics.getFriction());
        }

        // Slow down turning if not steering
        if (!controls.isLeft() && !controls.isRight()) {
            rotAcceleration = 0;
            rotVelocity = MathUtil.moveTowardsZero(rotVelocity, physics.getRotFriction());
        }

        // Clamp velocities (between min and max) and apply velocity to location
        velocity = MathUtil.clampF(velocity + acceleration, -physics.getMaxBackwardVelocity(), physics.getMaxForwardVelocity());
        rotVelocity = MathUtil.clampF(rotVelocity + rotAcceleration, -physics.getMaxRotVelocity(), physics.getMaxRotVelocity());
        newLoc.add(prevLoc.getDirection().normalize().multiply(velocity));
        newLoc.setYaw(prevLoc.getYaw() + rotVelocity);
        newLoc.setY(prevLoc.getY()); // Ignore changes to Y

        // Move up if the block is a slab
        Block blockUnder = batEntity.getWorld().getHighestBlockAt(newLoc.getBlockX(), newLoc.getBlockZ());
        if (blockUnder.getBlockData() instanceof Slab && ((Slab) blockUnder.getBlockData()).getType() == Type.BOTTOM) {
            newLoc.setY(blockUnder.getY() + 0.5);
        } else {
            newLoc.setY(blockUnder.getY() + 1d);
        }

        // Teleport the bat & tilt the kart
        if (!prevLoc.equals(newLoc)) {
            batEntity.teleport(newLoc);
            armorStandEntity.setHeadPose(armorStandEntity.getHeadPose().setZ((rotVelocity / physics.getMaxRotVelocity()) * -0.35));
        }

        // Update the armor stand's height to give the kart a hovering effect
        newLoc.setY(newLoc.getY() + getHoverOffset());

        // Teleport the kart (requires NMS since the armor stand has a passenger; Bukkit blocks this for some reason)
        ((CraftArmorStand) armorStandEntity).getHandle().setPositionRotation(
            newLoc.getX(),
            newLoc.getY(),
            newLoc.getZ(),
            newLoc.getYaw(),
            newLoc.getPitch());

        // TODO: 8/4/20 Remove this actionBar message
        Bukkit.getPlayer(ownerUuid).spigot().sendMessage(ChatMessageType.ACTION_BAR,
            new TextComponent(ChatColor.RED + "Acceleration: "
                + ChatColor.GREEN + acceleration + ChatColor.RED + ", Velocity: " + ChatColor.GREEN + velocity));
    }

    /**
     * Get the kart's current hover offset. This offset is the difference in y-coordinate between this cart's bat and armor stand. This is used to give the effect of the card bobbing up and down
     * ("hovering")
     */
    private double getHoverOffset() {
        if (!isSpawned()) {
            return 0;
        }
        // Sine should be fast enough for this to not be an issue
        return StrictMath.sin(Math.toRadians((System.currentTimeMillis() + randomHoverOffset) / 10d)) / 6d;
    }
}
