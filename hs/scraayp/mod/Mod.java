package hs.scraayp.mod;

import hs.scraayp.mod.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


public class Mod extends JavaPlugin {
    public static Mod plugin;
    @Override
    public void onEnable(){
        plugin = this;
        getCommand("modhelp").setExecutor(new Help());
        getCommand("kick").setExecutor(new Kick());
        getCommand("ban").setExecutor(new Ban());
        getCommand("scraayp").setExecutor(new Scraayp());
        getLogger().info("Moderation has been enabled!");
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        getLogger().info("Moderation has been disabled!");
    }
}