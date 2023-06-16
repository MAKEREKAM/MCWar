package kr.vanilage.mcwar.beacon;

import kr.vanilage.mcwar.MCWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class PlaceBeacon implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if (e.getBlock().getType().equals(Material.REINFORCED_DEEPSLATE)) {
            if (MCWar.config.getConfigurationSection("teams") != null) {
                for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                    if (i != null) {
                        if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                            for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                                if (j.equals(p.getUniqueId().toString())) {
                                    if ((!p.getWorld().getName().equals("world")) || e.getBlock().getY() != 90) {
                                        e.setCancelled(true);
                                        p.sendMessage(ChatColor.RED + "신호기는 오버월드의 Y좌표 90에만 설치 가능합니다.");
                                        return;
                                    }

                                    MCWar.config.set("beacons." + String.format("%d 90 %d.team", e.getBlock().getX(), e.getBlock().getZ()), i);
                                    MCWar.getPlugin(MCWar.class).saveConfig();

                                    for (String k : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                                        if (Bukkit.getPlayer(UUID.fromString(k)) != null) {
                                            Bukkit.getPlayer(UUID.fromString(k)).sendMessage(ChatColor.GREEN + "신호기가 설치되었습니다.");
                                        }
                                    }

                                    return;
                                }
                            }
                        }
                    }
                }
            }

            e.setCancelled(true);
        }
    }
}
