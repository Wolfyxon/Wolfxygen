package com.wolfyxon.wolfxygen;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.regex.Pattern;

public class Utils {
    public static void showActionBar(Player player, String text){
        if(text == null) return;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
    }
    public static String multiReplace(String str, Map<String,String> replacements){
        for(Map.Entry e : replacements.entrySet()){
            str = str.replace( (String) e.getKey(), (String) e.getValue());
        }
        return str;
    }
    public static String extractBetween(String input, String str1, String str2){
        String[] split1 = input.split(Pattern.quote(str2));
        if(split1.length == 0) return input;
        input = split1[split1.length-1];
        String[] split2 = input.split(str2);
        input = split2[0];
        return input;
    }
    public static boolean isInt(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
