package io.github.com.revenantgaze.classes.listeners;

import io.github.com.revenantgaze.classes.Main;

import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerSignWriteEvent implements Listener {

	public Main plugin;

	public PlayerSignWriteEvent(Main instance) {

		plugin = instance;

	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void signChange(SignChangeEvent event) {

		Player eventPlayer = event.getPlayer();

		String eventPlayerName = eventPlayer.getName();

		List<String> classNames = plugin.getConfig().getStringList("classes");

		String className = event.getLine(1).trim();

		if (event.getLine(0).equalsIgnoreCase("[classes]")) {

			if (eventPlayer.hasPermission("classes.sign.create")) {

				if (classNames.contains(className)) {

					event.setLine(0, ChatColor.GRAY + "[" + ChatColor.BLUE
							+ "Classes" + ChatColor.GRAY + "]");
					event.setLine(1,
							ChatColor.GREEN + WordUtils.capitalize(className));

					eventPlayer.sendMessage(ChatColor.BLUE
							+ "Sign has been created!");

				}

				else {

					event.setLine(0, ChatColor.GRAY + "[" + ChatColor.RED
							+ "Classes" + ChatColor.GRAY + "]");

					eventPlayer.sendMessage(ChatColor.RED
							+ "This class does not exist!");

				}

			}

			else {

				plugin.getLogger()
						.info(eventPlayerName
								+ "Tried to create a Classes sign without the needed permission!");

			}

		}

	}

}
