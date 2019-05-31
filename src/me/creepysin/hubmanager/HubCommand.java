package me.creepysin.hubmanager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.ChatColor;

public class HubCommand extends BukkitCommand {

	private String hubCmd;
	private HubTeleport hubTeleport;
	
	protected HubCommand(String name, HubTeleport _hubTele) {
		super(name);
		this.description = "Goes to a hub";
		this.usageMessage = "/<Command>";
		this.setPermission("hubmanager.hubs." + name);
		
		hubCmd = name;
		hubTeleport = _hubTele;
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
			
			hubTeleport.TeleportPlayer(player, hubCmd);
		}
		
		return true;
	}
}
