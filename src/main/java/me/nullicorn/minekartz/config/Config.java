package me.nullicorn.minekartz.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Nullicorn
 */
public abstract class Config implements ConfigurationSection {

    private final ConfigurationSection rawConfig;

    public Config(ConfigurationSection rawConfig) {
        this.rawConfig = rawConfig;
    }

    /*
     * ==================== BEGIN DELEGATE METHODS ====================
     */

    @Override
    @NotNull
    public Set<String> getKeys(boolean b) {
        return rawConfig.getKeys(b);
    }

    @Override
    @NotNull
    public Map<String, Object> getValues(boolean b) {
        return rawConfig.getValues(b);
    }

    @Override
    public boolean contains(@NotNull String s) {
        return rawConfig.contains(s);
    }

    @Override
    public boolean contains(@NotNull String s, boolean b) {
        return rawConfig.contains(s, b);
    }

    @Override
    public boolean isSet(@NotNull String s) {
        return rawConfig.isSet(s);
    }

    @Override
    @Nullable
    public String getCurrentPath() {
        return rawConfig.getCurrentPath();
    }

    @Override
    @NotNull
    public String getName() {
        return rawConfig.getName();
    }

    @Override
    @Nullable
    public Configuration getRoot() {
        return rawConfig.getRoot();
    }

    @Override
    @Nullable
    public ConfigurationSection getParent() {
        return rawConfig.getParent();
    }

    @Override
    @Nullable
    public Object get(@NotNull String s) {
        return rawConfig.get(s);
    }

    @Override
    @Nullable
    public Object get(@NotNull String s, @Nullable Object o) {
        return rawConfig.get(s, o);
    }

    @Override
    public void set(@NotNull String s, @Nullable Object o) {
        rawConfig.set(s, o);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String s) {
        return rawConfig.createSection(s);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String s, @NotNull Map<?, ?> map) {
        return rawConfig.createSection(s, map);
    }

    @Override
    @Nullable
    public String getString(@NotNull String s) {
        return rawConfig.getString(s);
    }

    @Override
    @Nullable
    public String getString(@NotNull String s, @Nullable String s1) {
        return rawConfig.getString(s, s1);
    }

    @Override
    public boolean isString(@NotNull String s) {
        return rawConfig.isString(s);
    }

    @Override
    public int getInt(@NotNull String s) {
        return rawConfig.getInt(s);
    }

    @Override
    public int getInt(@NotNull String s, int i) {
        return rawConfig.getInt(s, i);
    }

    @Override
    public boolean isInt(@NotNull String s) {
        return rawConfig.isInt(s);
    }

    @Override
    public boolean getBoolean(@NotNull String s) {
        return rawConfig.getBoolean(s);
    }

    @Override
    public boolean getBoolean(@NotNull String s, boolean b) {
        return rawConfig.getBoolean(s, b);
    }

    @Override
    public boolean isBoolean(@NotNull String s) {
        return rawConfig.isBoolean(s);
    }

    @Override
    public double getDouble(@NotNull String s) {
        return rawConfig.getDouble(s);
    }

    @Override
    public double getDouble(@NotNull String s, double v) {
        return rawConfig.getDouble(s, v);
    }

    @Override
    public boolean isDouble(@NotNull String s) {
        return rawConfig.isDouble(s);
    }

    @Override
    public long getLong(@NotNull String s) {
        return rawConfig.getLong(s);
    }

    @Override
    public long getLong(@NotNull String s, long l) {
        return rawConfig.getLong(s, l);
    }

    @Override
    public boolean isLong(@NotNull String s) {
        return rawConfig.isLong(s);
    }

    @Override
    @Nullable
    public List<?> getList(@NotNull String s) {
        return rawConfig.getList(s);
    }

    @Override
    @Nullable
    public List<?> getList(@NotNull String s, @Nullable List<?> list) {
        return rawConfig.getList(s, list);
    }

    @Override
    public boolean isList(@NotNull String s) {
        return rawConfig.isList(s);
    }

    @Override
    @NotNull
    public List<String> getStringList(@NotNull String s) {
        return rawConfig.getStringList(s);
    }

    @Override
    @NotNull
    public List<Integer> getIntegerList(@NotNull String s) {
        return rawConfig.getIntegerList(s);
    }

    @Override
    @NotNull
    public List<Boolean> getBooleanList(@NotNull String s) {
        return rawConfig.getBooleanList(s);
    }

    @Override
    @NotNull
    public List<Double> getDoubleList(@NotNull String s) {
        return rawConfig.getDoubleList(s);
    }

    @Override
    @NotNull
    public List<Float> getFloatList(@NotNull String s) {
        return rawConfig.getFloatList(s);
    }

    @Override
    @NotNull
    public List<Long> getLongList(@NotNull String s) {
        return rawConfig.getLongList(s);
    }

    @Override
    @NotNull
    public List<Byte> getByteList(@NotNull String s) {
        return rawConfig.getByteList(s);
    }

    @Override
    @NotNull
    public List<Character> getCharacterList(@NotNull String s) {
        return rawConfig.getCharacterList(s);
    }

    @Override
    @NotNull
    public List<Short> getShortList(@NotNull String s) {
        return rawConfig.getShortList(s);
    }

    @Override
    @NotNull
    public List<Map<?, ?>> getMapList(@NotNull String s) {
        return rawConfig.getMapList(s);
    }

    @Override
    @Nullable
    public <T> T getObject(@NotNull String s, @NotNull Class<T> aClass) {
        return rawConfig.getObject(s, aClass);
    }

    @Override
    @Nullable
    public <T> T getObject(@NotNull String s, @NotNull Class<T> aClass, @Nullable T t) {
        return rawConfig.getObject(s, aClass, t);
    }

    @Override
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String s, @NotNull Class<T> aClass) {
        return rawConfig.getSerializable(s, aClass);
    }

    @Override
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String s, @NotNull Class<T> aClass, @Nullable T t) {
        return rawConfig.getSerializable(s, aClass, t);
    }

    @Override
    @Nullable
    public Vector getVector(@NotNull String s) {
        return rawConfig.getVector(s);
    }

    @Override
    @Nullable
    public Vector getVector(@NotNull String s, @Nullable Vector vector) {
        return rawConfig.getVector(s, vector);
    }

    @Override
    public boolean isVector(@NotNull String s) {
        return rawConfig.isVector(s);
    }

    @Override
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String s) {
        return rawConfig.getOfflinePlayer(s);
    }

    @Override
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String s, @Nullable OfflinePlayer offlinePlayer) {
        return rawConfig.getOfflinePlayer(s, offlinePlayer);
    }

    @Override
    public boolean isOfflinePlayer(@NotNull String s) {
        return rawConfig.isOfflinePlayer(s);
    }

    @Override
    @Nullable
    public ItemStack getItemStack(@NotNull String s) {
        return rawConfig.getItemStack(s);
    }

    @Override
    @Nullable
    public ItemStack getItemStack(@NotNull String s, @Nullable ItemStack itemStack) {
        return rawConfig.getItemStack(s, itemStack);
    }

    @Override
    public boolean isItemStack(@NotNull String s) {
        return rawConfig.isItemStack(s);
    }

    @Override
    @Nullable
    public Color getColor(@NotNull String s) {
        return rawConfig.getColor(s);
    }

    @Override
    @Nullable
    public Color getColor(@NotNull String s, @Nullable Color color) {
        return rawConfig.getColor(s, color);
    }

    @Override
    public boolean isColor(@NotNull String s) {
        return rawConfig.isColor(s);
    }

    @Override
    @Nullable
    public Location getLocation(@NotNull String s) {
        return rawConfig.getLocation(s);
    }

    @Override
    @Nullable
    public Location getLocation(@NotNull String s, @Nullable Location location) {
        return rawConfig.getLocation(s, location);
    }

    @Override
    public boolean isLocation(@NotNull String s) {
        return rawConfig.isLocation(s);
    }

    @Override
    @Nullable
    public ConfigurationSection getConfigurationSection(@NotNull String s) {
        return rawConfig.getConfigurationSection(s);
    }

    @Override
    public boolean isConfigurationSection(@NotNull String s) {
        return rawConfig.isConfigurationSection(s);
    }

    @Override
    @Nullable
    public ConfigurationSection getDefaultSection() {
        return rawConfig.getDefaultSection();
    }

    @Override
    public void addDefault(@NotNull String s, @Nullable Object o) {
        rawConfig.addDefault(s, o);
    }
}
