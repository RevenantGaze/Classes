package io.github.com.revenantgaze.classes.commands;

import io.github.com.revenantgaze.classes.Main;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClass implements CommandExecutor {

	public Main plugin;

	public CmdClass(Main instance) {

		plugin = instance;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("class")) {

				Player senderPlayer = (Player) sender;

				String senderPlayerName = senderPlayer.getName();
				String playerClass = plugin.getConfig().getString(
						"player." + senderPlayerName + ".class");
				String playerClassCapitalized = WordUtils
						.capitalize(playerClass);

				senderPlayer.sendMessage(ChatColor.AQUA
						+ "You are currently in the " + ChatColor.RED
						+ playerClassCapitalized + ChatColor.AQUA + " class!");

				return true;

			}

		}

		else {

			sender.sendMessage("You can't execute this command from the console!");

			return true;

		}

		return true;

	}

}
