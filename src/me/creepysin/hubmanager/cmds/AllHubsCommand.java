package me.creepysin.hubmanager.cmds;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllHubsCommand implements CommandExecutor {

	private List<?> allCommands;
	
	public AllHubsCommand(List<?> commands) {
		allCommands = commands;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String finalMessage = ChatColor.GOLD + "Here are all the available hubs:\n" + ChatColor.WHITE;
		
		for (int i = 0; i < allCommands.size(); i++ ) {
			String hub = allCommands.get(i).toString();
			
			if(!(sender instanceof Player)) {
				//If it is the console then just put all hubs into the string
				finalMessage += hub + "\n"; 
			}
			else {
				//If it is a player then check if they have access to the hub permission
				Player player = (Player) sender;
				if(player.hasPermission("hubmanager.hubs." + hub)) {
					finalMessage += hub + "\n"; 
				}
			}
				
		}
		
		sender.sendMessage(finalMessage);
		
		return true;
	}

}
