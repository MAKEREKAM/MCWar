package kr.vanilage.mcwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryGenerator implements InventoryHolder {
    Inventory inv = Bukkit.createInventory(this, 9, ChatColor.GREEN + "아이템 강화");

    @Override
    public Inventory getInventory() {
        for (int i = 0; i < 9; i++) {
            if (i != 1 && i != 2) {
                inv.setItem(i, new ItemStack(Material.RED_STAINED_GLASS_PANE));
            }

            if (i == 7) {
                inv.setItem(i, new ItemStack(Material.ANVIL));
            }
        }
        return inv;
    }
}
