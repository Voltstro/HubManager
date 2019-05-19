package org.australiacraft.hubmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HubManagerCommands implements CommandExecutor {

	private Main plugin;
	
	public HubManagerCommands(Main _main) {
		plugin = _main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("reload")) {
				sender.sendMessage("Reloading...");
				plugin.reloadConfig();
				sender.sendMessage("Reloaded config! Any new commands added will require the server to resart.");
			}
			else {
				sender.sendMessage("There is no-know sub command " + args[0]);
			}
		}
		else {
			sender.sendMessage("HubManager - Version " + plugin.getDescription().getVersion() + "\nCreated by " + plugin.getDescription().getAuthors());
		}
		
		return true;
	}
	
	
}