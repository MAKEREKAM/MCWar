package kr.vanilage.mcwar.beacon;

import kr.vanilage.mcwar.MCWar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class InfoBeacon implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.REINFORCED_DEEPSLATE)) {
                if (e.getHand().equals(EquipmentSlot.HAND)) {
                    for (String i : MCWar.config.getConfigurationSection("beacons").getKeys(false)) {
                        int x = Integer.parseInt(i.split(" ")[0]);
                        int y = Integer.parseInt(i.split(" ")[1]);
                        int z = Integer.parseInt(i.split(" ")[2]);

                        if (x == e.getClickedBlock().getX() && y == e.getClickedBlock().getY() && z == e.getClickedBlock().getZ()) {
                            e.getPlayer().sendMessage(ChatColor.GREEN + MCWar.config.getString("beacons." + i + ".team") + " 팀의 신호기.");
                            return;
                        }
                    }
                }
            }
        }
    }
}
