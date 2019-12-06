package com.creepysin.hubmanager.commands;

import com.creepysin.hubmanager.HubManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetHub implements CommandExecutor {

    private HubManager plugin;

    public CmdSetHub(HubManager hubManager){
        plugin = hubManager;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "This command can only be used by a player in game!");
            return false;
        }

        if(args.length == 1){
            Player player = (Player) commandSender;

            if(plugin.commands.contains(args[0])){
                String hub = plugin.commands.get(plugin.commands.indexOf(args[0]));

                plugin.config.set(hub + ".location", player.getLocation());

                plugin.saveConfig();

                player.sendMessage(ChatColor.GREEN + "The hub was set to this location.");
            }
            else{
                player.sendMessage(ChatColor.RED + "That hub doesn't exists!");
            }

            return false;
        }

        commandSender.sendMessage(ChatColor.RED + "You can only input ONE argument into this command!");

        return false;
    }
}
