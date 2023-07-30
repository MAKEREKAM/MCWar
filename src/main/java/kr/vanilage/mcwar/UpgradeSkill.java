package kr.vanilage.mcwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.List;
import java.util.Queue;

public class UpgradeSkill implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getDamager();
            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore() != null) {
                List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();

                if (lore.contains(ChatColor.YELLOW + "★☆☆☆☆")) {
                    e.setDamage((e.isCritical()) ? e.getDamage() + 1.5 : e.getDamage() + 1);
                }

                if (lore.contains(ChatColor.YELLOW + "★★☆☆☆")) {
                    e.setDamage((e.isCritical()) ? e.getDamage() + 3 : e.getDamage() + 2);
                }

                if (lore.contains(ChatColor.YELLOW + "★★★☆☆")) {
                    e.setDamage((e.isCritical()) ? e.getDamage() + 4.5 : e.getDamage() + 3);
                }

                if (lore.contains(ChatColor.YELLOW + "★★★★☆")) {
                    e.setDamage((e.isCritical()) ? e.getDamage() + 6 : e.getDamage() + 4);
                }

                if (lore.contains(ChatColor.YELLOW + "★★★★★")) {
                    e.setDamage((e.isCritical()) ? e.getDamage() + 7.5 : e.getDamage() + 5);
                }
            }
        }

        if (e.getDamager() instanceof Projectile) {
            if (e.getDamager().getScoreboardTags().contains("damage1")) {
                e.setDamage(e.getDamage() + 1);
            }

            if (e.getDamager().getScoreboardTags().contains("damage2")) {
                e.setDamage(e.getDamage() + 2);
            }

            if (e.getDamager().getScoreboardTags().contains("damage3")) {
                e.setDamage(e.getDamage() + 3);
            }

            if (e.getDamager().getScoreboardTags().contains("damage4")) {
                e.setDamage(e.getDamage() + 4);
            }

            if (e.getDamager().getScoreboardTags().contains("damage5")) {
                e.setDamage(e.getDamage() + 5);
            }

            if (e.getDamager().getScoreboardTags().contains("damage6")) {
                e.setDamage(e.getDamage() + 6);
            }
        }
    }

    @EventHandler
    public void onProjectile(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() != null) {
            if (((LivingEntity) e.getEntity().getShooter()).getType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getEntity().getShooter();
                if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore() != null) {
                    List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();

                    if (lore.contains(ChatColor.YELLOW + "★☆☆☆☆")) {
                        e.getEntity().addScoreboardTag("damage1");
                    }

                    if (lore.contains(ChatColor.YELLOW + "★★☆☆☆")) {
                        e.getEntity().addScoreboardTag("damage2");
                    }

                    if (lore.contains(ChatColor.YELLOW + "★★★☆☆")) {
                        e.getEntity().addScoreboardTag("damage3");
                    }

                    if (lore.contains(ChatColor.YELLOW + "★★★★☆")) {
                        e.getEntity().addScoreboardTag("damage4");
                    }

                    if (lore.contains(ChatColor.YELLOW + "★★★★★")) {
                        e.getEntity().addScoreboardTag("damage5");
                    }

                    if (lore.contains(ChatColor.YELLOW + "★★★★★" + ChatColor.LIGHT_PURPLE + "★")) {
                        e.getEntity().addScoreboardTag("damage6");

                        for (int i = -180; i < 0; i += 20) {
                            Projectile arrow = p.getWorld().spawn(p.getEyeLocation(), e.getEntity().getClass());
                            arrow.setShooter(p);
                            arrow.setVelocity(e.getEntity().getVelocity().rotateAroundY(Math.toRadians(i)));
                            arrow.addScoreboardTag("nopick");
                        }

                        for (int i = 160; i > 0; i -= 20) {
                            Projectile arrow = p.getWorld().spawn(p.getEyeLocation(), e.getEntity().getClass());
                            arrow.setShooter(p);
                            arrow.setVelocity(e.getEntity().getVelocity().rotateAroundY(Math.toRadians(i)));
                            arrow.addScoreboardTag("nopick");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPickUp(ProjectileHitEvent e) {
        if (e.getEntity().getScoreboardTags().contains("nopick")) {
            e.getEntity().remove();
        }
    }
}
