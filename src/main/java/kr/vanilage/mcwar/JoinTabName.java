package kr.vanilage.mcwar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinTabName implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (MCWar.config.getConfigurationSection("teams") != null) {
            for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                if (i != null) {
                    if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                        for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                            if (j.equals(p.getUniqueId().toString())) {
                                p.setPlayerListName(p.getName() + " [" + i + "]");
                                p.setDisplayName(p.getName() + " [" + i + "]");
                                p.setCustomName(p.getName() + " [" + i + "]");
                            }
                        }
                    }
                }
            }
        }
    }
}
