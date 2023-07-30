package kr.vanilage.mcwar.beacon;

import kr.vanilage.mcwar.MCWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class BreakBeacon implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (e.getBlock().getType().equals(Material.REINFORCED_DEEPSLATE)) {
            if (MCWar.config.getConfigurationSection("beacons") != null) {
                for (String i : MCWar.config.getConfigurationSection("beacons").getKeys(false)) {
                    int bx = Integer.parseInt(i.split(" ")[0]);
                    int by = Integer.parseInt(i.split(" ")[1]);
                    int bz = Integer.parseInt(i.split(" ")[2]);
                    int x = e.getBlock().getX();
                    int y = e.getBlock().getY();
                    int z = e.getBlock().getZ();

                    if (bx == x && by == y && bz == z) {
                        String team = MCWar.config.getString("beacons." + i + ".team");
                        MCWar.config.set("beacons." + i, null);
                        MCWar.getPlugin(MCWar.class).saveConfig();

                        for (String j : MCWar.config.getConfigurationSection("teams." + team + ".member").getKeys(false)) {
                            if (Bukkit.getPlayer(UUID.fromString(j)) != null) Bukkit.getPlayer(UUID.fromString(j)).sendMessage(ChatColor.RED + "신호기가 파괴되었습니다.");
                        }

                        for (String j : MCWar.config.getConfigurationSection("beacons").getKeys(false)) {
                            if (MCWar.config.getString("beacons." + j + ".team").equals(team)) {
                                return;
                            }
                        }

                        Bukkit.broadcastMessage(ChatColor.RED + team + " 팀이 멸망했습니다.");

                        for (String j : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                            for (String k : MCWar.config.getConfigurationSection("teams." + j + ".member").getKeys(false)) {
                                if (p.getUniqueId().toString().equals(k)) {
                                    for (String l : MCWar.config.getConfigurationSection("teams." + team + ".member").getKeys(false)) {
                                        MCWar.config.set("final." + l, 0);
                                        MCWar.getPlugin(MCWar.class).saveConfig();
                                    }

                                    MCWar.config.set("teams." + team, null);
                                    MCWar.getPlugin(MCWar.class).saveConfig();
                                    return;
                                }
                            }
                        }

                        MCWar.config.set("teams." + team, null);
                        MCWar.getPlugin(MCWar.class).saveConfig();
                    }
                }
            }
        }
    }
}
