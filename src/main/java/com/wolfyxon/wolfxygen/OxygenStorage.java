package com.wolfyxon.wolfxygen;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OxygenStorage {
    Wolfxygen plugin;
    ConfigMgr config;
    OxygenMgr oxygenMgr;

    File file;
    String filename = "players.yml";

    FileConfiguration storage = new YamlConfiguration();

    public OxygenStorage(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.config;
        oxygenMgr = plugin.oxygenMgr;
        file = new File(plugin.getDataFolder(),filename);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            plugin.saveResource(filename,false);
        }
        try {
            storage.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            plugin.getLogger().info(ConfigMgr.format("&4Loading "+filename+" failed"));
            e.printStackTrace();
        }
    }
    public double getOxygen(String strUUID){
        strUUID = strUUID.toLowerCase();
        if(!storage.contains(strUUID)) return plugin.oxygenMgr.maxOxygen;
        return storage.getDouble(strUUID);
    }
    public double getOxygen(UUID uuid){ return getOxygen(uuid.toString()); }
    public double getOxygen(Player player){ return getOxygen(player.getUniqueId()); }

    public void setOxygen(String strUUID, double oxygen){
        storage.set(strUUID.toLowerCase(), oxygen);
        saveStorage();
    }
    public void setOxygen(UUID uuid, double oxygen){ setOxygen(uuid.toString(), oxygen); }
    public void setOxygen(Player player, double oxygen){ setOxygen(player.getUniqueId(), oxygen); }

    public void saveStorage(){
        try {
            storage.save(file);
        } catch (IOException e){
            plugin.getLogger().info(ConfigMgr.format("&4Saving "+filename+" failed"));
            e.printStackTrace();
        }

    }

    public void save(Player player){ setOxygen(player, oxygenMgr.getOxygen(player) ); }
    public void load(Player player){ oxygenMgr.setOxygen(player, getOxygen(player) ); }

    public void saveAll(){
        for(Player plr : plugin.getServer().getOnlinePlayers()){
            save(plr);
        }
    }
    public void loadAll(){
        for(Player plr : plugin.getServer().getOnlinePlayers()){
            load(plr);
        }
    }

}
