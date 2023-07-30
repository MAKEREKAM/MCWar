package kr.vanilage.mcwar.cmd;

import kr.vanilage.mcwar.MCWar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class JoinTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                if (MCWar.config.getConfigurationSection("teams") != null) {
                    for (String i : MCWar.config.getConfigurationSection("teams").getKeys(false)) {
                        if (i != null) {
                            if (MCWar.config.getConfigurationSection("teams." + i + ".member") != null) {
                                for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                                    if (j.equals(p.getUniqueId().toString())) {
                                        p.sendMessage(ChatColor.RED + "이미 다른 팀의 구성원입니다.");
                                        return false;
                                    }
                                }
                            }

                            if (i.equals(args[0])) {
                                MCWar.config.set("teams." + i + ".member." + p.getUniqueId(), 0);

                                if (MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false).size() >= 3) {
                                    p.sendMessage(ChatColor.RED + "팀 최대 인원은 3명입니다.");
                                    return false;
                                }

                                if (MCWar.config.getConfigurationSection("final").getKeys(false).contains(p.getUniqueId())) {
                                    p.sendMessage(ChatColor.RED + "팀의 신호기가 부서져 팀에 들어갈 수 없습니다.");
                                    return false;
                                }

                                for (String j : MCWar.config.getConfigurationSection("teams." + i + ".member").getKeys(false)) {
                                    if (Bukkit.getPlayer(UUID.fromString(j)) != null) {
                                        Bukkit.getPlayer(UUID.fromString(j)).sendMessage(ChatColor.GREEN + p.getName() + "님이 팀에 합류했습니다.");
                                        p.setPlayerListName(p.getName() + " [" + i + "]");
                                        p.setDisplayName(p.getName() + " [" + i + "]");
                                        p.setCustomName(p.getName() + " [" + i + "]");
                                        MCWar.config.set("teams." + i + ".member." + p.getUniqueId(), 0);
                                        MCWar.getPlugin(MCWar.class).saveConfig();
                                        return false;
                                    }
                                }
                            }
                        }
                    }

                    p.sendMessage(ChatColor.RED + "팀이 존재하지 않습니다.");
                }
            }

            else {
                p.sendMessage(ChatColor.RED + "잘못된 형식입니다.");
            }
        }
        return false;
    }
}
