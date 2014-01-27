package io.github.com.revenantgaze.classes;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.com.revenantgaze.classes.Main;

public class ConfigManager {
	
	public Main plugin;

	public ConfigManager(Main instance) {

		plugin = instance;

	}
	
	public void setPlayerClass(Player eventPlayer, String playerName, String className) {
		
		plugin.getConfig().set("player." + playerName + ".class", className);
		
		eventPlayer.sendMessage(ChatColor.GOLD + "You just joined the class" + ChatColor.RED + WordUtils.capitalize(className) + "!");
		
	}

}
