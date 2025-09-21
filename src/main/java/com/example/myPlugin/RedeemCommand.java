package com.example.myPlugin;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class RedeemCommand implements CommandExecutor {

    // Remove the hardcoded REWARD_AMOUNT
    private static final Economy econ = MyPlugin.econ;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("redeem")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /redeem <code>");
                return true;
            }

            String code = args[0].toUpperCase();
            Player player = (Player) sender;

            if (!CodeManager.isValidCode(code)) {
                player.sendMessage(ChatColor.RED + "Invalid or already used code.");
                return true;
            }

            // Get reward amount from config.yml
            double rewardAmount = MyPlugin.instance.getConfig().getDouble("reward-amount", 1000.0);

            econ.depositPlayer(player, rewardAmount);
            CodeManager.removeCode(code);
            player.sendMessage(ChatColor.GREEN + String.format("Success! You received $%.2f", rewardAmount));
            return true;
        }

        if (label.equalsIgnoreCase("generatecode")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You must be an operator to generate codes.");
                return true;
            }

            String newCode = CodeManager.generateCode();
            sender.sendMessage(ChatColor.YELLOW + "Generated code: " + ChatColor.AQUA + newCode);
            return true;
        }

        return false;
    }
}
