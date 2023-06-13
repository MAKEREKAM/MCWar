package kr.vanilage.mcwar;

import kr.vanilage.mcwar.beacon.PlaceBeacon;
import kr.vanilage.mcwar.cmd.InitTeam;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCWar extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this, "deepslate"), new ItemStack(Material.REINFORCED_DEEPSLATE));
        recipe.addIngredient(Material.BEACON);
        Bukkit.addRecipe(recipe);

        this.getCommand("팀생성").setExecutor(new InitTeam());
        this.getCommand("팀생성").setTabCompleter(null);

        Bukkit.getPluginManager().registerEvents(new PlaceBeacon(), this);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                if (config.getConfigurationSection("beacons") != null) {
                    for (String i : config.getConfigurationSection("beacons").getKeys(false)) {
                        if (i != null) {
                            String[] loc = i.split(" ");
                            int x = Integer.parseInt(loc[0]);
                            int y = 90;
                            int z = Integer.parseInt(loc[2]);

                            Bukkit.getWorld("world").spawnParticle(Particle.GLOW_SQUID_INK, new Location(Bukkit.getWorld("world"), x + 0.5, y, z + 0.5), 2500, 0, 100, 0, 0, null, true);
                        }
                    }
                }
            }
        }, 0, 1);
    }
}
