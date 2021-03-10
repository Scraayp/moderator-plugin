package ga.scraayp.mod.commands;

import ga.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plsender = (Player) sender;
            if (args.length == 0) {
                plsender.sendMessage(ChatColor.RED + "Correct syntax: /unban <player>");
                return true;
            }
            String target = args[0];
            if (plsender.hasPermission(config.getString("perm-unban"))) {
                if (config.getString("banned_players." + target + ".banned") == null) {
                    plsender.sendMessage(ChatColor.RED + "Correct syntax: /unban <player>");
                    return true;
                }
                config.set("banned_players." + target + ".banned", false);
                plsender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target + " has been unbanned!");
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Correct syntax: /unban <player>");
                return true;
            }
            String target = args[0];
            if (sender.hasPermission("mod.unban")) {
                if (config.getString("banned_players." + target + ".banned") == null) {
                    sender.sendMessage(ChatColor.RED + "Correct syntax: /unban <player>");
                    return true;
                }
                config.set("banned_players." + target + ".banned", false);
                sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + target + " has been unbanned!");
            }
        }

        return true;
    }
}
