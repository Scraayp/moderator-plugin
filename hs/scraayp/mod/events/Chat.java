package hs.scraayp.mod.events;

import hs.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static hs.scraayp.mod.Mod.plugin;
import static hs.scraayp.mod.ranks.RankSystem.*;

public class Chat implements Listener {
    static FileConfiguration config = Mod.plugin.getConfig();
    static String prefix = config.getString("prefix");

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (hasRank(event.getPlayer())) {
            String rank = getRank(event.getPlayer());
            String chatFormat = ChatColor.RED + "[" + rank + "]" + " %1$s" + ":" + ChatColor.RESET + " %2$s";
            event.setFormat(chatFormat);
        }
        if (plugin.getConfig().getConfigurationSection("muted_players") != null) {
            for (String section : plugin.getConfig().getConfigurationSection("muted_players").getKeys(false)) {
                if (section.equals(event.getPlayer().getName())) {
                    if (plugin.getConfig().getBoolean("muted_players." + event.getPlayer().getDisplayName() + ".muted") == false)
                        return;
                    event.setCancelled(true);
                }
            }
        }
        if (plugin.getConfig().getBoolean("chat_lockdown") == true) {
            if (event.getPlayer().hasPermission(config.getString("perm-chatlock-bypass"))) return;
            event.setCancelled(true);
        }
    }
}
