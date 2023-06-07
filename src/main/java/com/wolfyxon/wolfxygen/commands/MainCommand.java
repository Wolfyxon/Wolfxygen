package com.wolfyxon.wolfxygen.commands;

import com.wolfyxon.wolfxygen.Wolfxygen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class MainCommand extends WolfxygenCommand{
    public MainCommand(Wolfxygen plugin) {
        super(plugin);
    }

    class Action {
        public String[] aliases;
        public String description;
        public String permission;

        public Action(String[] aliases, String description, String permission){
            this.aliases = aliases;
            this.description = description;
            this.permission = permission;
        }
        public Action(String alias, String description, String permission){
            this(new String[]{alias},description,permission);
        }

        public boolean hasAlias(String alias){ return Arrays.asList(aliases).contains(alias); }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sendMsg(sender,"&9Wolfxygen");
            sendMsg(sender,"&5by &4Wolfyxon");
            sendMsg(sender,"&6https://github.com/Wolfyxon/Wolfxygen");
            return true;
        }
        String action = args[0];

        return true;
    }
}
