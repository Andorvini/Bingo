package org.andorvini;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GameController {

    private static final JavaPlugin plugin = Main.plugin;

    public static Inventory itemChoosingGUI = Bukkit.createInventory(null,45,"Choose an item:");

    public static void GameStart(Collection<? extends Player> players, World world, HumanEntity whoClicked) {

        if (!Configuration.randomChoosing) {

            ArrayList<ItemStack> items = (ArrayList<ItemStack>) plugin.getConfig().get("items");
            int i = 0;

            for (ItemStack item : items) {
                itemChoosingGUI.setItem(i, item);
                i++;
                if (i == items.size() || i == 45) {
                    break;
                }
            }

            whoClicked.openInventory(itemChoosingGUI);

        } else if (Configuration.randomChoosing) {
            int randomItem = new Random().nextInt(((ArrayList<ItemStack>)plugin.getConfig().get("items")).size());
            ItemStack item = (ItemStack) ((ArrayList<?>) plugin.getConfig().get("items")).get(randomItem);

            RunGame(item, players, world);
        }
    }

    public static void RunGame(ItemStack item, Collection<? extends Player> players, World world) {

        for (Player onlinePlayer : players) {
            while (true) {
                double x = Math.random() * 2000 - 1000;
                double y = new Random().nextInt(256);
                double z = Math.random() * 2000 - 1000;

                plugin.getServer().getPlayer("Andorvini").sendMessage(String.valueOf(item));

                Location locationRandom = new Location(world, x, y, z);
                Block highestBlock = world.getHighestBlockAt(locationRandom);
                int highestBlockY = highestBlock.getY();

                Location checkLocation = new Location(world, x, highestBlockY, z);
                Location locationToTp = new Location(world, x, highestBlockY + 1, z);

                if (world.getBlockAt(checkLocation).getType() != Material.WATER) {
                    onlinePlayer.teleport(locationToTp);
                    break;
                } else {
                    continue;
                }
            }
        }

    }
}
