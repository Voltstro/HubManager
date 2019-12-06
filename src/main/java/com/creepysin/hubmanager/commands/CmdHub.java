package com.creepysin.hubmanager.commands;

import com.creepysin.hubmanager.HubManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CmdHub extends BukkitCommand {

    private String hub;
    private HubManager plugin;

    public CmdHub(String hubName, HubManager hubManager) {
        super(hubName);
        this.description = "Goes to a hub";
        this.usageMessage = "/<Command>";

        hub = hubName;
        plugin = hubManager;
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "This command cannot be used by the console!");
        }
        else {
            Player player = (Player) commandSender;

            plugin.hubTeleport.TeleportPlayerToHub(player, hub);
        }

        return false;
    }
}
