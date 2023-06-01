package com.wolfyxon.wolfxygen;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class OxygenMgr {
    Wolfxygen plugin;

    public OxygenMgr(Wolfxygen plugin){
        this.plugin = plugin;
    }

    public long interval = 20L; // ticks: 20 = 1s
    public double maxOxygen = 10;
    public double oxygenLoss = 1;
    public double damage = 0.5;
    List affectedGamemodes = List.of("survival","adventure");
    List affectedWorlds = List.of("world_nether","world_the_end");

    public void applyConfig(){
        affectedGamemodes = plugin.config.getAffectedGamemodes();
        affectedWorlds = plugin.config.getAffectedWorlds();
    }

    BukkitScheduler scheduler = Bukkit.getScheduler();
    Timer timer;

    Map<Player,Double> oxygenAmount = new HashMap<>();

    public void start(){
        scheduler.runTaskTimer(plugin, () -> {
                for(Player plr : Bukkit.getOnlinePlayers()){
                    if(isAffected(plr)){
                        plr.sendMessage(String.valueOf(getOxygen(plr)));
                        subtractOxygen(plr);
                        if(getOxygen(plr) <= 0){
                            plr.damage(damage);

                        }
                    }
                }
        }, 0, interval);
    }
    public boolean isAffected(Player player){
        if(!player.isOnline()) return false;
        if(player.isDead()) return false;
        if( !affectedGamemodes.contains(player.getGameMode().name().toLowerCase()) ) return false;
        if( !affectedWorlds.contains(player.getWorld().getName()) ) return false;
        return true;
    }

    public void setOxygen(Player player,double amount){
        oxygenAmount.put(player,amount);
    }
    public void prepare(Player player){
        if(oxygenAmount.containsKey(player)) return;
        setOxygen(player,maxOxygen);
    }
    public double getOxygen(Player player){
        prepare(player);
        return oxygenAmount.get(player);
    }
    public void addOxygen(Player player,double amount){
        double oxygen = getOxygen(player) + amount;
        if(oxygen>maxOxygen) oxygen = maxOxygen;
        setOxygen(player,oxygen);
    }
    public void subtractOxygen(Player player,double amount){
        double oxygen = getOxygen(player) - amount;
        if(amount<0) oxygen = 0;
        setOxygen(player,oxygen);
    }
    public void subtractOxygen(Player player){ subtractOxygen(player,oxygenLoss); }

}
