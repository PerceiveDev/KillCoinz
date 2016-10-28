/**
 * 
 */
package com.perceivedev.killcoinz.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.killcoinz.PlayerData;

/**
 * @author Rayzr
 *
 */
public class EntityListener implements Listener {

    private static boolean USE_GLOW;
    static {
        try {
            USE_GLOW = Entity.class.getMethod("setGlowing", Boolean.TYPE) != null;
        } catch (NoSuchMethodException e) {
            USE_GLOW = false;
        }
    }

    private KillCoinz plugin;

    public EntityListener(KillCoinz plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if (e.getEntity().getKiller() == null) {
            // It was not a player
            return;
        }

        long value = plugin.getMobRegistry().getWorth(e.getEntityType());

        if (value < 1) {
            return;
        }

        Player player = e.getEntity().getKiller();
        PlayerData data = plugin.getPlayerManager().getPlayerData(player);

        Item item = e.getEntity().getWorld().dropItem(e.getEntity().getLocation().add(0, 0.5, 0), new ItemStack(Material.GOLD_NUGGET, 0));
        item.setCustomName(ChatColor.YELLOW + "KillCoinz: " + ChatColor.GOLD + value);
        item.setTicksLived(5960);
        item.setInvulnerable(true);
        item.setVelocity(new Vector(0, 0.2, 0));
        item.setCustomNameVisible(true);

        if (USE_GLOW) {
            item.setGlowing(true);
        }

        data.addCoins(value);
    }

}
