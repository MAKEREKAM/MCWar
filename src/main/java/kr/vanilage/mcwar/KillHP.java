package kr.vanilage.mcwar;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillHP implements Listener {
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
            ((Player) damager).setMaxHealth(((Player) damager).getMaxHealth() + 1);
        }
    }
}
