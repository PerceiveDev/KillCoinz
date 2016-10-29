/**
 * 
 */
package com.perceivedev.killcoinz;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.perceivedev.perceivecore.config.SerializationManager;

/**
 * @author Rayzr
 *
 */
public class PlayerManager {

    private List<PlayerData> players = new ArrayList<PlayerData>();

    private KillCoinz        plugin;

    /**
     * @param killCoinz
     */
    public PlayerManager(KillCoinz plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the config
     */
    public void load() {
        players.clear();

        YamlConfiguration config = plugin.getConfig("players.yml");
        config.getKeys(false).stream().filter(key -> config.isConfigurationSection(key)).map(key -> {
            return SerializationManager.deserialize(PlayerData.class, config.getConfigurationSection(key));
        }).filter(data -> {
            System.out.println("Filtering data: " + data);
            return data != null;
        }).forEach(data -> players.add(data));

        System.out.println("Players.size() = " + players.size());
    }

    /**
     * Save the config
     */
    public void save() {

        YamlConfiguration config = new YamlConfiguration();
        players.forEach(player -> config.set(player.getId().toString(), SerializationManager.serialize(player)));

        if (!plugin.saveConfig(config, "players.yml")) {
            plugin.getLogger().severe("Failed to save players.yml!");
        }

    }

    public PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    public PlayerData getPlayerData(UUID id) {
        return players.stream().filter(data -> data.getId() == id).findFirst().orElseGet(() -> {
            PlayerData data = new PlayerData(id);
            players.add(data);
            return data;
        });
    }

}
