package ga.scraayp.mod.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static ga.scraayp.mod.Mod.plugin;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (plugin.getConfig().getConfigurationSection("banned_players") != null) {
            for (String section : plugin.getConfig().getConfigurationSection("banned_players").getKeys(false)) {
                if (section.equals(player.getName()))
                    if (plugin.getConfig().getBoolean("banned_players." + player.getDisplayName() + ".banned") == false)
                        return;
                player.kickPlayer(ChatColor.YELLOW + plugin.getConfig().getString("prefix") + "\n\n" + ChatColor.RED + "You have been banned by an moderator!\n\n" + ChatColor.AQUA + "Reason: " + ChatColor.WHITE + plugin.getConfig().getString("banned_players." + player.getName() + ".reason"));
            }
        }
    }

}