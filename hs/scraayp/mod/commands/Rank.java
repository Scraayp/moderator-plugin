package hs.scraayp.mod.commands;

import hs.scraayp.mod.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static hs.scraayp.mod.ranks.RankSystem.setRank;
import static org.bukkit.Bukkit.getPlayer;

public class Rank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Mod.plugin.getConfig();
        String prefix = config.getString("prefix");
        if (sender instanceof Player) {
            if (sender.hasPermission(config.getString("perm-rank"))) {

            } else {
                sender.sendMessage(ChatColor.RED + "You're not allowed to use this command!");
                return false;
            }
        }
        Player target = getPlayer(args[0]);
        if (target == null) return false;
        String rank = args[1];
        if (rank == null) return false;
        setRank(target, rank);
//        TODO: Make rank color popssibility!
//        TODO: Timebased ranks!
        sender.sendMessage(ChatColor.YELLOW + prefix + ChatColor.RED + " Rank for " + ChatColor.WHITE + target.getDisplayName() + ChatColor.RED + " has been set to: " + ChatColor.AQUA + rank);
        return true;
    }
}
