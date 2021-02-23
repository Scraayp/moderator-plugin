package hs.scraayp.mod;

import hs.scraayp.mod.commands.Help;
import hs.scraayp.mod.commands.Kick;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Mod extends JavaPlugin {
    public FileConfiguration config = this.getConfig();
    @Override
    public void onEnable(){
        getCommand("modhelp").setExecutor(new Help());
        getCommand("kick").setExecutor(new Kick());
        getLogger().info("Moderation has been enabled!");
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        getLogger().info("Moderation has been disabled!");
    }
}