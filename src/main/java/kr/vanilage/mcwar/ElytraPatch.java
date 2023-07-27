package kr.vanilage.mcwar;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ElytraPatch implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getEntity();
            if (p.getInventory().getItem(EquipmentSlot.CHEST).getType().equals(Material.ELYTRA)) {
                p.setCooldown(Material.ELYTRA, 100);
            }
        }
    }

    @EventHandler
    public void onFly(EntityToggleGlideEvent e) {
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getEntity();
            if (p.hasCooldown(Material.ELYTRA)) {
                e.setCancelled(true);
            }
        }
    }
}
