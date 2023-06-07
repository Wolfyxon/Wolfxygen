package com.wolfyxon.wolfxygen.events;

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
        plugin.oxygenMgr.createBossBar(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.oxygenMgr.deleteBossBar(event.getPlayer());
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
        Player plr = e.getPlayer();
        ItemStack item = e.getItem();
        System.out.println(item.getItemMeta().getDisplayName());
        double oxygen = plugin.itemsMgr.getOxygenAmount(item);
        if(oxygen > 0){
            plugin.oxygenMgr.addOxygen(plr,oxygen);
        }
    }
}