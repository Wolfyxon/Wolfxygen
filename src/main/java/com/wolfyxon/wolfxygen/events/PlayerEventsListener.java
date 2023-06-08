package com.wolfyxon.wolfxygen.events;

import com.wolfyxon.wolfxygen.ConfigMgr;
import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventsListener implements Listener {
    Wolfxygen plugin;

    public PlayerEventsListener(Wolfxygen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent q) {
        plugin.oxygenMgr.setOxygen(q.getPlayer(), plugin.oxygenMgr.maxOxygen);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player plr = e.getPlayer();
        plugin.oxygenStorage.load(plr);
        plugin.oxygenMgr.createBossBar(plr);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player plr = event.getPlayer();
        plugin.oxygenStorage.save(plr);
        plugin.oxygenMgr.deleteBossBar(plr);
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = e.getItem();
        double oxygen = plugin.itemsMgr.getOxygenAmount(item);
        if(oxygen > 0){
            plugin.oxygenMgr.addOxygen(plr,oxygen);
        }
        plr.sendMessage(ConfigMgr.format( plugin.config.getMessage("potionDrank").replace("{oxygen}",String.valueOf(oxygen)) ));
    }
}