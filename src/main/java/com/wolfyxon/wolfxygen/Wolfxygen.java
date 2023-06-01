package com.wolfyxon.wolfxygen;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wolfxygen extends JavaPlugin {

    public OxygenMgr oxygenMgr = new OxygenMgr(this);
    public ConfigMgr config = new ConfigMgr(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        getLogger().info("Enabled");
    }
    public void loadConfig(){
        oxygenMgr.applyConfig();
        oxygenMgr.start();
        getLogger().info("Config has been loaded");
    }


    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }
}
