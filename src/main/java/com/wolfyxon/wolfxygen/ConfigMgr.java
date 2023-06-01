package com.wolfyxon.wolfxygen;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMgr {
    Wolfxygen plugin;
    FileConfiguration config;
    public ConfigMgr(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public Map<String,Object> getMap(String path){
        ConfigurationSection section = config.getConfigurationSection(path);
        Map<String,Object> map = new HashMap<>();
        for(String key : section.getKeys(false)){
            map.put(key,section.get(key));
        }
        return map;
    }
    public Map<String,String> getStringMap(String path){
        ConfigurationSection section = config.getConfigurationSection(path);
        Map<String,String> map = new HashMap<>();
        for(String key : section.getKeys(false)){
            map.put(key,section.getString(key));
        }
        return map;
    }
    public Map<Integer,String> getIntStringMap(String path){
        ConfigurationSection section = config.getConfigurationSection(path);
        Map<Integer,String> map = new HashMap<>();
        for(String key : section.getKeys(false)){
            map.put(Integer.valueOf(key),section.getString(key));
        }
        return map;
    }


    public List<String> getAffectedWorlds(){ return config.getStringList("affectedWorlds"); }
    public List<String> getAffectedGamemodes(){ return config.getStringList("affectedGamemodes"); }

    public boolean getRefillInUnaffectedGamemodes(){ return config.getBoolean("refillInUnaffectedGamemodes"); }
    public boolean getRefillInUnaffectedWorlds(){ return config.getBoolean("refillInUnaffectedWorlds"); }

    public double getMaxOxygen(){ return config.getDouble("numbers.maxOxygen"); }
    public double getSubtractionAmount(){ return config.getDouble("numbers.subtractionAmount"); }
    public double getRefillingAmount(){ return config.getDouble("numbers.refillingAmount"); }
    public long getIntervalTicks(){ return config.getLong("numbers.intervalTicks"); }
    public double getDamage(){ return config.getDouble("numbers.damage"); }

    public String getDisplayMode(){ return config.getString("display.mode").toLowerCase(); }
    public Map<Integer,String> getActionBarStyles(){ return getIntStringMap("display.actionBarPercentageStyles"); }
}
