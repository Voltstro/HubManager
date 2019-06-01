package me.creepysin.hubmanager.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.creepysin.hubmanager.HubTeleport;
import me.creepysin.hubmanager.Main;

public class HubSignListeners implements Listener {

	private Main plugin;
	private HubTeleport hubTele;
	
	public HubSignListeners(Main _main, HubTeleport _hubtele) {
		plugin = _main;
		hubTele = _hubtele;
		
		plugin.getLogger().info("Added events.");
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		// Check to see if it is a hub sign, and if it is then check to see if the player has the right permissions
		if(e.getBlock().getState() instanceof Sign) {
			Sign sign = (Sign) e.getBlock().getState();
			
			if(sign.getLine(0).equalsIgnoreCase("[hub]")) {
				if(!e.getPlayer().hasPermission("hubmanager.signs.break")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to break hub signs!");
				}
			}
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[hub]")) {
			
			// Check to see if the player has the right permissions
			if(!e.getPlayer().hasPermission("hubmanager.signs.create")) {
				e.setLine(0, "[§4Hub§0]");
				e.setLine(1, "");
				
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to create hub signs!");
				return;
			}
			
			e.setLine(0, "[Hub]");
			
			e.setLine(1, plugin.getConfig().getString("signs.signHubColor") + e.getLine(1));
			
			for (int i = 0; i < 2; i++ ) {
				e.setLine(i + 2, plugin.getConfig().getList("signs.messages").get(i).toString());
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		
		if(e.getClickedBlock().getState() instanceof Sign) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			
			if(!e.getPlayer().hasPermission("hubmanager.signs.interact")) {
				if(!sign.getLine(0).equalsIgnoreCase("[hub]")) return;
				
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to interact with hub signs!");
				return;
			}
			
			if(sign.getLine(0).equalsIgnoreCase("[hub]")) {
				hubTele.TeleportPlayer(e.getPlayer(), sign.getLine(1).substring(2));
			}
		}
	}
	
}
