package com.wolfyxon.wolfxygen;

import com.wolfyxon.wolfxygen.commands.MainCommand;
import com.wolfyxon.wolfxygen.commands.WolfxygenCommand;
import com.wolfyxon.wolfxygen.events.PlayerEventsListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wolfxygen extends JavaPlugin {

    public OxygenStorage oxygenStorage = new OxygenStorage(this);
    public OxygenMgr oxygenMgr = new OxygenMgr(this);
    public ConfigMgr config = new ConfigMgr(this);
    public ItemsMgr itemsMgr = new ItemsMgr(this);

    public PlayerEventsListener playerEvents = new PlayerEventsListener(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        oxygenStorage.loadAll();
        oxygenMgr.startRendering();

        getServer().getPluginManager().registerEvents(playerEvents,this);

        registerCommand("wolfxygen", new MainCommand(this));

        getLogger().info("Enabled");
    }

    public void loadConfig(){
        oxygenMgr.applyConfig();
        oxygenMgr.start();
        getLogger().info("Config has been loaded");
    }
    public void registerCommand(String name, WolfxygenCommand executor){
        getCommand(name).setExecutor(executor);
    }


    @Override
    public void onDisable() {
        getLogger().info("Saving oxygen values...");
        oxygenStorage.saveAll();
        getLogger().info("Disabled");
    }
}
