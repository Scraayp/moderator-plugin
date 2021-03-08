package hs.scraayp.mod.commands;

import hs.scraayp.mod.Mod;
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

public class Kick implements CommandExecutor {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plsender = (Player) sender;
            if (args.length == 0) {
                plsender.sendMessage(ChatColor.RED + "Correct syntax: /kick <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if (target == null) {
                plsender.sendMessage(ChatColor.RED + "Correct syntax: /kick <player> [reason]");
                return true;
            }
            if (plsender.hasPermission(config.getString("perm-kick"))) {
                // Time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String Time = now.format(format);

                String reason = StringUtils.join(args, ' ', 1, args.length);
                if (reason.length() == 0) {
                    target.kickPlayer(ChatColor.YELLOW + prefix + "\n\n" + ChatColor.RED + "You have been kicked by an moderator!\n\n" + ChatColor.AQUA + "Time of kick: " + ChatColor.WHITE + Time);
                    plsender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been kicked!");
                    log("kicked", target, sender, reason);
                } else {
                    target.kickPlayer(ChatColor.YELLOW + prefix + "\n\n" + ChatColor.RED + "You have been kicked by an moderator!\n\n" + ChatColor.AQUA + "Reason: " + ChatColor.WHITE + reason + ChatColor.AQUA + "\nTime of kick: " + ChatColor.WHITE + Time);
                    plsender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been kicked!");
                    log("kicked", target, sender, reason);
                }
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Correct syntax: /kick <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Correct syntax: /kick <player> [reason]");
                return true;
            }
            // Time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String Time = now.format(format);

            String reason = StringUtils.join(args, ' ', 1, args.length);
            if (reason.length() == 0) {
                target.kickPlayer(ChatColor.YELLOW + prefix + "\n\n" + ChatColor.RED + "You have been kicked by an moderator!\n\n" + ChatColor.AQUA + "Time of kick: " + ChatColor.WHITE + Time);
                sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been kicked!");
                log("kicked", target, sender, reason);
            } else {
                target.kickPlayer(ChatColor.YELLOW + prefix + "\n\n" + ChatColor.RED + "You have been kicked by an moderator!\n\n" + ChatColor.AQUA + "Reason: " + ChatColor.WHITE + reason + ChatColor.AQUA + "\nTime of kick: " + ChatColor.WHITE + Time);
                sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been kicked!");
                log("kicked", target, sender, reason);
            }
        }

        return true;
    }
}
