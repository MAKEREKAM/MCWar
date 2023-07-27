package kr.vanilage.mcwar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (MCWar.config.getConfigurationSection("teams") != null) {
            for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                if (i != null) {
                    if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                        for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                            if (j.equals(p.getUniqueId().toString())) {
                                for (String k : MCWar.config.getConfigurationSection("beacons").getKeys(false)) {
                                    if (k != null) {
                                        if (MCWar.config.getString("beacons." + k + ".team").equals(i)) {
                                            int x = Integer.parseInt(k.split(" ")[0]);
                                            int y = Integer.parseInt(k.split(" ")[1]);
                                            int z = Integer.parseInt(k.split(" ")[2]);

                                            e.setRespawnLocation(new Location(Bukkit.getWorld("world"), x, y, z));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
