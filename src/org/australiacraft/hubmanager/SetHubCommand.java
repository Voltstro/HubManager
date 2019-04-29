package org.australiacraft.hubmanager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHubCommand implements CommandExecutor {

	private Main plugin;
	
	public SetHubCommand(Main _main) {
		plugin = _main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("This cannot be uses by the console!");
		}
		else {
			Player player = (Player) sender;
			if(args.length == 1) {
				
				String hubCmd = args[0];
				
				plugin.getConfig().set(hubCmd + ".world", player.getLocation().getWorld().getName());
				plugin.getConfig().set(hubCmd + ".x", player.getLocation().getX());
				plugin.getConfig().set(hubCmd + ".y", player.getLocation().getY());
				plugin.getConfig().set(hubCmd + ".z", player.getLocation().getZ());
				
				plugin.saveConfig();
				
				player.sendMessage("The hub was successfully set!");
				
			}
			else {
				player.sendMessage(ChatColor.RED + "There is either too many arguments or too little arguments!");
			}
		}
		
		return true;
	}

}
