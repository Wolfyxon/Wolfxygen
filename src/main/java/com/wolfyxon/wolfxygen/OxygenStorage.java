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

    File file;
    String filename = "players.yml";

    FileConfiguration storage = new YamlConfiguration();

    public OxygenStorage(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.config;
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
    public double getOxygen(String strUUID){ return storage.getDouble(strUUID.toLowerCase()); }
    public double getOxygen(UUID uuid){ return getOxygen(uuid.toString()); }
    public double getOxygen(Player player){ return getOxygen(player.getUniqueId()); }

    public void setOxygen(String strUUID, double oxygen){ storage.set(strUUID.toLowerCase(), oxygen); }
    public void setOxygen(UUID uuid, double oxygen){ setOxygen(uuid.toString(), oxygen); }
    public void setOxygen(Player player, double oxygen){ setOxygen(player.getUniqueId(), oxygen); }

}
