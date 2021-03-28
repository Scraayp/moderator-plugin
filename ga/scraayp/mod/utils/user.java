package ga.scraayp.mod.utils;

import ga.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.broadcast;
import static org.bukkit.Bukkit.broadcastMessage;


public class user {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");
}
