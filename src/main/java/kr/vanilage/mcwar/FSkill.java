package kr.vanilage.mcwar;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class FSkill implements Listener {
    HashMap<UUID, Long> cool = new HashMap<>();

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {

        int level = -1;
        if (e.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getItemMeta() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getItemMeta().getLore() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getLore().contains(ChatColor.YELLOW + "★★★★★")) level++;
        if (e.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).getItemMeta() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).getItemMeta().getLore() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).getLore().contains(ChatColor.YELLOW + "★★★★★")) level++;
        if (e.getPlayer().getInventory().getItem(EquipmentSlot.LEGS).getItemMeta() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.LEGS).getItemMeta().getLore() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.LEGS).getLore().contains(ChatColor.YELLOW + "★★★★★")) level++;
        if (e.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getItemMeta() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getItemMeta().getLore() != null && e.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getLore().contains(ChatColor.YELLOW + "★★★★★")) level++;

        if (level != -1) {
            if (!cool.containsKey(e.getPlayer().getUniqueId())) {
                cool.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*10, level));
                e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());

                return;
            }

            if (System.currentTimeMillis() - cool.get(e.getPlayer().getUniqueId()) >= 90000) {
                cool.remove(e.getPlayer().getUniqueId());
                cool.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*10, level));
                e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
            }
            else {
                e.getPlayer().sendMessage(ChatColor.RED + String.format("쿨타임이 %d초 남았습니다.", 90 - (System.currentTimeMillis() - cool.get(e.getPlayer().getUniqueId())) / 1000));
            }
        }
    }
}
