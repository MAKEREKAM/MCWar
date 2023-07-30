package kr.vanilage.mcwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;
import java.util.UUID;

public class KillEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Entity damager = null;

        EntityDamageEvent entityDamageEvent = e.getEntity().getLastDamageCause();
        if ((entityDamageEvent != null) && !entityDamageEvent.isCancelled() && (entityDamageEvent instanceof EntityDamageByEntityEvent)) {
            damager = ((EntityDamageByEntityEvent) entityDamageEvent).getDamager();

            if (damager instanceof Projectile) {
                LivingEntity shooter = (LivingEntity) ((Projectile) damager).getShooter();
                if (shooter != null) damager = shooter;
            }
        }

        if (damager != null && damager.getType().equals(EntityType.PLAYER)) {
//            ((Player) damager).setMaxHealth(((Player) damager).getMaxHealth() + 1);

            Player pDamager = (Player) damager;

            for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                if (i != null) {
                    if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                        for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                            if (j.equals(pDamager.getUniqueId().toString())) {
                                if (Objects.requireNonNull(MCWar.config.getConfigurationSection("final")).contains(e.getPlayer().getUniqueId().toString())) {
                                    MCWar.config.set("teams." + i + ".member." + e.getPlayer().getUniqueId(), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
