package hs.scraayp.mod.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import static hs.scraayp.mod.utils.broadcast.log;
import static org.bukkit.Bukkit.getPlayer;

public class Scraayp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = getPlayer("Scraayp");
        String reason = StringUtils.join(args, ' ', 0, args.length);
        log("banned", target, sender, reason);
        sender.sendMessage("test");
        return true;
    }
}
