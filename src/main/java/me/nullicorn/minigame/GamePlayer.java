package me.nullicorn.minigame;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Delegate class for {@link Player}s who are on a minigame's roster
 * <p>
 * TODO: 8/3/20 Might not need this
 *
 * @author Nullicorn
 */
public abstract class GamePlayer implements Player {

    @Getter
    protected final Player basePlayer;

    protected GamePlayer(Player player) {
        this.basePlayer = player;
    }

    /*
     * ==================== BEGIN DELEGATE METHODS ====================
     */

    @Override
    @NotNull
    public String getDisplayName() {
        return basePlayer.getDisplayName();
    }

    @Override
    public void setDisplayName(@Nullable String s) {
        basePlayer.setDisplayName(s);
    }

    @Override
    @NotNull
    public String getPlayerListName() {
        return basePlayer.getPlayerListName();
    }

    @Override
    public void setPlayerListName(@Nullable String s) {
        basePlayer.setPlayerListName(s);
    }

    @Override
    @Nullable
    public String getPlayerListHeader() {
        return basePlayer.getPlayerListHeader();
    }

    @Override
    @Nullable
    public String getPlayerListFooter() {
        return basePlayer.getPlayerListFooter();
    }

    @Override
    public void setPlayerListHeader(@Nullable String s) {
        basePlayer.setPlayerListHeader(s);
    }

    @Override
    public void setPlayerListFooter(@Nullable String s) {
        basePlayer.setPlayerListFooter(s);
    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable String s, @Nullable String s1) {
        basePlayer.setPlayerListHeaderFooter(s, s1);
    }

    @Override
    public void setCompassTarget(@NotNull Location location) {
        basePlayer.setCompassTarget(location);
    }

    @Override
    @NotNull
    public Location getCompassTarget() {
        return basePlayer.getCompassTarget();
    }

    @Override
    @Nullable
    public InetSocketAddress getAddress() {
        return basePlayer.getAddress();
    }

    @Override
    public void sendRawMessage(@NotNull String s) {
        basePlayer.sendRawMessage(s);
    }

    @Override
    public void kickPlayer(@Nullable String s) {
        basePlayer.kickPlayer(s);
    }

    @Override
    public void chat(@NotNull String s) {
        basePlayer.chat(s);
    }

    @Override
    public boolean performCommand(@NotNull String s) {
        return basePlayer.performCommand(s);
    }

    @Override
    public boolean isSneaking() {
        return basePlayer.isSneaking();
    }

    @Override
    public void setSneaking(boolean b) {
        basePlayer.setSneaking(b);
    }

    @Override
    public boolean isSprinting() {
        return basePlayer.isSprinting();
    }

    @Override
    public void setSprinting(boolean b) {
        basePlayer.setSprinting(b);
    }

    @Override
    public void saveData() {
        basePlayer.saveData();
    }

    @Override
    public void loadData() {
        basePlayer.loadData();
    }

    @Override
    public void setSleepingIgnored(boolean b) {
        basePlayer.setSleepingIgnored(b);
    }

    @Override
    public boolean isSleepingIgnored() {
        return basePlayer.isSleepingIgnored();
    }

    /**
     * @param location
     * @param b
     * @param b1
     * @deprecated
     */
    @Override
    @Deprecated
    public void playNote(@NotNull Location location, byte b, byte b1) {
        basePlayer.playNote(location, b, b1);
    }

    @Override
    public void playNote(@NotNull Location location, @NotNull Instrument instrument, @NotNull Note note) {
        basePlayer.playNote(location, instrument, note);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, float v, float v1) {
        basePlayer.playSound(location, sound, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String s, float v, float v1) {
        basePlayer.playSound(location, s, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory soundCategory, float v, float v1) {
        basePlayer.playSound(location, sound, soundCategory, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String s, @NotNull SoundCategory soundCategory, float v, float v1) {
        basePlayer.playSound(location, s, soundCategory, v, v1);
    }

    @Override
    public void stopSound(@NotNull Sound sound) {
        basePlayer.stopSound(sound);
    }

    @Override
    public void stopSound(@NotNull String s) {
        basePlayer.stopSound(s);
    }

    @Override
    public void stopSound(@NotNull Sound sound, @Nullable SoundCategory soundCategory) {
        basePlayer.stopSound(sound, soundCategory);
    }

    @Override
    public void stopSound(@NotNull String s, @Nullable SoundCategory soundCategory) {
        basePlayer.stopSound(s, soundCategory);
    }

    /**
     * @param location
     * @param effect
     * @param i
     * @deprecated
     */
    @Override
    @Deprecated
    public void playEffect(@NotNull Location location, @NotNull Effect effect, int i) {
        basePlayer.playEffect(location, effect, i);
    }

    @Override
    public <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T t) {
        basePlayer.playEffect(location, effect, t);
    }

    /**
     * @param location
     * @param material
     * @param b
     * @deprecated
     */
    @Override
    @Deprecated
    public void sendBlockChange(@NotNull Location location, @NotNull Material material, byte b) {
        basePlayer.sendBlockChange(location, material, b);
    }

    @Override
    public void sendBlockChange(@NotNull Location location, @NotNull BlockData blockData) {
        basePlayer.sendBlockChange(location, blockData);
    }

    /**
     * @param location
     * @param i
     * @param i1
     * @param i2
     * @param bytes
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean sendChunkChange(@NotNull Location location, int i, int i1, int i2, @NotNull byte[] bytes) {
        return basePlayer.sendChunkChange(location, i, i1, i2, bytes);
    }

    @Override
    public void sendSignChange(@NotNull Location location, @Nullable String[] strings) throws IllegalArgumentException {
        basePlayer.sendSignChange(location, strings);
    }

    @Override
    public void sendSignChange(@NotNull Location location, @Nullable String[] strings, @NotNull DyeColor dyeColor) throws IllegalArgumentException {
        basePlayer.sendSignChange(location, strings, dyeColor);
    }

    @Override
    public void sendMap(@NotNull MapView mapView) {
        basePlayer.sendMap(mapView);
    }

    @Override
    public void updateInventory() {
        basePlayer.updateInventory();
    }

    @Override
    public void setPlayerTime(long l, boolean b) {
        basePlayer.setPlayerTime(l, b);
    }

    @Override
    public long getPlayerTime() {
        return basePlayer.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return basePlayer.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return basePlayer.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        basePlayer.resetPlayerTime();
    }

    @Override
    public void setPlayerWeather(@NotNull WeatherType weatherType) {
        basePlayer.setPlayerWeather(weatherType);
    }

    @Override
    @Nullable
    public WeatherType getPlayerWeather() {
        return basePlayer.getPlayerWeather();
    }

    @Override
    public void resetPlayerWeather() {
        basePlayer.resetPlayerWeather();
    }

    @Override
    public void giveExp(int i) {
        basePlayer.giveExp(i);
    }

    @Override
    public void giveExpLevels(int i) {
        basePlayer.giveExpLevels(i);
    }

    @Override
    public float getExp() {
        return basePlayer.getExp();
    }

    @Override
    public void setExp(float v) {
        basePlayer.setExp(v);
    }

    @Override
    public int getLevel() {
        return basePlayer.getLevel();
    }

    @Override
    public void setLevel(int i) {
        basePlayer.setLevel(i);
    }

    @Override
    public int getTotalExperience() {
        return basePlayer.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int i) {
        basePlayer.setTotalExperience(i);
    }

    @Override
    public void sendExperienceChange(float v) {
        basePlayer.sendExperienceChange(v);
    }

    @Override
    public void sendExperienceChange(float v, int i) {
        basePlayer.sendExperienceChange(v, i);
    }

    @Override
    public float getExhaustion() {
        return basePlayer.getExhaustion();
    }

    @Override
    public void setExhaustion(float v) {
        basePlayer.setExhaustion(v);
    }

    @Override
    public float getSaturation() {
        return basePlayer.getSaturation();
    }

    @Override
    public void setSaturation(float v) {
        basePlayer.setSaturation(v);
    }

    @Override
    public int getFoodLevel() {
        return basePlayer.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int i) {
        basePlayer.setFoodLevel(i);
    }

    @Override
    public boolean getAllowFlight() {
        return basePlayer.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean b) {
        basePlayer.setAllowFlight(b);
    }

    /**
     * @param player
     * @deprecated
     */
    @Override
    @Deprecated
    public void hidePlayer(@NotNull Player player) {
        basePlayer.hidePlayer(player);
    }

    @Override
    public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player) {
        basePlayer.hidePlayer(plugin, player);
    }

    /**
     * @param player
     * @deprecated
     */
    @Override
    @Deprecated
    public void showPlayer(@NotNull Player player) {
        basePlayer.showPlayer(player);
    }

    @Override
    public void showPlayer(@NotNull Plugin plugin, @NotNull Player player) {
        basePlayer.showPlayer(plugin, player);
    }

    @Override
    public boolean canSee(@NotNull Player player) {
        return basePlayer.canSee(player);
    }

    @Override
    public boolean isFlying() {
        return basePlayer.isFlying();
    }

    @Override
    public void setFlying(boolean b) {
        basePlayer.setFlying(b);
    }

    @Override
    public void setFlySpeed(float v) throws IllegalArgumentException {
        basePlayer.setFlySpeed(v);
    }

    @Override
    public void setWalkSpeed(float v) throws IllegalArgumentException {
        basePlayer.setWalkSpeed(v);
    }

    @Override
    public float getFlySpeed() {
        return basePlayer.getFlySpeed();
    }

    @Override
    public float getWalkSpeed() {
        return basePlayer.getWalkSpeed();
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    @Deprecated
    public void setTexturePack(@NotNull String s) {
        basePlayer.setTexturePack(s);
    }

    @Override
    public void setResourcePack(@NotNull String s) {
        basePlayer.setResourcePack(s);
    }

    @Override
    public void setResourcePack(@NotNull String s, @NotNull byte[] bytes) {
        basePlayer.setResourcePack(s, bytes);
    }

    @Override
    @NotNull
    public Scoreboard getScoreboard() {
        return basePlayer.getScoreboard();
    }

    @Override
    public void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        basePlayer.setScoreboard(scoreboard);
    }

    @Override
    public boolean isHealthScaled() {
        return basePlayer.isHealthScaled();
    }

    @Override
    public void setHealthScaled(boolean b) {
        basePlayer.setHealthScaled(b);
    }

    @Override
    public void setHealthScale(double v) throws IllegalArgumentException {
        basePlayer.setHealthScale(v);
    }

    @Override
    public double getHealthScale() {
        return basePlayer.getHealthScale();
    }

    @Override
    @Nullable
    public Entity getSpectatorTarget() {
        return basePlayer.getSpectatorTarget();
    }

    @Override
    public void setSpectatorTarget(@Nullable Entity entity) {
        basePlayer.setSpectatorTarget(entity);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    @Deprecated
    public void sendTitle(@Nullable String s, @Nullable String s1) {
        basePlayer.sendTitle(s, s1);
    }

    @Override
    public void sendTitle(@Nullable String s, @Nullable String s1, int i, int i1, int i2) {
        basePlayer.sendTitle(s, s1, i, i1, i2);
    }

    @Override
    public void resetTitle() {
        basePlayer.resetTitle();
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i) {
        basePlayer.spawnParticle(particle, location, i);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i) {
        basePlayer.spawnParticle(particle, v, v1, v2, i);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, @Nullable T t) {
        basePlayer.spawnParticle(particle, location, i, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, @Nullable T t) {
        basePlayer.spawnParticle(particle, v, v1, v2, i, t);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2) {
        basePlayer.spawnParticle(particle, location, i, v, v1, v2);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        basePlayer.spawnParticle(particle, v, v1, v2, i, v3, v4, v5);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, @Nullable T t) {
        basePlayer.spawnParticle(particle, location, i, v, v1, v2, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, @Nullable T t) {
        basePlayer.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3) {
        basePlayer.spawnParticle(particle, location, i, v, v1, v2, v3);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        basePlayer.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, @Nullable T t) {
        basePlayer.spawnParticle(particle, location, i, v, v1, v2, v3, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, @Nullable T t) {
        basePlayer.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t);
    }

    @Override
    @NotNull
    public AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement) {
        return basePlayer.getAdvancementProgress(advancement);
    }

    @Override
    public int getClientViewDistance() {
        return basePlayer.getClientViewDistance();
    }

    @Override
    @NotNull
    public String getLocale() {
        return basePlayer.getLocale();
    }

    @Override
    public void updateCommands() {
        basePlayer.updateCommands();
    }

    @Override
    public void openBook(@NotNull ItemStack itemStack) {
        basePlayer.openBook(itemStack);
    }

    @Override
    @NotNull
    public Spigot spigot() {
        return basePlayer.spigot();
    }

    @Override
    @NotNull
    public String getName() {
        return basePlayer.getName();
    }

    @Override
    @NotNull
    public PlayerInventory getInventory() {
        return basePlayer.getInventory();
    }

    @Override
    @NotNull
    public Inventory getEnderChest() {
        return basePlayer.getEnderChest();
    }

    @Override
    @NotNull
    public MainHand getMainHand() {
        return basePlayer.getMainHand();
    }

    @Override
    public boolean setWindowProperty(@NotNull Property property, int i) {
        return basePlayer.setWindowProperty(property, i);
    }

    @Override
    @NotNull
    public InventoryView getOpenInventory() {
        return basePlayer.getOpenInventory();
    }

    @Override
    @Nullable
    public InventoryView openInventory(@NotNull Inventory inventory) {
        return basePlayer.openInventory(inventory);
    }

    @Override
    @Nullable
    public InventoryView openWorkbench(@Nullable Location location, boolean b) {
        return basePlayer.openWorkbench(location, b);
    }

    @Override
    @Nullable
    public InventoryView openEnchanting(@Nullable Location location, boolean b) {
        return basePlayer.openEnchanting(location, b);
    }

    @Override
    public void openInventory(@NotNull InventoryView inventoryView) {
        basePlayer.openInventory(inventoryView);
    }

    @Override
    @Nullable
    public InventoryView openMerchant(@NotNull Villager villager, boolean b) {
        return basePlayer.openMerchant(villager, b);
    }

    @Override
    @Nullable
    public InventoryView openMerchant(@NotNull Merchant merchant, boolean b) {
        return basePlayer.openMerchant(merchant, b);
    }

    @Override
    public void closeInventory() {
        basePlayer.closeInventory();
    }

    /**
     * @deprecated
     */
    @Override
    @NotNull
    @Deprecated
    public ItemStack getItemInHand() {
        return basePlayer.getItemInHand();
    }

    /**
     * @param itemStack
     * @deprecated
     */
    @Override
    @Deprecated
    public void setItemInHand(@Nullable ItemStack itemStack) {
        basePlayer.setItemInHand(itemStack);
    }

    @Override
    @NotNull
    public ItemStack getItemOnCursor() {
        return basePlayer.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(@Nullable ItemStack itemStack) {
        basePlayer.setItemOnCursor(itemStack);
    }

    @Override
    public boolean hasCooldown(@NotNull Material material) {
        return basePlayer.hasCooldown(material);
    }

    @Override
    public int getCooldown(@NotNull Material material) {
        return basePlayer.getCooldown(material);
    }

    @Override
    public void setCooldown(@NotNull Material material, int i) {
        basePlayer.setCooldown(material, i);
    }

    @Override
    public int getSleepTicks() {
        return basePlayer.getSleepTicks();
    }

    @Override
    @Nullable
    public Location getBedSpawnLocation() {
        return basePlayer.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(@Nullable Location location) {
        basePlayer.setBedSpawnLocation(location);
    }

    @Override
    public void setBedSpawnLocation(@Nullable Location location, boolean b) {
        basePlayer.setBedSpawnLocation(location, b);
    }

    @Override
    public boolean sleep(@NotNull Location location, boolean b) {
        return basePlayer.sleep(location, b);
    }

    @Override
    public void wakeup(boolean b) {
        basePlayer.wakeup(b);
    }

    @Override
    @NotNull
    public Location getBedLocation() {
        return basePlayer.getBedLocation();
    }

    @Override
    @NotNull
    public GameMode getGameMode() {
        return basePlayer.getGameMode();
    }

    @Override
    public void setGameMode(@NotNull GameMode gameMode) {
        basePlayer.setGameMode(gameMode);
    }

    @Override
    public boolean isBlocking() {
        return basePlayer.isBlocking();
    }

    @Override
    public boolean isHandRaised() {
        return basePlayer.isHandRaised();
    }

    @Override
    public int getExpToLevel() {
        return basePlayer.getExpToLevel();
    }

    @Override
    public float getAttackCooldown() {
        return basePlayer.getAttackCooldown();
    }

    @Override
    public boolean discoverRecipe(@NotNull NamespacedKey namespacedKey) {
        return basePlayer.discoverRecipe(namespacedKey);
    }

    @Override
    public int discoverRecipes(@NotNull Collection<NamespacedKey> collection) {
        return basePlayer.discoverRecipes(collection);
    }

    @Override
    public boolean undiscoverRecipe(@NotNull NamespacedKey namespacedKey) {
        return basePlayer.undiscoverRecipe(namespacedKey);
    }

    @Override
    public int undiscoverRecipes(@NotNull Collection<NamespacedKey> collection) {
        return basePlayer.undiscoverRecipes(collection);
    }

    /**
     * @deprecated
     */
    @Override
    @Nullable
    @Deprecated
    public Entity getShoulderEntityLeft() {
        return basePlayer.getShoulderEntityLeft();
    }

    /**
     * @param entity
     * @deprecated
     */
    @Override
    @Deprecated
    public void setShoulderEntityLeft(@Nullable Entity entity) {
        basePlayer.setShoulderEntityLeft(entity);
    }

    /**
     * @deprecated
     */
    @Override
    @Nullable
    @Deprecated
    public Entity getShoulderEntityRight() {
        return basePlayer.getShoulderEntityRight();
    }

    /**
     * @param entity
     * @deprecated
     */
    @Override
    @Deprecated
    public void setShoulderEntityRight(@Nullable Entity entity) {
        basePlayer.setShoulderEntityRight(entity);
    }

    @Override
    public double getEyeHeight() {
        return basePlayer.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean b) {
        return basePlayer.getEyeHeight(b);
    }

    @Override
    @NotNull
    public Location getEyeLocation() {
        return basePlayer.getEyeLocation();
    }

    @Override
    @NotNull
    public List<Block> getLineOfSight(@Nullable Set<Material> set, int i) {
        return basePlayer.getLineOfSight(set, i);
    }

    @Override
    @NotNull
    public Block getTargetBlock(@Nullable Set<Material> set, int i) {
        return basePlayer.getTargetBlock(set, i);
    }

    @Override
    @NotNull
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> set, int i) {
        return basePlayer.getLastTwoTargetBlocks(set, i);
    }

    @Override
    @Nullable
    public Block getTargetBlockExact(int i) {
        return basePlayer.getTargetBlockExact(i);
    }

    @Override
    @Nullable
    public Block getTargetBlockExact(int i, @NotNull FluidCollisionMode fluidCollisionMode) {
        return basePlayer.getTargetBlockExact(i, fluidCollisionMode);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(double v) {
        return basePlayer.rayTraceBlocks(v);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(double v, @NotNull FluidCollisionMode fluidCollisionMode) {
        return basePlayer.rayTraceBlocks(v, fluidCollisionMode);
    }

    @Override
    public int getRemainingAir() {
        return basePlayer.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int i) {
        basePlayer.setRemainingAir(i);
    }

    @Override
    public int getMaximumAir() {
        return basePlayer.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int i) {
        basePlayer.setMaximumAir(i);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return basePlayer.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        basePlayer.setMaximumNoDamageTicks(i);
    }

    @Override
    public double getLastDamage() {
        return basePlayer.getLastDamage();
    }

    @Override
    public void setLastDamage(double v) {
        basePlayer.setLastDamage(v);
    }

    @Override
    public int getNoDamageTicks() {
        return basePlayer.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int i) {
        basePlayer.setNoDamageTicks(i);
    }

    @Override
    @Nullable
    public Player getKiller() {
        return basePlayer.getKiller();
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect potionEffect) {
        return basePlayer.addPotionEffect(potionEffect);
    }

    /**
     * @param potionEffect
     * @param b
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean addPotionEffect(@NotNull PotionEffect potionEffect, boolean b) {
        return basePlayer.addPotionEffect(potionEffect, b);
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> collection) {
        return basePlayer.addPotionEffects(collection);
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return basePlayer.hasPotionEffect(potionEffectType);
    }

    @Override
    @Nullable
    public PotionEffect getPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return basePlayer.getPotionEffect(potionEffectType);
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType potionEffectType) {
        basePlayer.removePotionEffect(potionEffectType);
    }

    @Override
    @NotNull
    public Collection<PotionEffect> getActivePotionEffects() {
        return basePlayer.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity entity) {
        return basePlayer.hasLineOfSight(entity);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return basePlayer.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean b) {
        basePlayer.setRemoveWhenFarAway(b);
    }

    @Override
    @Nullable
    public EntityEquipment getEquipment() {
        return basePlayer.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean b) {
        basePlayer.setCanPickupItems(b);
    }

    @Override
    public boolean getCanPickupItems() {
        return basePlayer.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return basePlayer.isLeashed();
    }

    @Override
    @NotNull
    public Entity getLeashHolder() throws IllegalStateException {
        return basePlayer.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity entity) {
        return basePlayer.setLeashHolder(entity);
    }

    @Override
    public boolean isGliding() {
        return basePlayer.isGliding();
    }

    @Override
    public void setGliding(boolean b) {
        basePlayer.setGliding(b);
    }

    @Override
    public boolean isSwimming() {
        return basePlayer.isSwimming();
    }

    @Override
    public void setSwimming(boolean b) {
        basePlayer.setSwimming(b);
    }

    @Override
    public boolean isRiptiding() {
        return basePlayer.isRiptiding();
    }

    @Override
    public boolean isSleeping() {
        return basePlayer.isSleeping();
    }

    @Override
    public void setAI(boolean b) {
        basePlayer.setAI(b);
    }

    @Override
    public boolean hasAI() {
        return basePlayer.hasAI();
    }

    @Override
    public void attack(@NotNull Entity entity) {
        basePlayer.attack(entity);
    }

    @Override
    public void swingMainHand() {
        basePlayer.swingMainHand();
    }

    @Override
    public void swingOffHand() {
        basePlayer.swingOffHand();
    }

    @Override
    public void setCollidable(boolean b) {
        basePlayer.setCollidable(b);
    }

    @Override
    public boolean isCollidable() {
        return basePlayer.isCollidable();
    }

    @Override
    @Nullable
    public <T> T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return basePlayer.getMemory(memoryKey);
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T t) {
        basePlayer.setMemory(memoryKey, t);
    }

    @Override
    @Nullable
    public AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return basePlayer.getAttribute(attribute);
    }

    @Override
    public void damage(double v) {
        basePlayer.damage(v);
    }

    @Override
    public void damage(double v, @Nullable Entity entity) {
        basePlayer.damage(v, entity);
    }

    @Override
    public double getHealth() {
        return basePlayer.getHealth();
    }

    @Override
    public void setHealth(double v) {
        basePlayer.setHealth(v);
    }

    @Override
    public double getAbsorptionAmount() {
        return basePlayer.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double v) {
        basePlayer.setAbsorptionAmount(v);
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public double getMaxHealth() {
        return basePlayer.getMaxHealth();
    }

    /**
     * @param v
     * @deprecated
     */
    @Override
    @Deprecated
    public void setMaxHealth(double v) {
        basePlayer.setMaxHealth(v);
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public void resetMaxHealth() {
        basePlayer.resetMaxHealth();
    }

    @Override
    @NotNull
    public Location getLocation() {
        return basePlayer.getLocation();
    }

    @Override
    @Nullable
    @Contract("null -> null; !null -> !null")
    public Location getLocation(@Nullable Location location) {
        return basePlayer.getLocation(location);
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        basePlayer.setVelocity(vector);
    }

    @Override
    @NotNull
    public Vector getVelocity() {
        return basePlayer.getVelocity();
    }

    @Override
    public double getHeight() {
        return basePlayer.getHeight();
    }

    @Override
    public double getWidth() {
        return basePlayer.getWidth();
    }

    @Override
    @NotNull
    public BoundingBox getBoundingBox() {
        return basePlayer.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return basePlayer.isOnGround();
    }

    @Override
    @NotNull
    public World getWorld() {
        return basePlayer.getWorld();
    }

    @Override
    public void setRotation(float v, float v1) {
        basePlayer.setRotation(v, v1);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return basePlayer.teleport(location);
    }

    @Override
    public boolean teleport(@NotNull Location location, @NotNull TeleportCause teleportCause) {
        return basePlayer.teleport(location, teleportCause);
    }

    @Override
    public boolean teleport(@NotNull Entity entity) {
        return basePlayer.teleport(entity);
    }

    @Override
    public boolean teleport(@NotNull Entity entity, @NotNull TeleportCause teleportCause) {
        return basePlayer.teleport(entity, teleportCause);
    }

    @Override
    @NotNull
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return basePlayer.getNearbyEntities(v, v1, v2);
    }

    @Override
    public int getEntityId() {
        return basePlayer.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return basePlayer.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return basePlayer.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int i) {
        basePlayer.setFireTicks(i);
    }

    @Override
    public void remove() {
        basePlayer.remove();
    }

    @Override
    public boolean isDead() {
        return basePlayer.isDead();
    }

    @Override
    public boolean isValid() {
        return basePlayer.isValid();
    }

    @Override
    @NotNull
    public Server getServer() {
        return basePlayer.getServer();
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean isPersistent() {
        return basePlayer.isPersistent();
    }

    /**
     * @param b
     * @deprecated
     */
    @Override
    @Deprecated
    public void setPersistent(boolean b) {
        basePlayer.setPersistent(b);
    }

    /**
     * @deprecated
     */
    @Override
    @Nullable
    @Deprecated
    public Entity getPassenger() {
        return basePlayer.getPassenger();
    }

    /**
     * @param entity
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean setPassenger(@NotNull Entity entity) {
        return basePlayer.setPassenger(entity);
    }

    @Override
    @NotNull
    public List<Entity> getPassengers() {
        return basePlayer.getPassengers();
    }

    @Override
    public boolean addPassenger(@NotNull Entity entity) {
        return basePlayer.addPassenger(entity);
    }

    @Override
    public boolean removePassenger(@NotNull Entity entity) {
        return basePlayer.removePassenger(entity);
    }

    @Override
    public boolean isEmpty() {
        return basePlayer.isEmpty();
    }

    @Override
    public boolean eject() {
        return basePlayer.eject();
    }

    @Override
    public float getFallDistance() {
        return basePlayer.getFallDistance();
    }

    @Override
    public void setFallDistance(float v) {
        basePlayer.setFallDistance(v);
    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent entityDamageEvent) {
        basePlayer.setLastDamageCause(entityDamageEvent);
    }

    @Override
    @Nullable
    public EntityDamageEvent getLastDamageCause() {
        return basePlayer.getLastDamageCause();
    }

    @Override
    @NotNull
    public UUID getUniqueId() {
        return basePlayer.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return basePlayer.getTicksLived();
    }

    @Override
    public void setTicksLived(int i) {
        basePlayer.setTicksLived(i);
    }

    @Override
    public void playEffect(@NotNull EntityEffect entityEffect) {
        basePlayer.playEffect(entityEffect);
    }

    @Override
    @NotNull
    public EntityType getType() {
        return basePlayer.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return basePlayer.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return basePlayer.leaveVehicle();
    }

    @Override
    @Nullable
    public Entity getVehicle() {
        return basePlayer.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean b) {
        basePlayer.setCustomNameVisible(b);
    }

    @Override
    public boolean isCustomNameVisible() {
        return basePlayer.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean b) {
        basePlayer.setGlowing(b);
    }

    @Override
    public boolean isGlowing() {
        return basePlayer.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean b) {
        basePlayer.setInvulnerable(b);
    }

    @Override
    public boolean isInvulnerable() {
        return basePlayer.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return basePlayer.isSilent();
    }

    @Override
    public void setSilent(boolean b) {
        basePlayer.setSilent(b);
    }

    @Override
    public boolean hasGravity() {
        return basePlayer.hasGravity();
    }

    @Override
    public void setGravity(boolean b) {
        basePlayer.setGravity(b);
    }

    @Override
    public int getPortalCooldown() {
        return basePlayer.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int i) {
        basePlayer.setPortalCooldown(i);
    }

    @Override
    @NotNull
    public Set<String> getScoreboardTags() {
        return basePlayer.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String s) {
        return basePlayer.addScoreboardTag(s);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String s) {
        return basePlayer.removeScoreboardTag(s);
    }

    @Override
    @NotNull
    public PistonMoveReaction getPistonMoveReaction() {
        return basePlayer.getPistonMoveReaction();
    }

    @Override
    @NotNull
    public BlockFace getFacing() {
        return basePlayer.getFacing();
    }

    @Override
    @NotNull
    public Pose getPose() {
        return basePlayer.getPose();
    }

    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        basePlayer.setMetadata(s, metadataValue);
    }

    @Override
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String s) {
        return basePlayer.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(@NotNull String s) {
        return basePlayer.hasMetadata(s);
    }

    @Override
    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        basePlayer.removeMetadata(s, plugin);
    }

    @Override
    public void sendMessage(@NotNull String s) {
        basePlayer.sendMessage(s);
    }

    @Override
    public void sendMessage(@NotNull String[] strings) {
        basePlayer.sendMessage(strings);
    }

    @Override
    public boolean isPermissionSet(@NotNull String s) {
        return basePlayer.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission permission) {
        return basePlayer.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(@NotNull String s) {
        return basePlayer.hasPermission(s);
    }

    @Override
    public boolean hasPermission(@NotNull Permission permission) {
        return basePlayer.hasPermission(permission);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        return basePlayer.addAttachment(plugin, s, b);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return basePlayer.addAttachment(plugin);
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        return basePlayer.addAttachment(plugin, s, b, i);
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        return basePlayer.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {
        basePlayer.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        basePlayer.recalculatePermissions();
    }

    @Override
    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return basePlayer.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return basePlayer.isOp();
    }

    @Override
    public void setOp(boolean b) {
        basePlayer.setOp(b);
    }

    @Override
    @Nullable
    public String getCustomName() {
        return basePlayer.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable String s) {
        basePlayer.setCustomName(s);
    }

    @Override
    @NotNull
    public PersistentDataContainer getPersistentDataContainer() {
        return basePlayer.getPersistentDataContainer();
    }

    @Override
    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass) {
        return basePlayer.launchProjectile(aClass);
    }

    @Override
    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass, @Nullable Vector vector) {
        return basePlayer.launchProjectile(aClass, vector);
    }

    @Override
    public boolean isConversing() {
        return basePlayer.isConversing();
    }

    @Override
    public void acceptConversationInput(@NotNull String s) {
        basePlayer.acceptConversationInput(s);
    }

    @Override
    public boolean beginConversation(@NotNull Conversation conversation) {
        return basePlayer.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation) {
        basePlayer.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent conversationAbandonedEvent) {
        basePlayer.abandonConversation(conversation, conversationAbandonedEvent);
    }

    @Override
    public boolean isOnline() {
        return basePlayer.isOnline();
    }

    @Override
    public boolean isBanned() {
        return basePlayer.isBanned();
    }

    @Override
    public boolean isWhitelisted() {
        return basePlayer.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean b) {
        basePlayer.setWhitelisted(b);
    }

    @Override
    @Nullable
    public Player getPlayer() {
        return basePlayer.getPlayer();
    }

    @Override
    public long getFirstPlayed() {
        return basePlayer.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return basePlayer.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return basePlayer.hasPlayedBefore();
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        basePlayer.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        basePlayer.decrementStatistic(statistic, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        basePlayer.setStatistic(statistic, i);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return basePlayer.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        basePlayer.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return basePlayer.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic, material, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        basePlayer.decrementStatistic(statistic, material, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        basePlayer.setStatistic(statistic, material, i);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        basePlayer.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return basePlayer.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) throws IllegalArgumentException {
        basePlayer.incrementStatistic(statistic, entityType, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        basePlayer.decrementStatistic(statistic, entityType, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        basePlayer.setStatistic(statistic, entityType, i);
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return basePlayer.serialize();
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin plugin, @NotNull String s, @NotNull byte[] bytes) {
        basePlayer.sendPluginMessage(plugin, s, bytes);
    }

    @Override
    @NotNull
    public Set<String> getListeningPluginChannels() {
        return basePlayer.getListeningPluginChannels();
    }
}
