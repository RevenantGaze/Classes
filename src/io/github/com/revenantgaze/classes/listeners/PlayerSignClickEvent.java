package io.github.com.revenantgaze.classes.listeners;

import io.github.com.revenantgaze.classes.ConfigManager;
import io.github.com.revenantgaze.classes.Main;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerSignClickEvent implements Listener {

	public Main plugin;

	public PlayerSignClickEvent(Main instance) {

		plugin = instance;

	}

	public static ConfigManager cm;

	@EventHandler(priority = EventPriority.NORMAL)
	public void signClick(PlayerInteractEvent event) {

		Player eventPlayer = event.getPlayer();

		String eventPlayerName = eventPlayer.getName();
		String playerClass = plugin.getConfig().getString(
				"player." + eventPlayerName + ".class");

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (event.getClickedBlock().getState() instanceof Sign) {

				Sign sign = (Sign) event.getClickedBlock().getState();

				String className = sign.getLine(1).toLowerCase();

				if (sign.getLine(0).equalsIgnoreCase(
						ChatColor.GRAY + "[" + ChatColor.BLUE + "Classes"
								+ ChatColor.GRAY + "]")) {

					if (eventPlayer.hasPermission("classes.sign.use")) {

						if (className == playerClass.toLowerCase()) {

							eventPlayer.sendMessage(ChatColor.RED
									+ "You are already a part of this class!");

						}

						else {

							plugin.getConfig().set("player." + eventPlayerName + ".class", className);
							
							eventPlayer.sendMessage(ChatColor.GOLD + "You just joined the class" + ChatColor.RED + WordUtils.capitalize(className) + "!");

						}

					}

					if (eventPlayer.hasPermission("classes.sign.denyuse")) {

						plugin.getLogger()
								.info(eventPlayerName
										+ "Tried to use a Classes sign without the needed permission!");

					}
					
					else {
						
						eventPlayer.sendMessage(ChatColor.RED + "You do not have permission to use this sign!");
						
					}

				}

			}

		}

	}

}
