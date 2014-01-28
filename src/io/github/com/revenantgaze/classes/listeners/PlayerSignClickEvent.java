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

		boolean logToConsole = plugin.getConfig().getBoolean("log-to-console");

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (event.getClickedBlock().getState() instanceof Sign) {

				Sign sign = (Sign) event.getClickedBlock().getState();

				String className = sign.getLine(1).toLowerCase();

				if (sign.getLine(0).equalsIgnoreCase(
						ChatColor.GRAY + "[" + ChatColor.BLUE + "Classes"
								+ ChatColor.GRAY + "]")) {

					if (eventPlayer.hasPermission("classes.sign.use")) {

						if (className.equalsIgnoreCase(playerClass)) {

							eventPlayer.sendMessage(ChatColor.RED
									+ "You are already a part of this class!");
							
							if (logToConsole == true) {

								plugin.getLogger()
										.info(eventPlayerName
												+ " tried to join his/hers current class!");

							}

						}

						else {

							String classNameCapitalized = WordUtils
									.capitalize(className);

							plugin.getConfig().set(
									"player." + eventPlayerName + ".class",
									className);
							plugin.saveConfig();

							eventPlayer.sendMessage(ChatColor.GOLD
									+ "You just joined the class "
									+ ChatColor.RED + classNameCapitalized
									+ ChatColor.GOLD + "!");
							
							if (logToConsole == true) {

								plugin.getLogger()
										.info(eventPlayerName
												+ " joined the class " + classNameCapitalized + "!");

							}

						}

					}

					else {

						eventPlayer
								.sendMessage(ChatColor.RED
										+ "You do not have permission to use this sign!");

						if (logToConsole == true) {

							plugin.getLogger()
									.info(eventPlayerName
											+ "tried to use a Classes sign without the needed permission!");

						}

					}

				}

			}

		}

	}

}
