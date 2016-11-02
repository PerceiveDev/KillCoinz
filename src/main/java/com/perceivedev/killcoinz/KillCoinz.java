package com.perceivedev.killcoinz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceivedev.killcoinz.command.CommandKillCoinz;
import com.perceivedev.killcoinz.events.EntityListener;
import com.perceivedev.perceivecore.command.CommandTree;
import com.perceivedev.perceivecore.command.DefaultCommandExecutor;
import com.perceivedev.perceivecore.command.DefaultTabCompleter;
import com.perceivedev.perceivecore.language.I18N;

public class KillCoinz extends JavaPlugin {

    private static KillCoinz instance;

    private I18N             language;

    private PlayerManager    playerManager;
    private MobRegistry      mobRegistry;

    @Override
    public void onEnable() {
        instance = this;

        setupLanguage();

        playerManager = new PlayerManager(this);
        mobRegistry = new MobRegistry(this);

        load();

        new BukkitRunnable() {
            @Override
            public void run() {
                save();
            }
        }.runTaskTimer(this, 120 * 20, 120 * 20);

        setupCommands();

        getServer().getPluginManager().registerEvents(new EntityListener(this), this);

    }

    @Override
    public void onDisable() {
        playerManager.save();

        instance = null;
    }

    private void setupLanguage() {
        Path output = getDataFolder().toPath().resolve("language");

        if (Files.notExists(output)) {
            try {
                Files.createDirectories(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        I18N.copyDefaultFiles("language", output, false, getFile());

        language = new I18N(this, "language");
    }

    private void setupCommands() {

        CommandTree tree = new CommandTree();
        CommandExecutor executor = new DefaultCommandExecutor(tree, language);
        TabCompleter tabCompleter = new DefaultTabCompleter(tree);

        CommandKillCoinz command = new CommandKillCoinz(this);

        tree.addTopLevelChildAndRegister(command, executor, tabCompleter, this, "coinz", "kc", "kcoins");

        tree.attachHelp(command, "KillCoinz.help", language);

    }

    public void load() {

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        playerManager.load();
        mobRegistry.load();
    }

    public void save() {
        playerManager.save();
        mobRegistry.save();
    }

    /**
     * 
     */
    public void reload() {
        playerManager.save();
        load();
    }

    public String versionText() {
        return getName() + " v" + getDescription().getVersion();
    }

    /**
     * @param string
     * @return
     */
    public YamlConfiguration getConfig(String path) {
        File file = getFile(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignore) {
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * @param path
     * @return
     */
    public File getFile(String path) {
        return new File(getDataFolder(), path.replace("/", File.pathSeparator));
    }

    /**
     * @param config
     * @param string
     * @return If the config was successfully saved
     */
    public boolean saveConfig(YamlConfiguration config, String path) {
        try {
            File file = getFile(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * @return
     */
    public MobRegistry getMobRegistry() {
        return mobRegistry;
    }

    public I18N getLanguage() {
        return language;
    }

    public String tr(String key, Object... objects) {
        return language.tr(key, objects);
    }

    public static KillCoinz getInstance() {
        return instance;
    }

}
