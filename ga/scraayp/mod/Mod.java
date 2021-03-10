package ga.scraayp.mod;

import ga.scraayp.mod.commands.*;
import ga.scraayp.mod.events.Chat;
import ga.scraayp.mod.events.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;


public class Mod extends JavaPlugin {
    public static Mod plugin;

    @Override
    public void onEnable() {

        plugin = this;
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getCommand("kick").setExecutor(new Kick());
        getCommand("ban").setExecutor(new Ban());
        getCommand("unban").setExecutor(new Unban());
        getCommand("mute").setExecutor(new Mute());
        getCommand("unmute").setExecutor(new Unmute());
        getLogger().info("Moderation has been enabled!");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Moderation has been disabled!");
    }
}