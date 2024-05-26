package org.hikuro.hikucraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameSelector {

	private static final Inventory menu = Bukkit.createInventory(null, 9, "Game Selector");

	static {
		ItemStack semirp = new ItemStack(Material.FILLED_MAP);
		ItemMeta semirpMeta = semirp.getItemMeta();
		semirpMeta.setDisplayName(ChatColor.GREEN + "Semi-RP");
		semirp.setItemMeta(semirpMeta);

		menu.setItem(0, semirp);
	}

	public void show(Player player) {
		player.openInventory(menu);
	}
}
