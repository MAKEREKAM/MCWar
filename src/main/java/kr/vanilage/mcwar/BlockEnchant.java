package kr.vanilage.mcwar;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockEnchant implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE) || e.getClickedBlock().getType().equals(Material.GRINDSTONE)) e.setCancelled(true);
        }
    }
}
