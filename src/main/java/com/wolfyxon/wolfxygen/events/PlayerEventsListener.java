package com.wolfyxon.wolfxygen.events;

import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerEventsListener implements Listener {
    Wolfxygen plugin;
    public PlayerEventsListener(Wolfxygen plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent q) {
        plugin.oxygenMgr.setOxygen(q.getPlayer(),plugin.oxygenMgr.maxOxygen);
    }

}
