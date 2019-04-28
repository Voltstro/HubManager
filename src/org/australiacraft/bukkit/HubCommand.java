package org.australiacraft.bukkit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HubCommand implements CommandExecutor {

	private Main plugin;
	
	public HubCommand(Main _main) {
		plugin = _main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("This cannot be uses by the console!");
		}
		else {
			
			Player player = (Player) sender;
			
			World w = plugin.getServer().getWorld(plugin.getConfig().getString("hub.world"));
			double x = plugin.getConfig().getDouble("hub.x");
			double y = plugin.getConfig().getDouble("hub.y");
			double z = plugin.getConfig().getDouble("hub.z");
			
			if(w.getName().equalsIgnoreCase("")) {
				player.sendMessage("The hub hasn't been set yet!");
				return true;
			}
			
			
			player.teleport(new Location(w, x, y, z));
			
		}
		
		return true;
	}

}
