package io.github.com.revenantgaze.classes;

import io.github.com.revenantgaze.classes.listeners.PlayerSignClickEvent;
import io.github.com.revenantgaze.classes.listeners.PlayerSignWriteEvent;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public Plugin instance;

	@Override
	public void onEnable() {

		getServer().getPluginManager().registerEvents(
				new PlayerSignWriteEvent(this), this);
		getServer().getPluginManager().registerEvents(
				new PlayerSignClickEvent(this), this);

		getConfig().options().copyDefaults(true);

		this.getConfig()
				.options()
				.header("Configuration file for Classes "
						+ this.getDescription().getVersion());

		saveConfig();

		getLogger().info(
				"Classes v" + this.getDescription().getVersion()
						+ " has been enabled!");

	}

	@Override
	public void onDisable() {

		getLogger().info(
				"Classes v" + this.getDescription().getVersion()
						+ " has been disabled!");

	}

}
