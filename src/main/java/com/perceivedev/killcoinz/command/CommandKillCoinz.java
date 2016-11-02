/**
 * 
 */
package com.perceivedev.killcoinz.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import com.perceivedev.killcoinz.KillCoinz;
import com.perceivedev.perceivecore.command.CommandResult;
import com.perceivedev.perceivecore.command.CommandSenderType;
import com.perceivedev.perceivecore.command.TranslatedCommandNode;

/**
 * @author Rayzr
 *
 */
public class CommandKillCoinz extends TranslatedCommandNode {

    private KillCoinz plugin;

    public CommandKillCoinz(KillCoinz plugin) {
        super(new Permission("KillCoinz.user"), "killcoinz", plugin.getLanguage(), CommandSenderType.ALL);
        this.plugin = plugin;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> wholeChat, int relativeIndex) {
        return relativeIndex == 0 ? Arrays.asList("reload", "save") : Collections.emptyList();
    }

    @Override
    protected CommandResult executeGeneral(CommandSender sender, String... args) {
        if (args.length < 1) {
            return CommandResult.SEND_USAGE;
        }

        String arg = args[0].toLowerCase();
        args = Arrays.copyOfRange(args, 1, args.length);

        // Player specific commands
        if (arg.equals("config")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.tr("command.playeronly", arg));
                return CommandResult.SUCCESSFULLY_INVOKED;
            }
            Player player = (Player) sender;
            new ConfigGui(plugin).open(player);
        }

        return CommandResult.SUCCESSFULLY_INVOKED;
    }

}
