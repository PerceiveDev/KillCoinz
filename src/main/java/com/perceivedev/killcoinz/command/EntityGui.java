/**
 * 
 */
package com.perceivedev.killcoinz.command;

import org.bukkit.entity.EntityType;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.perceivecore.gui.Gui;
import com.perceivedev.perceivecore.gui.components.implementation.pane.FlowPane;

/**
 * @author Rayzr
 *
 */
public class EntityGui extends Gui {

    private KillCoinz  plugin;
    private EntityType type;

    /**
     * @param plugin
     * @param type
     */
    public EntityGui(KillCoinz plugin, EntityType type) {
        super(plugin.tr("gui.entity.name", type.getName()), 3, new FlowPane(9, 6));
        this.plugin = plugin;
        this.type = type;

        init();
    }

    private void init() {
    }

}
