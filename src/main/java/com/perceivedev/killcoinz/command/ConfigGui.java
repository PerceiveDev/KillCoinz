/**
 * 
 */
package com.perceivedev.killcoinz.command;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.perceivecore.gui.Gui;
import com.perceivedev.perceivecore.gui.base.Component;
import com.perceivedev.perceivecore.gui.components.panes.FlowPane;
import com.perceivedev.perceivecore.gui.components.simple.SimpleButton;
import com.perceivedev.perceivecore.util.ItemFactory;

/**
 * @author Rayzr
 *
 */
public class ConfigGui extends Gui {

    private KillCoinz plugin;

    public ConfigGui(KillCoinz plugin) {
        super(plugin.tr("gui.config.name"), 6, new FlowPane(9, 6));
        this.plugin = plugin;

        init();
    }

    private void init() {
        SimpleButton reload = new SimpleButton("&c&nReload Config", e -> {
            plugin.reload();
            e.getPlayer().sendMessage(plugin.tr("config.reloaded"));
        });
        reload.setDisplayType(c -> ItemFactory.builder(Material.REDSTONE_BLOCK));
        addComponent(reload);

        SimpleButton save = new SimpleButton("&c&nSave Config", e -> {
            plugin.save();
            e.getPlayer().sendMessage(plugin.tr("config.saved"));
        });
        save.setDisplayType(c -> ItemFactory.builder(Material.REDSTONE_BLOCK));
        addComponent(save);

        for (EntityType type : EntityType.values()) {
            SimpleButton entityButton = new SimpleButton("&a&l" + type.getName(), e -> {
                new EntityGui(plugin, type).open(e.getPlayer());
            });
            addComponent(entityButton);
        }

    }

    private void addComponent(Component component) {
        getRootAsFreeform().addComponent(component);
    }

}
