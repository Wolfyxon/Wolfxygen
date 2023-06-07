package com.wolfyxon.wolfxygen;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Utils {
    public static void showActionBar(Player player, String text){
        if(text == null) return;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }
}
