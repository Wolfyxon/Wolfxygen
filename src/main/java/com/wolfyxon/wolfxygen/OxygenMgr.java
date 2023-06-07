package com.wolfyxon.wolfxygen;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import javax.print.attribute.Size2DSyntax;
import java.util.*;

public class OxygenMgr {
    Wolfxygen plugin;

    public OxygenMgr(Wolfxygen plugin){
        this.plugin = plugin;
    }

    public long interval = 20L; // ticks: 20 = 1s
    public double maxOxygen = 10;
    public double oxygenLoss = 1;
    public double oxygenRefilling = 1.5;
    public double damage = 0.5;
    boolean refillInUnaffectedGamemodes = true;
    boolean refillInUnaffectedWorlds = true;
    List affectedGamemodes = List.of("survival","adventure");
    List affectedWorlds = List.of("world_nether","world_the_end");

    public String bossBarTitle = "Oxygen";
    public BarColor bossBarColor = BarColor.WHITE;

    Map<Integer,String> actionBarStyles;

    public void applyConfig(){
        actionBarStyles = plugin.config.getActionBarStyles();
        refillInUnaffectedGamemodes = plugin.config.getRefillInUnaffectedGamemodes();
        refillInUnaffectedWorlds = plugin.config.getRefillInUnaffectedGamemodes();
        affectedGamemodes = plugin.config.getAffectedGamemodes();
        affectedWorlds = plugin.config.getAffectedWorlds();
        maxOxygen = plugin.config.getMaxOxygen();
        oxygenLoss = plugin.config.getSubtractionAmount();
        damage = plugin.config.getDamage();
        interval = plugin.config.getIntervalTicks();
        oxygenRefilling = plugin.config.getRefillingAmount();

        bossBarTitle = plugin.config.getBossBarTitle();
        bossBarColor = plugin.config.getBossBarColor();
    }

    BukkitScheduler scheduler = Bukkit.getScheduler();
    BukkitTask task;

    Map<Player,Double> oxygenAmount = new HashMap<>();

    public void start(){
        stop();
        task = scheduler.runTaskTimer(plugin, () -> {
                for(Player plr : Bukkit.getOnlinePlayers()){
                    if(isAffected(plr)){
                        subtractOxygen(plr);
                        if(getOxygen(plr) <= 0){
                            plr.damage(damage);
                        }
                    } else {
                        if(
                                ( refillInUnaffectedGamemodes && !isInAffectedGameMode(plr) ) ||
                                ( refillInUnaffectedWorlds && !isInAffectedWorld(plr) )
                        ){
                            addOxygen(plr);
                        }
                    }
                }
        }, 0, interval);
    }
    public void stop(){
        if(task != null) task.cancel();
    }

    public ArrayList<BossBar> bossBars = new ArrayList<>();
    public BossBar getBossBar(Player player){
        for(BossBar b : bossBars){
            if(b.getPlayers().contains(player)) return b;
        }
        return null;
    }
    public void createBossBar(Player player){
        if(getBossBar(player) != null) return;
        BossBar bossBar = Bukkit.createBossBar(bossBarTitle, bossBarColor, BarStyle.SOLID);
        bossBar.addPlayer(player);
        bossBars.add(bossBar);
    }
    public void deleteBossBar(Player player){
        BossBar bossBar = getBossBar(player);
        if(bossBar == null) return;
        bossBar.removeAll();
        bossBars.remove(bossBar);
    }

    public void startRendering(){
        scheduler.runTaskTimer(plugin, () -> {
            for(Player plr : Bukkit.getOnlinePlayers()){
                BossBar bar = getBossBar(plr);
                if(bar != null){
                    double p = getOxygenPercentage(plr);
                    bar.setProgress(p);
                    bar.setVisible( plugin.config.getShowWhenFull() || (p < 1) );
                }
                //Utils.showActionBar( plr, plugin.config.getActionBarText(getOxygen(plr),maxOxygen,actionBarStyles) );
            }
        },0,5L);
    }

    public boolean isAffected(Player player){
        if(!player.isOnline()) return false;
        if(player.isDead()) return false;
        if( !isInAffectedGameMode(player) ) return false;
        if( !isInAffectedWorld(player) ) return false;
        return true;
    }
    public boolean isInAffectedGameMode(Player player){
        return affectedGamemodes.contains(player.getGameMode().name().toLowerCase());
    }
    public boolean isInAffectedWorld(Player player){
        return affectedWorlds.contains(player.getWorld().getName());
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
    public double getOxygenPercentage(Player player){
        prepare(player);
        double amt = getOxygen(player);
        if(amt == 0) return 0;
        return amt / maxOxygen;
    }

    public void addOxygen(Player player,double amount){
        double oxygen = getOxygen(player) + amount;
        if(oxygen>maxOxygen) oxygen = maxOxygen;
        setOxygen(player,oxygen);
    }
    public void addOxygen(Player player){
        addOxygen(player,oxygenRefilling);
    }

    public void subtractOxygen(Player player,double amount){
        double oxygen = getOxygen(player) - amount;
        if(oxygen<0) oxygen = 0;
        setOxygen(player,oxygen);
    }
    public void subtractOxygen(Player player){ subtractOxygen(player,oxygenLoss); }



}
