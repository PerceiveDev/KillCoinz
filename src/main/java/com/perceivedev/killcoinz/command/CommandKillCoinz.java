/**
 * 
 */
package com.perceivedev.killcoinz.command;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perceivedev.killcoinz.KillCoinz;

/**
 * @author Rayzr
 *
 */
public class CommandKillCoinz implements CommandExecutor {

    private KillCoinz plugin;

    public CommandKillCoinz(KillCoinz plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(plugin.tr("commands.killcoinz.help"));
            return true;
        }

        String arg = args[0].toLowerCase();
        args = Arrays.copyOfRange(args, 1, args.length);

        // Player specific commands
        if (arg.equals("config")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.tr("command.playeronly", arg));
                return true;
            }
            Player player = (Player) sender;
            new ConfigGui(plugin).open(player);
        }

        return true;
    }

}
