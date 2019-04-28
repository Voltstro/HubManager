package org.australiacraft.bukkit;

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
			
			plugin.getConfig().set("hub.world", player.getLocation().getWorld().getName());
			plugin.getConfig().set("hub.x", player.getLocation().getX());
			plugin.getConfig().set("hub.y", player.getLocation().getY());
			plugin.getConfig().set("hub.z", player.getLocation().getZ());
			
			plugin.saveConfig();
			
			player.sendMessage("The hub was successfully set!");
		}
		
		return true;
	}

}
