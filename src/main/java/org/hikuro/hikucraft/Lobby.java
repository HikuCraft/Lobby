package org.hikuro.hikucraft;

import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
	@Override
	public void onEnable() {

		getLogger().info("Lobby plugin enabled");
	}

	@Override
	public void onDisable() {

		getLogger().info("Lobby plugin disabled");
	}
}
