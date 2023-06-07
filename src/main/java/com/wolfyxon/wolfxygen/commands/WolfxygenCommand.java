package com.wolfyxon.wolfxygen.commands;

import com.wolfyxon.wolfxygen.ConfigMgr;
import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WolfxygenCommand implements CommandExecutor {
    Wolfxygen plugin;
    ConfigMgr config;
    public WolfxygenCommand(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.config;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    public static void sendMsg(CommandSender sender, String message){
        sender.sendMessage(ConfigMgr.format(message));
    }
    public static void sendError(CommandSender sender, String message){
        sender.sendMessage( ConfigMgr.format("&c"+message) );
    }
}
