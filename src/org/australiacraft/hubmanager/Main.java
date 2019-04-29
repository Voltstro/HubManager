package org.australiacraft.hubmanager;

import java.util.List;

import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		
		loadConfig();
		
		Permission sethubPerm = new Permission("hubmanager.sethubs");
		
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(sethubPerm);
		
		getCommand("sethub").setExecutor(new SetHubCommand(this));
	}
	
	public void loadConfig() {
		
		getLogger().info("Adding commands...");
		
		List<?> baseCommands = getConfig().getList("commands");
		
		for (int i = 0; i < baseCommands.size(); i++ ) {
			
			String cmd = baseCommands.get(i).toString();
			
			getConfig().addDefault(cmd + ".message", "You have been teleported to %name%!%line%You can change this message in the config.");
			getConfig().addDefault(cmd + ".world", "");
			getConfig().addDefault(cmd + ".x", 0);
			getConfig().addDefault(cmd + ".y", 0);
			getConfig().addDefault(cmd + ".z", 0);
			
			
			((CraftServer) this.getServer()).getCommandMap().register(cmd, new HubCommand(cmd, this));
			
			getLogger().info("Added the command `" + baseCommands.get(i) + "`.");	
		}
		
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		
		getLogger().info("The config has been loaded!");
	}

}
