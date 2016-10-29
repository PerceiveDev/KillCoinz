/**
 * 
 */
package com.perceivedev.killcoinz.command;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.perceivecore.gui.DisplayType;
import com.perceivedev.perceivecore.gui.GUI;
import com.perceivedev.perceivecore.gui.component.Button;

/**
 * @author Rayzr
 *
 */
public class ConfigGui extends GUI {

    private KillCoinz plugin;

    public ConfigGui(KillCoinz plugin) {
        super(plugin.tr("gui.config.name"), 6);
        this.plugin = plugin;

        init();
    }

    private void init() {
        Button reload = new Button("&c&nReload Config", e -> {
            plugin.reload();
            e.getPlayer().sendMessage(plugin.tr("config.reloaded"));
        });
        reload.setDisplayType(DisplayType.custom(Material.REDSTONE_BLOCK, 0, 1));
        addComponent(reload, 0, 5);

        Button save = new Button("&c&nSave Config", e -> {
            plugin.save();
            e.getPlayer().sendMessage(plugin.tr("config.saved"));
        });
        save.setDisplayType(DisplayType.custom(Material.REDSTONE_BLOCK, 0, 1));
        addComponent(save, 1, 5);

        int i = 0;
        for (EntityType type : EntityType.values()) {
            Button entityButton = new Button("&a&l" + type.getName(), e -> {
                new EntityGui(plugin, type).open(e.getPlayer());
            });
            addComponent(entityButton, i % 9, i / 9);
            i++;
        }

    }

}
