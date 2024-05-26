package org.hikuro.hikucraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
	@Override
	public void onEnable() {
		EventRecorder eventRecorder = new EventRecorder(this);

		// On player join
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onPlayerJoin(PlayerJoinEvent event) {
						getLogger().info("Player join: " + event.getPlayer().getName());
						// api.registerPlayer(event.getPlayer().getUniqueId());
						ItemStack compass = new ItemStack(Material.COMPASS);
						ItemMeta meta = compass.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + "Game Selector");
						compass.setItemMeta(meta);
						event.getPlayer().getInventory().setItem(0, compass);
					}
				});

		// On player quit
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onPlayerQuit(PlayerQuitEvent event) {
						getLogger().info("Player quit: " + event.getPlayer().getName());
					}
				});

		// On block break
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onBlockBreak(BlockBreakEvent event) {
						event.setCancelled(true);
					}
				});

		// On Inventory click
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onInventoryClick(BlockBreakEvent event) {
						event.setCancelled(true);
					}
				});

		// On player drop item
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onPlayerDropItem(PlayerDropItemEvent event) {
						event.setCancelled(true);
					}
				});

		// On player interact
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onPlayerInteract(PlayerInteractEvent event) {
						event.setCancelled(true);
						if (event.getItem() != null
								&& event.getItem().getType() == Material.COMPASS) {
							new GameSelector().show(event.getPlayer());
						}
					}
				});

		// On choose game mode
		eventRecorder.register(
				new EventListener() {
					@EventHandler
					public void onInventoryClick(InventoryClickEvent event) {
						if (event.getView().getTitle().equals("Game Selector")) {
							event.setCancelled(true);
							Player player = (Player) event.getWhoClicked();
							ItemStack clickedItem = event.getCurrentItem();

							if (clickedItem != null) {
								if (clickedItem.getType() == Material.FILLED_MAP) {
									player.sendMessage(
											ChatColor.GREEN + "Teleporting to Semi-RP World...");
									// TODO: common.teleportToSemiRPWorld(player);
								}
							}
						}
					}
				});

		// Lobby command
		this.getCommand("lobby")
				.setExecutor(
						(commandSender, command, s, strings) -> {
							if (!(commandSender instanceof Player player)) {
								return false;
							}
							player.teleport(player.getWorld().getSpawnLocation());
							return true;
						});

		// Test command
		this.getCommand("test")
				.setExecutor(
						(commandSender, command, s, strings) -> {
							if (!(commandSender instanceof Player player)) {
								return false;
							}
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
