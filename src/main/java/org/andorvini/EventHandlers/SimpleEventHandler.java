package org.andorvini.EventHandlers;

import org.andorvini.Commands.ControlGUI;
import org.andorvini.GameController;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleEventHandler implements Listener {

    private final JavaPlugin plugin;

    public SimpleEventHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();

        if (e.getInventory().equals(ControlGUI.controlGUI)) {
            Timer gameStartTimer = new Timer();
            e.setCancelled(true);
            HumanEntity whoClicked = e.getWhoClicked();
            if (e.getSlot() == 13) {

                whoClicked.closeInventory();
                gameStartTimer.scheduleAtFixedRate(new TimerTask() {

                    int i = 10;

                    @Override
                    public void run() {
                        if (i == 5) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 5");
                        }
                        else if (i == 4) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 4");
                        }
                        else if (i == 3) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 3");
                        }
                        else if (i == 2) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 2");
                        }
                        else if (i == 1) {
                            plugin.getServer().broadcastMessage(ChatColor.GREEN + "Игра начинается через 1");
                        }
                        else if (i == 0) {
                            plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Игра начинается");
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    GameController.GameStart(players,plugin.getServer().getWorld("world"));
                                }
                            }.runTask(plugin);
                        }
                        else if (i == -1) {
                            gameStartTimer.cancel();
                        }
                        i--;
                    }

                },0,1000);
            } else if (e.getSlot() == 12) {

            }
        }
    }
}
