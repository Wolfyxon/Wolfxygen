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
        public String primaryAlias;
        public String[] aliases;
        public String description;
        public String permission;

        public Action(String[] aliases, String description, String permission){
            this.aliases = aliases;
            this.description = description;
            this.permission = permission;
            primaryAlias = aliases[0];
        }
        public Action(String alias, String description, String permission){
            this(new String[]{alias},description,permission);
        }
        public Action(String alias, String description){
            this(new String[]{alias},description,null);
        }
        public Action(String aliases[], String description){
            this(aliases,description,null);
        }

        public boolean matches(String alias){ return Arrays.asList(aliases).contains(alias); }

    }

    Action[] actions = {
            new Action("help","Lists subcommands and other Wolfxygen commands.")
    };

    public Action getAction(String alias){
        for(Action a : actions){
            if(a.matches(alias)) return a;
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sendMsg(sender,"&9Wolfxygen");
            sendMsg(sender,"&5by &4Wolfyxon");
            sendMsg(sender,"&6https://github.com/Wolfyxon/Wolfxygen");
            return true;
        }
        String strAction = args[0];
        Action action = getAction(strAction);
        if(action == null) {
            sendError(sender,"Subcommand '"+strAction+"' not found");
            return true;
        }
        if(action.permission != null && !config.checkAndNotifyPermission(sender,action.permission)) return true;
        switch (action.primaryAlias){
            case "help":{
                sendMsg(sender,"&9&lAvailable subcommands:");
                for( Action a : actions ){
                    sendMsg(sender, "&6"+a.aliases.toString()+"&r: "+a.description);
                }
            break;}
        }

        return true;
    }
}
