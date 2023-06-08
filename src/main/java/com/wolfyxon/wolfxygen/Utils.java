package com.wolfyxon.wolfxygen;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
    public static Color parseColor(String input){
        Color def = Color.BLACK;
        if(input.contains("(") && input.contains(")")){ // use RGB
            String tmp = extractBetween(input,"(",")");
            String[] strRGB = tmp.split(",");
            if(strRGB.length != 3) return def;
            List<Integer> rgb = new ArrayList<>();
            for( String s : strRGB){
                if(!isInt(s)) return def;
                rgb.add(Integer.parseInt(s));
            }
            return Color.fromRGB(rgb.get(0),rgb.get(1),rgb.get(2));
        }
        switch (input.toLowerCase()){
            case "white": return Color.WHITE;
            case "silver": return Color.SILVER;
            case "gray": return Color.GRAY;
            case "black": return Color.BLACK;
            case "red": return Color.RED;
            case "maroon": return Color.MAROON;
            case "yellow": return Color.YELLOW;
            case "olive": return Color.OLIVE;
            case "lime": return Color.LIME;
            case "green": return Color.GREEN;
            case "aqua": return Color.AQUA;
            case "teal": return Color.TEAL;
            case "blue": return Color.BLUE;
            case "navy": return Color.NAVY;
            case "fuchsia": return Color.FUCHSIA;
            case "purple": return Color.PURPLE;
            case "orange": return Color.ORANGE;
        }

        return def;
    }
    public static boolean hasMethod(Object object, String methodName) {
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }

}
