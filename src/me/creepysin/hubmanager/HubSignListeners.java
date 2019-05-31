package me.creepysin.hubmanager;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HubSignListeners implements Listener {

	private Main plugin;
	private HubTeleport hubTele;
	
	public HubSignListeners(Main _main, HubTeleport _hubtele) {
		plugin = _main;
		hubTele = _hubtele;
		
		plugin.getLogger().info("Added events.");
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[hub]")) {
			
			// Check to see if the player has the right permissions
			if(!e.getPlayer().hasPermission("hubmanager.signs.create")) return;
			
			e.setLine(0, "[Hub]");
			
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
			
			if(sign.getLine(0).equalsIgnoreCase("[hub]")) {
				hubTele.TeleportPlayer(e.getPlayer(), sign.getLine(1));
			}
		}
	}
	
}
