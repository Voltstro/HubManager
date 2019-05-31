package me.creepysin.hubmanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class HubManagerCommands implements CommandExecutor, TabCompleter {

	private Main plugin;
	
	public HubManagerCommands(Main _main) {
		plugin = _main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender instanceof Player) {
					Player player = (Player) sender;
					if(player.hasPermission("hubmanager.reload")) {
						plugin.reload(sender);
					}
				}
				else {
					plugin.reload(sender);
				}
			}
			else if(args[0].equalsIgnoreCase("about")) {
				sender.sendMessage("HubManager - Version " + plugin.getDescription().getVersion() + "\nCreated by " + plugin.getDescription().getAuthors());
			}
			else if(args[0].equalsIgnoreCase("version")) {
				sender.sendMessage(plugin.getDescription().getVersion());
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
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> subCmds = new ArrayList<String>();
		
		subCmds.add("version");
		subCmds.add("about");
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("hubmanager.reload")) {
				subCmds.add("reload");
			}
		}
		else {
			subCmds.add("reload");
		}
		
		return subCmds;	
	}
}
