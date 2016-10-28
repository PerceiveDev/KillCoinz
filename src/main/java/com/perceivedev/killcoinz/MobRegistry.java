/**
 * 
 */
package com.perceivedev.killcoinz;

import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

/**
 * @author Rayzr
 *
 */
public class MobRegistry {

    private HashMap<EntityType, Long> values = new HashMap<>();
    private KillCoinz                 plugin;

    /**
     * @param killCoinz
     */
    public MobRegistry(KillCoinz plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the config
     */
    @SuppressWarnings("deprecation")
    public void load() {
        values.clear();

        YamlConfiguration config = plugin.getConfig("mobs.yml");
        config.getKeys(false).stream().filter(key -> config.isLong(key) || config.isInt(key)).filter(key -> {
            return EntityType.fromName(key) != null;
        }).forEach(key -> {
            values.put(EntityType.fromName(key), config.getLong(key));
        });
    }

    /**
     * Saves the config
     */
    @SuppressWarnings("deprecation")
    public void save() {
        YamlConfiguration config = new YamlConfiguration();
        values.forEach((type, value) -> {
            config.set(type.getName(), value);
        });

        if (!plugin.saveConfig(config, "mobs.yml")) {
            plugin.getLogger().severe("Failed to save mobs.yml!");
        }
    }

    /**
     * @param type
     * @return
     */
    public long getWorth(EntityType type) {
        return values.getOrDefault(type, 0L);
    }

}
