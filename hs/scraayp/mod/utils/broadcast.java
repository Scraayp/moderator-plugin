package hs.scraayp.mod.utils;

import hs.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.broadcast;
import static org.bukkit.Bukkit.broadcastMessage;


public class broadcast {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");
    public static boolean log(String method, Player target, CommandSender mod, String reason){
        if(config.getBoolean("send-mod-action-in-global-chat") == true){
            if(reason.length() == 0){
                if(mod instanceof Player){
                    Player modsender = (Player) mod;
                    broadcastMessage(ChatColor.RED+prefix+" "+target.getDisplayName()+" was "+method+" by "+modsender.getDisplayName());
                }else{
                    broadcastMessage(ChatColor.RED+prefix+" "+target.getDisplayName()+" was "+method+" by "+"console");
                }
            }else {
                if(mod instanceof Player){
                    Player modsender = (Player) mod;
                    broadcastMessage(ChatColor.RED+prefix+" "+target.getDisplayName()+" was "+method+" for "+reason+" by "+modsender.getDisplayName());
                }else{
                    broadcastMessage(ChatColor.RED+prefix+" "+target.getDisplayName()+" was "+method+" for "+reason+" by "+"console");
                }
            }
        }
        if(config.getBoolean("send-mod-action-to-staff") == true) {
            if (reason.length() == 0) {
                if (mod instanceof Player) {
                    Player modsender = (Player) mod;
                    broadcast(ChatColor.RED + prefix + " " + target.getDisplayName() + " was " + method + " by " + modsender.getDisplayName(), config.getString("permission-to-see-staff-mod-logs"));
                } else {
                    broadcast(ChatColor.RED + prefix + " " + target.getDisplayName() + " was " + method + " by " + "console", config.getString("permission-to-see-staff-mod-logs"));
                }
            } else {
                if (mod instanceof Player) {
                    Player modsender = (Player) mod;
                    broadcast(ChatColor.RED + prefix + " " + target.getDisplayName() + " was " + method + " for " + reason + " by " + modsender.getDisplayName(), config.getString("permission-to-see-staff-mod-logs"));
                } else {
                    broadcast(ChatColor.RED + prefix + " " + target.getDisplayName() + " was " + method + " for " + reason + " by " + "console", config.getString("permission-to-see-staff-mod-logs"));
                }
            }
        }
        return true;
    }
}
