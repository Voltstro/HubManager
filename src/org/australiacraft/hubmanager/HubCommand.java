package org.australiacraft.hubmanager;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class HubCommand extends BukkitCommand {

	private String hubCmd;
	private Main plugin;
	
	protected HubCommand(String name, Main _main) {
		super(name);
		this.description = "Goes to a hub";
		this.usageMessage = "/<Command>";
		this.setPermission("hubmanager.hubs." + name);
		
		hubCmd = name;
		plugin = _main;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("This command cannot be used by the console!");
		}
		else {
			
			if (!sender.hasPermission(this.getPermission())) {
	            sender.sendMessage(ChatColor.RED + "You don't have permission to do that command!");
	            return true;
	        }
			
			Player player = (Player) sender;
			
			World w = Bukkit.getServer().getWorld(plugin.getConfig().getString(hubCmd + ".world"));
			
			//If the world equals null, then don't teleport and put a warning in the console.
			if(w == null) {
				player.sendMessage(ChatColor.RED + "That hub hasn't been set yet!");
				plugin.getLogger().warning(ChatColor.RED + "The hub '" + hubCmd + "' hasn't been set! When in game do '/sethub " + hubCmd + "' to set the hub!");
				return true;
			}
			
			double x = plugin.getConfig().getDouble(hubCmd + ".x");
			double y = plugin.getConfig().getDouble(hubCmd + ".y");
			double z = plugin.getConfig().getDouble(hubCmd + ".z");
			
			if(w.getName().equalsIgnoreCase("")) {
				player.sendMessage("That hub hasn't been set yet!");
				return true;
			}
			else {
				player.teleport(new Location(w, x, y, z));
				
				// Send the player the formated message.
				String formatMessage = plugin.getConfig().getString(hubCmd + ".message").replace("%name%", hubCmd).replace("%line%", "\n");
				if(!formatMessage.equalsIgnoreCase(""))
					player.sendMessage(formatMessage);
			}
		}
		
		return true;
	}
}
