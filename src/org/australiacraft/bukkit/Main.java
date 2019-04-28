package org.australiacraft.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		
		loadConfig();
		
		getCommand("hub").setExecutor(new HubCommand(this));
		getCommand("sethub").setExecutor(new SetHubCommand(this));
	}
	
	public void loadConfig() {
		
		String hub_world = "hub.world";
		String hub_x = "hub.x";
		String hub_y = "hub.y";
		String hub_z = "hub.z";
		
		getConfig().addDefault(hub_world, "");
		
		getConfig().addDefault(hub_x, 0);
		getConfig().addDefault(hub_y, 0);
		getConfig().addDefault(hub_z, 0);
		
		getConfig().options().copyDefaults(true);
		
		saveConfig();
	}

}
