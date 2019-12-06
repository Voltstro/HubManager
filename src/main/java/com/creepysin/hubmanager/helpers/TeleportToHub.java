package com.creepysin.hubmanager.helpers;

import com.creepysin.hubmanager.HubManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportToHub {

    private HubManager plugin;

    public TeleportToHub(HubManager hubManager){
        plugin = hubManager;
    }

    public void TeleportPlayerToHub(Player player, String hub){
        if(plugin.config.get(hub + ".location") == null){
            player.sendMessage(ChatColor.RED + "That hub doesn't exist!");
            return;
        }

        player.teleport((Location)plugin.config.get(hub + ".location"));

        String formatMessage = plugin.getConfig().getString(hub + ".message").replace("%name%", hub).replace("%line%", "\n");
        if(!formatMessage.equalsIgnoreCase(""))
            player.sendMessage(formatMessage);
    }
}
