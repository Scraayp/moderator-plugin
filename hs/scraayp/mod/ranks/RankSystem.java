package hs.scraayp.mod.ranks;

import hs.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class RankSystem {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");

    public static String getRank(Player p) {
        String a = config.getString(p.getUniqueId() + ".rank");
        if (a == null) {
            return "null";
        }
        return a;
    }

    public static boolean hasRank(Player p) {
        String has = config.getString(p.getUniqueId() + ".rank");
        if (has == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean setRank(Player p, String rank) {
        config.set(p.getUniqueId() + ".rank", rank);
        return true;
    }

}
