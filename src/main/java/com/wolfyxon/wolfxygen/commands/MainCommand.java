package com.wolfyxon.wolfxygen.commands;

import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MainCommand extends WolfxygenCommand{
    public MainCommand(Wolfxygen plugin) {
        super(plugin);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sendMsg(sender,"&9Wolfxygen");
            sendMsg(sender,"&5by &4Wolfyxon");
            sendMsg(sender,"&6https://github.com/Wolfyxon/Wolfxygen");
            return true;
        }
        return true;
    }
}
