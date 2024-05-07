package org.hikuro.hikucraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
	@Override
	public void onEnable() {

		EventRecorder eventRecorder = new EventRecorder(this);
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onPlayerJoin(PlayerJoinEvent event) {
						getLogger().info("Player join: " + event.getPlayer().getName());
						// api.registerPlayer(event.getPlayer().getUniqueId());
					}

					@EventHandler
					public void onPlayerQuit(PlayerQuitEvent event) {
						getLogger().info("Player quit: " + event.getPlayer().getName());
					}

					@EventHandler
					public void onBlockBreak(BlockBreakEvent event) {
						event.setCancelled(true);
					}
				});

		this.getCommand("lobby")
				.setExecutor(
						(commandSender, command, s, strings) -> {
							if (!(commandSender instanceof Player)) {
								return false;
							}
							Player player = (Player) commandSender;
							player.teleport(player.getWorld().getSpawnLocation());
							return true;
						});

		this.getCommand("test")
				.setExecutor(
						(commandSender, command, s, strings) -> {
							if (!(commandSender instanceof Player)) {
								return false;
							}
							Player player = (Player) commandSender;
							player.sendMessage("Test not implemented yet!");
							return true;
						});
		getLogger().info("Lobby plugin enabled");
	}

	@Override
	public void onDisable() {

		getLogger().info("Lobby plugin disabled");
	}
}
