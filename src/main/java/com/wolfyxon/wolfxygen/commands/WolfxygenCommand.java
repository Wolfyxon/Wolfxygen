package com.wolfyxon.wolfxygen.commands;

import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WolfxygenCommand implements CommandExecutor {
    Wolfxygen plugin;
    public WolfxygenCommand(Wolfxygen plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
