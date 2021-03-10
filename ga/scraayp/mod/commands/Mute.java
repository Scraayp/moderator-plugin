package ga.scraayp.mod.commands;

import ga.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ga.scraayp.mod.utils.broadcast.log;
import static org.bukkit.Bukkit.getServer;

public class Mute implements CommandExecutor {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plsender = (Player) sender;
            if (args.length == 0) {
                plsender.sendMessage(ChatColor.RED + "Correct syntax: /mute <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if (target == null) {
                plsender.sendMessage(ChatColor.RED + "Correct syntax: /mute <player> [reason]");
                return true;
            }
            if (plsender.hasPermission(config.getString("perm-mute"))) {
                // Time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String Time = now.format(format);

                String reason = StringUtils.join(args, ' ', 1, args.length);

                // Add it to config
                String banner = "Server";
                if (sender instanceof Player) {
                    banner = sender.getName();
                }
                Number times = 0;
                if (config.getInt("muted_players." + target.getDisplayName() + ".times") > 0) {
                    times = config.getInt("muted_players." + target.getDisplayName() + ".times");
                }
                config.set("muted_players." + target.getDisplayName() + ".mod", banner);
                config.set("muted_players." + target.getDisplayName() + ".muted", true);
                config.set("muted_players." + target.getDisplayName() + ".times", times);
                if (reason.length() == 0) {
                    plsender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been muted!");
                    log("muted", target, sender, reason);
                } else {
                    config.set("muted_players." + target.getDisplayName() + ".reason", reason);
                    plsender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been muted!");
                    log("muted", target, sender, reason);
                }
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Correct syntax: /mute <player> [reason]");
                return true;
            }
            Player target = getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Correct syntax: /mute <player> [reason]");
                return true;
            }
            // Time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String Time = now.format(format);

            String reason = StringUtils.join(args, ' ', 1, args.length);

            // Add it to config
            String banner = "Server";
            if (sender instanceof Player) {
                banner = sender.getName();
            }
            Number times = 0;
            if (config.getInt("muted_players." + target.getDisplayName() + ".times") > 0) {
                times = config.getInt("muted_players." + target.getDisplayName() + ".times");
            }
            config.set("muted_players." + target.getDisplayName() + ".mod", banner);
            config.set("muted_players." + target.getDisplayName() + ".muted", true);
            config.set("muted_players." + target.getDisplayName() + ".times", times);
            if (reason.length() == 0) {
                sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been muted!");
                log("muted", target, sender, reason);
            } else {
                config.set("muted_players." + target.getDisplayName() + ".reason", reason);
                sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target.getDisplayName() + " has been muted!");
                log("muted", target, sender, reason);
            }
        }

        return true;
    }
}
