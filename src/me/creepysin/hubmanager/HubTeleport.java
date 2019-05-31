package me.creepysin.hubmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class HubTeleport {
	
	private Main plugin;
	
	public HubTeleport(Main _main) {
		plugin = _main;
	}
	
	public void TeleportPlayer(Player player, String hubCmd) {
		// Check to see if the hub actually exist!
		if(plugin.getConfig().getString(hubCmd + ".world") == null) {
			player.sendMessage(ChatColor.RED + "That hub doesn't exist!");
			return;
		}
		
		World w = Bukkit.getServer().getWorld(plugin.getConfig().getString(hubCmd + ".world"));
		
		// If the world equals null, then don't teleport and put a warning in the console.
		if(w == null) {
			player.sendMessage(ChatColor.RED + "That hub hasn't been set yet!");
			plugin.getLogger().warning(ChatColor.RED + "The hub '" + hubCmd + "' hasn't been set! When in game do '/sethub " + hubCmd + "' to set the hub!");
			return;
		}
		
		double x = plugin.getConfig().getDouble(hubCmd + ".x");
		double y = plugin.getConfig().getDouble(hubCmd + ".y");
		double z = plugin.getConfig().getDouble(hubCmd + ".z");
		
		if(w.getName().equalsIgnoreCase("")) {
			player.sendMessage(ChatColor.RED + "That hub hasn't been set yet!");
			return;
		}
		else {
			player.teleport(new Location(w, x, y, z));
			
			// Send the player the formated message.
			String formatMessage = plugin.getConfig().getString(hubCmd + ".message").replace("%name%", hubCmd).replace("%line%", "\n");
			if(!formatMessage.equalsIgnoreCase(""))
				player.sendMessage(formatMessage);
		}
	}
}
