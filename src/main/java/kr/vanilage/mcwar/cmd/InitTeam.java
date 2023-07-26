package kr.vanilage.mcwar.cmd;

import kr.vanilage.mcwar.MCWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InitTeam implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                if (MCWar.config.getConfigurationSection("teams") != null) {
                    for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                        if (i != null) {
                            if (i.equals(args[0])) {
                                p.sendMessage(ChatColor.RED + "이미 그 이름의 팀이 존재합니다.");
                                return false;
                            }

                            if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                                for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                                    if (j.equals(p.getUniqueId().toString())) {
                                        p.sendMessage(ChatColor.RED + "이미 다른 팀의 구성원입니다.");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }

                if ((!p.getWorld().getName().equals("world")) || p.getLocation().getBlockY() != 90) {
                    p.sendMessage(ChatColor.RED + "신호기는 오버월드의 Y좌표 90에만 설치 가능합니다.");
                    return false;
                }

                p.getLocation().getBlock().setType(Material.REINFORCED_DEEPSLATE);
                MCWar.config.set("teams." + args[0] + ".leader", p.getUniqueId().toString());
                MCWar.config.set("teams." + args[0] + ".member." + p.getUniqueId(), 0);
                MCWar.config.set("beacons." + String.format("%d 90 %d", p.getLocation().getBlockX(), p.getLocation().getBlockZ()) + ".team", args[0]);
                MCWar.getPlugin(MCWar.class).saveConfig();
                Bukkit.broadcastMessage(ChatColor.GREEN + args[0] + " 팀이 생성되었습니다.");
                p.setPlayerListName(p.getName() + " [" + args[0] + "]");
                p.setDisplayName(p.getName() + " [" + args[0] + "]");
                p.setCustomName(p.getName() + " [" + args[0] + "]");
            }

            else {
                p.sendMessage(ChatColor.RED + "잘못된 형식입니다.");
            }
        }
        return false;
    }
}
