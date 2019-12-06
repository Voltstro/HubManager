package com.creepysin.hubmanager;

import com.creepysin.hubmanager.commands.CmdHub;
import com.creepysin.hubmanager.commands.CmdSetHub;
import com.creepysin.hubmanager.helpers.TeleportToHub;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;

public class HubManager extends JavaPlugin {

    public FileConfiguration config;
    public List<String> commands;
    public TeleportToHub hubTeleport;

    @Override
    public void onEnable() {
        getLogger().info("+=====================+");
        getLogger().info("|  " + ChatColor.GREEN +  "HubManager  " + ChatColor.WHITE + getDescription().getVersion() + "  |");
        getLogger().info("+=====================+");
        getLogger().info("Now loading...");

        //Set commands
        try {
            getCommand("sethub").setExecutor(new CmdSetHub(this));
        }
        catch (NullPointerException ex){
            getLogger().log(Level.SEVERE, ex.getMessage());
        }

        hubTeleport = new TeleportToHub(this);

        //Load the config and hubs
        try {
            loadConfig();
        }
        catch(Exception ex){
            getLogger().log(Level.SEVERE, ex.getMessage());
        }

        getLogger().info(ChatColor.GREEN + "HubManager is now ready!");
    }

    private void loadConfig(){
        config = getConfig();
        commands = config.getStringList("commands");

        try{
            final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(getServer());

            for (String command : commands) {
                config.addDefault(command + ".location", getServer().getWorlds().get(0).getSpawnLocation());
                config.addDefault(command + ".message", "You have been teleported.");

                commandMap.register(command, new CmdHub(command, this));
            }

            config.options().copyDefaults(true);
            saveConfig();
        }
        catch (NoSuchFieldException ex){
            getLogger().log(Level.SEVERE, ex.getMessage());
        }
        catch (IllegalAccessException ex){
            getLogger().log(Level.SEVERE, ex.getMessage());
        }
    }
}
