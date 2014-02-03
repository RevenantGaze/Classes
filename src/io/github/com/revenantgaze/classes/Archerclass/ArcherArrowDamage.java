package io.github.com.revenantgaze.classes.Archerclass;

import io.github.com.revenantgaze.classes.Main;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArcherArrowDamage implements Listener {

	public Main plugin;

	public ArcherArrowDamage(Main instance) {

		plugin = instance;

	}

	public void onArrowDamage(EntityDamageByEntityEvent event) {

		String playerName = ((Player) event.getEntity()).getName();
		String playerClass = plugin.getConfig().getString(
				"player." + playerName + ".class");

		if (playerClass.equalsIgnoreCase("archer")) {

			if (event.getDamager() instanceof Arrow) {

				Arrow shotArrow = (Arrow) event.getDamager();

				if (shotArrow.getShooter() instanceof Player) {

					event.setDamage(event.getDamage() + 2);

				}

			}

		}

	}

}
