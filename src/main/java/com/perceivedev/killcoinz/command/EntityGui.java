/**
 * 
 */
package com.perceivedev.killcoinz.command;

import org.bukkit.entity.EntityType;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.perceivecore.gui.GUI;

/**
 * @author Rayzr
 *
 */
public class EntityGui extends GUI {

    private KillCoinz  plugin;
    private EntityType type;

    /**
     * @param plugin
     * @param type
     */
    public EntityGui(KillCoinz plugin, EntityType type) {
        super(plugin.tr("gui.entity.name", type.getName()), 3);
        this.plugin = plugin;
        this.type = type;

        init();
    }

    private void init() {
    }

}
