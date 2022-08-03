package org.andorvini.EventHandlers;

import org.andorvini.Commands.ControlGUI;
import org.andorvini.Configuration;
import org.andorvini.GameController;
import org.andorvini.ItemListLoad;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SimpleEventHandler implements Listener {

    private final JavaPlugin plugin;

    public SimpleEventHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    Inventory settingsInventory = Bukkit.createInventory(null, 27, "Settings");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent electronicArts) {

        ArrayList<ItemStack> items = (ArrayList<ItemStack>) plugin.getConfig().get("items");

        ItemStack listPaper = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = listPaper.getItemMeta();
        paperMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Items List");
        listPaper.setItemMeta(paperMeta);

        ItemStack randomToogleComparator = new ItemStack(Material.COMPARATOR);
        ItemMeta comparatorMeta = randomToogleComparator.getItemMeta();
        comparatorMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Toogle Random Item Choosing");

        if (Configuration.randomChoosing) {
            comparatorMeta.setLore(new ArrayList<String>(Arrays.asList(new String[]{ChatColor.YELLOW.toString() + ChatColor.BOLD + "Choosing randomly"})));
        } else if (!Configuration.randomChoosing) {
            comparatorMeta.setLore(new ArrayList<String>(Arrays.asList(new String[]{ChatColor.RED.toString() + ChatColor.BOLD + "Admin is choosing"})));
        }

        randomToogleComparator.setItemMeta(comparatorMeta);

        HumanEntity whoClicked = electronicArts.getWhoClicked();
        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();

        if (electronicArts.getInventory().equals(ControlGUI.controlGUI)) {

            electronicArts.setCancelled(true);
            Timer gameStartTimer = new Timer();

            if (electronicArts.getSlot() == 13) {

                whoClicked.closeInventory();

                gameStartTimer.scheduleAtFixedRate(new TimerTask() {

                    int i = 10;

                    @Override
                    public void run() {
                        if (i == 5) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 5");
                        } else if (i == 4) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 4");
                        } else if (i == 3) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 3");
                        } else if (i == 2) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 2");
                        } else if (i == 1) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 1");
                        } else if (i == 0) {
                            plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Игра начинается");

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    GameController.GameStart(players, plugin.getServer().getWorld("world"));
                                }
                            }.runTask(plugin);

                        } else if (i == -1) {
                            gameStartTimer.cancel();
                        }
                        i--;
                    }

                }, 0, 1000);
            } else if (electronicArts.getSlot() == 12) {

                settingsInventory.setItem(10,listPaper);
                settingsInventory.setItem(11,randomToogleComparator);

                whoClicked.openInventory(settingsInventory);

            }
        } else if (electronicArts.getInventory().equals(settingsInventory)) {
            electronicArts.setCancelled(true);

            if (electronicArts.getSlot() == 10) {
                ItemListLoad.openItemListInventory(whoClicked, items);
            } else if (electronicArts.getSlot() == 11) {

                if (Configuration.randomChoosing) {
                    Configuration.randomChoosing = false;
                    comparatorMeta.setLore(new ArrayList<String>(
                            Arrays.asList(new String[]{ChatColor.YELLOW.toString() + ChatColor.BOLD + "Admin is choosing"})
                    ));
                    settingsInventory.setItem(11,randomToogleComparator);
                    whoClicked.openInventory(settingsInventory);
                } else if (!Configuration.randomChoosing) {
                    Configuration.randomChoosing = true;
                    comparatorMeta.setLore(new ArrayList<String>(
                            Arrays.asList(new String[]{ChatColor.RED.toString() + ChatColor.BOLD + "Choosing randomly"}))
                    );
                    settingsInventory.setItem(11,randomToogleComparator);
                    whoClicked.openInventory(settingsInventory);
                }

            }

        } else if (electronicArts.getInventory().equals(ItemListLoad.itemListInventory)) {
            electronicArts.setCancelled(true);
        }
    }
}