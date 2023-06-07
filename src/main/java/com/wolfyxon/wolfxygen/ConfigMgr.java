package com.wolfyxon.wolfxygen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMgr {
    Wolfxygen plugin;
    FileConfiguration config;

    public static String permPrefix = "wolfxygen.";

    public ConfigMgr(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public String format(String text){
        if(text == null) return "";
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public String getMessage(String subPath){
        String msg = config.getString("message."+subPath);
        if(msg == null) return null;
        return format(msg);
    }
    public void sendMessage(Player player, String messageSubPath){
        String msg = getMessage(messageSubPath);
        if(msg == null) return;
        if(msg.length()==0) return;
        player.sendMessage(msg);
    }
    public void sendNoPermissionMessage(Player player){
        sendMessage(player,"messages.noPermission");
    }
    public static String formatPermission(String permission){
        if(permission.startsWith(permPrefix)) return permission.replace(permPrefix,"");
        return permission;
    }
    public static boolean hasPermission(Player player, String permission){
        return player.isOp() || player.hasPermission(permPrefix+permission);
    }
    public boolean checkAndNotifyPermission(Player player,String permission){
        boolean has = hasPermission(player,permission);
        if(!has) sendNoPermissionMessage(player);
        return has;
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
    public boolean getShowWhenFull(){ return  config.getBoolean("display.showWhenFull");}
    public Map<Integer,String> getActionBarStyles(){ return getIntStringMap("display.actionBarPercentageStyles"); }

    public String getActionBarText(double oxygen, double maxOxygen,Map<Integer,String> styles){
        double percent = oxygen/maxOxygen;
        Object[] percentages = styles.keySet().toArray();
        Arrays.sort(percentages);
        int targetPercent = 0;

        for(Object p : percentages){
            if(percent >= (Integer) p){
                targetPercent = (Integer) p;
            } else {
                break;
            }
        }
        if(!styles.containsKey(targetPercent)) return null;
        return format( styles.get(targetPercent) );
    }
    public String getActionBarText(double oxygen, double maxOxygen){
        return getActionBarText(oxygen,maxOxygen,getActionBarStyles());
    }

    public String getBossBarTitle(){ return format(config.getString("display.bossBar.title")); }
    public BarColor getBossBarColor(){
        try {
            return BarColor.valueOf(config.getString("display.bossBar.color").toUpperCase());
        } catch (IllegalStateException e){
            return BarColor.WHITE;
        }
    }

}
