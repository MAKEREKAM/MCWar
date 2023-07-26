package kr.vanilage.mcwar;

import kr.vanilage.mcwar.beacon.BreakBeacon;
import kr.vanilage.mcwar.beacon.InfoBeacon;
import kr.vanilage.mcwar.beacon.PlaceBeacon;
import kr.vanilage.mcwar.cmd.InitTeam;
import kr.vanilage.mcwar.cmd.JoinTeam;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public final class MCWar extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this, "deepslate"), new ItemStack(Material.REINFORCED_DEEPSLATE));
        recipe.addIngredient(Material.BEACON);
        Bukkit.addRecipe(recipe);

        ItemStack maxHealth = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta maxHealthMeta = maxHealth.getItemMeta();
        maxHealthMeta.setDisplayName(ChatColor.YELLOW + "체력 증가 토템");
        maxHealthMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "general.maxHealth", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
        maxHealth.setItemMeta(maxHealthMeta);
        ShapedRecipe maxHealthRecipe = new ShapedRecipe(new NamespacedKey(this, "max_health"), maxHealth);
        maxHealthRecipe.shape("DDD", "DTD", "DDD");
        maxHealthRecipe.setIngredient('D', Material.DIAMOND);
        maxHealthRecipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        Bukkit.addRecipe(maxHealthRecipe);

        this.getCommand("팀생성").setExecutor(new InitTeam());
        this.getCommand("팀생성").setTabCompleter(new TabCompleter() {
            @Override
            public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
                return null;
            }
        });
        this.getCommand("팀가입").setExecutor(new JoinTeam());
        this.getCommand("팀가입").setTabCompleter(new TabCompleter() {
            @Override
            public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
                return null;
            }
        });

        Bukkit.getPluginManager().registerEvents(new PlaceBeacon(), this);
        Bukkit.getPluginManager().registerEvents(new BreakBeacon(), this);
        Bukkit.getPluginManager().registerEvents(new InfoBeacon(), this);
        Bukkit.getPluginManager().registerEvents(new KillHP(), this);
        Bukkit.getPluginManager().registerEvents(new Upgrade(), this);
        Bukkit.getPluginManager().registerEvents(new UpgradeSkill(), this);
        Bukkit.getPluginManager().registerEvents(new JoinTabName(), this);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                if (config.getConfigurationSection("beacons") != null) {
                    for (String i : config.getConfigurationSection("beacons").getKeys(false)) {
                        if (i != null) {
                            String[] loc = i.split(" ");
                            int x = Integer.parseInt(loc[0]);
                            int y = Integer.parseInt(loc[1]);
                            int z = Integer.parseInt(loc[2]);

                            Bukkit.getWorld("world").spawnParticle(Particle.GLOW_SQUID_INK, new Location(Bukkit.getWorld("world"), x + 0.5, y, z + 0.5), 2500, 0, 100, 0, 0, null, true);
                        }
                    }
                }
            }
        }, 0, 1);
    }
}
