package hs.scraayp.mod.commands;

import hs.scraayp.mod.Mod;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static hs.scraayp.mod.utils.broadcast.log;
import static org.bukkit.Bukkit.getServer;

public class Ban implements CommandExecutor {
    private Mod plugin = Mod.getPlugin(Mod.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plsender = (Player) sender;
            if (args.length == 0) {
                plsender.sendMessage(ChatColor.RED+"Correct syntax: /ban <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if(target == null){
                plsender.sendMessage(ChatColor.RED+"Correct syntax: /ban <player> [reason]");
                return true;
            }
            if(plsender.hasPermission("mod.ban")){
                // Time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String Time = now.format(format);

                String reason = StringUtils.join(args, ' ', 1, args.length);
                if(reason.length() == 0){
                    target.kickPlayer(ChatColor.YELLOW+"Moderation [MOD]\n\n"+ChatColor.RED+"You have been banned by an moderator!\n\n"+ChatColor.AQUA+"Time of ban: \n"+ChatColor.WHITE+Time);
                    plsender.sendMessage(ChatColor.RED+"[MOD]"+target.getDisplayName()+ChatColor.RED+" has been banned!");
                    log("banned",target,sender,reason);
                }else{
                    target.kickPlayer(ChatColor.YELLOW+"Moderation [MOD]\n\n"+ChatColor.RED+"You have been banned by an moderator!\n\n"+ChatColor.AQUA+"Reason: \n"+ChatColor.WHITE+reason+ChatColor.AQUA+"\nTime of kick: "+ChatColor.WHITE+Time);
                    plsender.sendMessage(ChatColor.RED+"[MOD]"+target.getDisplayName()+ChatColor.RED+" has been banned for: "+ChatColor.WHITE+reason);
                    log("banned",target,sender,reason);
                }
            }
        }else{
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED+"Correct syntax: /ban <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if(target == null){
                sender.sendMessage(ChatColor.RED+"Correct syntax: /ban <player> [reason]");
                return true;
            }
            // Time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String Time = now.format(format);

            String reason = StringUtils.join(args, ' ', 1, args.length);
            if(reason.length() == 0){
                target.kickPlayer(ChatColor.YELLOW+"Moderation [MOD]\n\n"+ChatColor.RED+"You have been banned by an moderator!\n\n"+ChatColor.AQUA+"Length: \n"+ChatColor.WHITE+"permanent\n"+ChatColor.AQUA+"Time of ban: "+ChatColor.WHITE+Time);
                sender.sendMessage(ChatColor.RED+"[MOD] "+target.getDisplayName()+" has been banned!");
                log("banned",target,sender,reason);
            }else {
                target.kickPlayer(ChatColor.YELLOW+"Moderation [MOD]\n\n"+ChatColor.RED+"You have been banned by an moderator!\n\n"+ChatColor.AQUA+"Reason: \n"+ChatColor.WHITE+reason+ChatColor.AQUA+"Length: "+ChatColor.WHITE+"permanent"+ChatColor.AQUA+"\nTime of kick: "+ChatColor.WHITE+Time);
                sender.sendMessage(ChatColor.RED+"[MOD] "+target.getDisplayName()+" has been banned!");
                log("banned",target,sender,reason);
            }
        }

        return true;
    }
}