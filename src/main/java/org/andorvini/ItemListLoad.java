package org.andorvini;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ItemListLoad {

    //public static JavaPlugin plugin;

    public static Inventory itemListInventory = Bukkit.createInventory(null,54,"Item List");

    public static void openItemListInventory(HumanEntity whoClicked , ArrayList<ItemStack> items) {

        int i = 0;
        int itemsSize = items.size();

        whoClicked.openInventory(itemListInventory);
        for (ItemStack item : items) {
            if (i == itemsSize) {
                break;
            } else {
                itemListInventory.setItem(i,item);
            }
            i++;
        }
    }

}
