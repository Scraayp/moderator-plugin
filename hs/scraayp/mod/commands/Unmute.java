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

public class Unmute implements CommandExecutor {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plsender = (Player) sender;
            if (args.length == 0) {
                plsender.sendMessage(ChatColor.RED+"Correct syntax: /unmute <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if(target == null){
                plsender.sendMessage(ChatColor.RED+"Correct syntax: /unmute <player> [reason]");
                return true;
            }
            if(plsender.hasPermission(config.getString("perm-unmute"))){
                // Time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String Time = now.format(format);

                String reason = StringUtils.join(args, ' ', 1, args.length);

                // Add it to config
                String banner = "Server";
                if(sender instanceof Player){
                    banner = sender.getName();
                }
                config.set("muted_players."+target.getDisplayName()+".muted", false);
                if(reason.length() == 0){
                    plsender.sendMessage(ChatColor.YELLOW+prefix+ChatColor.RED+target.getDisplayName()+" has been unmuted!");
                    log("unmuted",target,sender,reason);
                }else{
                    config.set("muted_players."+target.getDisplayName()+".reason", reason);
                    plsender.sendMessage(ChatColor.YELLOW+prefix+ChatColor.RED+target.getDisplayName()+" has been unmuted!");
                    log("unmuted",target,sender,reason);
                }
            }
        }else{
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED+"Correct syntax: /unmute <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if(target == null){
                sender.sendMessage(ChatColor.RED+"Correct syntax: /unmute <player> [reason]");
                return true;
            }
            // Time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String Time = now.format(format);

            String reason = StringUtils.join(args, ' ', 1, args.length);

            // Add it to config
            String banner = "Server";
            if(sender instanceof Player){
                banner = sender.getName();
            }
            config.set("muted_players."+target.getDisplayName()+".muted", false);
            if(reason.length() == 0){
                sender.sendMessage(ChatColor.YELLOW+prefix+ChatColor.RED+target.getDisplayName()+" has been unmuted!");
                log("unmuted",target,sender,reason);
            }else {
                config.set("muted_players."+target.getDisplayName()+".reason", reason);
                sender.sendMessage(ChatColor.YELLOW+prefix+ChatColor.RED+target.getDisplayName()+" has been unmuted!");
                log("unmuted",target,sender,reason);
            }
        }

        return true;
    }
}
