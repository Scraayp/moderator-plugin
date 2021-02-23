package hs.scraayp.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plsender = (Player) sender;
            plsender.sendMessage(ChatColor.YELLOW+"           Moderator");
            plsender.sendMessage(ChatColor.GREEN+"============================================");
            plsender.sendMessage(ChatColor.AQUA+"/mod | mod");
            plsender.sendMessage(ChatColor.AQUA+"/kick <player> [reason] | mod.kick");
        }else{
            sender.sendMessage(ChatColor.YELLOW+"           Moderator");
            sender.sendMessage(ChatColor.GREEN+"============================================");
            sender.sendMessage(ChatColor.AQUA+"/mod | mod");
            sender.sendMessage(ChatColor.AQUA+"/kick <player> [reason] | mod.kick");
        }

        return true;
    }
}
