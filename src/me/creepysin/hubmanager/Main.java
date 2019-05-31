package me.creepysin.hubmanager;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.CraftServer;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public List<?> baseCommands;
	
	private HubTeleport hubTele;

	public void onEnable() {
		hubTele = new HubTeleport(this);
		
		loadConfig();
		
		// Add the permissions
		Permission sethubPerm = new Permission("hubmanager.sethubs");
		Permission reloadPerm = new Permission("hubmanager.reload");
		Permission signsCreatePerm = new Permission("hubmanager.signs.create");
		Permission signsInteractPerm = new Permission("hubmanager.signs.interact");
		
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(sethubPerm);
		pm.addPermission(reloadPerm);
		pm.addPermission(signsCreatePerm);
		pm.addPermission(signsInteractPerm);
		
		// Add commands
		getCommand("sethub").setExecutor(new SetHubCommand(this));
		getCommand("hubmanager").setExecutor(new HubManagerCommands(this));
		
		// Register events
		if(getConfig().getBoolean("signs.enabled")) {
			getServer().getPluginManager().registerEvents(new HubSignListeners(this, hubTele), this);
		}
		
		getLogger().info("HubManger has been loaded!");
		
	}
	
	public void loadConfig() {
		getLogger().info("Adding commands...");
		
		baseCommands = getConfig().getList("commands");
		
		// Load in each command
		for (int i = 0; i < baseCommands.size(); i++ ) {
			String cmd = baseCommands.get(i).toString();
			
			getConfig().addDefault(cmd + ".message", "\u00A76You have been teleported to \u00A7c%name%\u00A76!%line%You can change this message in the config.");
			getConfig().addDefault(cmd + ".world", "");
			getConfig().addDefault(cmd + ".x", 0);
			getConfig().addDefault(cmd + ".y", 0);
			getConfig().addDefault(cmd + ".z", 0);
			
			((CraftServer) this.getServer()).getCommandMap().register(cmd, new HubCommand(cmd, hubTele));
			
			if(getConfig().getBoolean("outputAddedCmds") == true) {
				getLogger().info("Added the command `" + baseCommands.get(i) + "`.");
			}		
		}	

		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getCommand("hubs").setExecutor(new AllHubsCommand(baseCommands));
		
		getLogger().info("The config has been loaded!");
	}
	
	public void reload(CommandSender sender) {
		sender.sendMessage("Reloading...");
		this.reloadConfig();
		sender.sendMessage("Reloaded config! Any new commands added will require the server to resart.");
	}
}
