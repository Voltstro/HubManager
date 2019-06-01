package me.creepysin.hubmanager.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.creepysin.hubmanager.Main;

public class SetHubCommand implements CommandExecutor, TabCompleter {

	private Main plugin;
	
	public SetHubCommand(Main _main) {
		plugin = _main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("This command cannot be used by the console!");
		}
		else {
			Player player = (Player) sender;
			if(args.length == 1) {
				
				String hubCmd = args[0];
				
				boolean doesExist = false;
				for(int i = 0; i < plugin.baseCommands.size(); i++ ) {
					if(hubCmd.equalsIgnoreCase(plugin.baseCommands.get(i).toString())) {
						doesExist = true;
					}
				}
				
				if(doesExist == false) {
					player.sendMessage(ChatColor.RED + "That hub doesn't exist!");
					return true;
				}

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
	
	public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			List<String> subCmds = new ArrayList<String>();
			
			for(int i = 0; i < plugin.baseCommands.size(); i++ ) {
				if(player.hasPermission("hubmanager.hubs." + plugin.baseCommands.get(i))) {
					subCmds.add(plugin.baseCommands.get(i).toString());
				}
			}
			
			return subCmds;
		}
		
		return null;	
	}
}
