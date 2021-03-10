package hs.scraayp.mod;

import hs.scraayp.mod.commands.*;
import hs.scraayp.mod.events.Chat;
import hs.scraayp.mod.events.PlayerJoin;
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
        getCommand("rank").setExecutor(new Rank());
        getLogger().info("Moderation has been enabled!");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Moderation has been disabled!");
    }
}