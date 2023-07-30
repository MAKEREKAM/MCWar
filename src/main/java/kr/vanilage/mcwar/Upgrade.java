package kr.vanilage.mcwar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Upgrade implements Listener {
    Random rd = new Random();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getPlayer().isSneaking() && e.getClickedBlock().getType().equals(Material.SMITHING_TABLE)) {
                e.setCancelled(true);
                e.getPlayer().openInventory(new InventoryGenerator().getInventory());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClick().isLeftClick()) {
            if (e.getView().getTitle().contains(ChatColor.GREEN + "아이템 강화")) {
                if (e.getRawSlot() == 7) {
                    e.setCancelled(true);
                    if (e.getClickedInventory().getItem(1) != null && e.getClickedInventory().getItem(2) != null && Objects.requireNonNull(e.getClickedInventory().getItem(2).getItemMeta()).getDisplayName().equals(ChatColor.YELLOW + "강화된 청금석")) {
                        ItemStack item = e.getClickedInventory().getItem(1);
                        String itemName = item.getType().name();
                        if (itemName.endsWith("_SWORD") || itemName.endsWith("_AXE") || itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS") || itemName.equals("BOW") || itemName.equals("TRIDENT") || itemName.equals("CROSSBOW")) {
                            e.getClickedInventory().getItem(2).setAmount(e.getClickedInventory().getItem(2).getAmount() - 1);

                            if (item.getItemMeta() == null || item.getItemMeta().getLore() == null) {
                                int rdInt = rd.nextInt(10);
                                if (rdInt < 9) {
                                    ItemMeta meta = item.getItemMeta();
                                    List<String> lore = new ArrayList<>();
                                    lore.add(ChatColor.YELLOW + "★☆☆☆☆");
                                    meta.setLore(lore);
                                    item.setItemMeta(meta);
                                    if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);
                                    }
                                    e.getClickedInventory().setItem(1, item);
                                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                }

                                return;
                            } else {
                                if (item.getItemMeta().getLore().contains(ChatColor.YELLOW + "★☆☆☆☆")) {
                                    int rdInt = rd.nextInt(10);
                                    if (rdInt < 8) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★☆☆☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                    }

                                    else if (rdInt == 8) {
                                        ItemMeta meta = item.getItemMeta();
                                        meta.setLore(null);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_DESTROY, 100, 0.7F);
                                    }

                                    return;
                                }

                                if (item.getItemMeta().getLore().contains(ChatColor.YELLOW + "★★☆☆☆")) {
                                    int rdInt = rd.nextInt(20);
                                    if (rdInt < 10) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★★☆☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                    }

                                    else if (rdInt > 16) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★☆☆☆☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,1);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_DESTROY, 100, 0.7F);
                                    }

                                    else if (rdInt == 16) {
                                        e.getClickedInventory().setItem(1, null);
                                        Bukkit.broadcastMessage(ChatColor.RED + e.getWhoClicked().getName() + "님의 도구가 파괴되었습니다!");
                                    }

                                    return;
                                }

                                if (item.getItemMeta().getLore().contains(ChatColor.YELLOW + "★★★☆☆")) {
                                    int rdInt = rd.nextInt(10);
                                    if (rdInt < 4) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★★★☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                    }

                                    else if (rdInt == 8) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★☆☆☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_DESTROY, 100, 0.7F);
                                    }

                                    else if (rdInt == 9) {
                                        e.getClickedInventory().setItem(1, null);
                                        Bukkit.broadcastMessage(ChatColor.RED + e.getWhoClicked().getName() + "님의 도구가 파괴되었습니다!");
                                    }

                                    return;
                                }

                                if (item.getItemMeta().getLore().contains(ChatColor.YELLOW + "★★★★☆")) {
                                    int rdInt = rd.nextInt(20);
                                    if (rdInt < 5) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★★★★");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                    }

                                    else if (rdInt == 15 || rdInt == 16) {
                                        ItemMeta meta = item.getItemMeta();
                                        List<String> lore = new ArrayList<>();
                                        lore.add(ChatColor.YELLOW + "★★★☆☆");
                                        meta.setLore(lore);
                                        item.setItemMeta(meta);
                                        if (itemName.endsWith("_HELMET") || itemName.endsWith("_CHESTPLATE") || itemName.endsWith("_LEGGINGS") || itemName.endsWith("_BOOTS")) {
                                            item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                                            item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,3);
                                        }
                                        e.getClickedInventory().setItem(1, item);
                                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_DESTROY, 100, 0.7F);
                                    }

                                    else if (rdInt > 16) {
                                        e.getClickedInventory().setItem(1, null);
                                        Bukkit.broadcastMessage(ChatColor.RED + e.getWhoClicked().getName() + "님의 도구가 파괴되었습니다!");
                                    }

                                    return;
                                }

                                if (item.getItemMeta().getLore().contains(ChatColor.YELLOW + "★★★★★")) {
                                    if (item.getType().equals(Material.BOW) || item.getType().equals(Material.TRIDENT)) {
                                        int rdInt = rd.nextInt(20);
                                        if (rdInt == 0) {
                                            ItemMeta meta = item.getItemMeta();
                                            List<String> lore = new ArrayList<>();
                                            lore.add(ChatColor.YELLOW + "★★★★★" + ChatColor.LIGHT_PURPLE + "★");
                                            meta.setLore(lore);
                                            item.setItemMeta(meta);
                                            e.getClickedInventory().setItem(1, item);
                                            ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                                        }

                                        else if (rdInt > 9) {
                                            e.getClickedInventory().setItem(1, null);
                                            Bukkit.broadcastMessage(ChatColor.RED + e.getWhoClicked().getName() + "님의 도구가 파괴되었습니다!");
                                        } else {
                                            ItemMeta meta = item.getItemMeta();
                                            List<String> lore = new ArrayList<>();
                                            lore.add(ChatColor.YELLOW + "★★★★☆");
                                            meta.setLore(lore);
                                            item.setItemMeta(meta);
                                            e.getClickedInventory().setItem(1, item);
                                            ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_DESTROY, 100, 0.7F);
                                        }
                                    }

                                    return;
                                }
                            }
                        }
                    }
                }

                if (e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().contains(ChatColor.GREEN + "아이템 강화")) {
            if (e.getInventory().getItem(1) != null) e.getPlayer().getInventory().addItem(e.getInventory().getItem(1));
            if (e.getInventory().getItem(2) != null) e.getPlayer().getInventory().addItem(e.getInventory().getItem(2));
        }
    }
}
