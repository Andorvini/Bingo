package org.andorvini;

import org.andorvini.Commands.AddItem;
import org.andorvini.Commands.ControlGUI;
import org.andorvini.EventHandlers.SimpleEventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public static ArrayList<ItemStack> items = new ArrayList<>();

    @Override
    public void onEnable() {
        
        // Variables and Arrays
        ItemStack dirt = new ItemStack(Material.DIRT);
        ItemStack stone = new ItemStack(Material.STONE);

        // Some Things
        items.add(dirt);
        items.add(stone);

        // Config Files
        getConfig().addDefault("randomItemChoosing",true);
        getConfig().addDefault("items",items);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Commands
        getServer().getPluginCommand("controlpanel").setExecutor(new ControlGUI(this));
        getServer().getPluginCommand("additem").setExecutor(new AddItem(this));

        // EventHadlers
        getServer().getPluginManager().registerEvents(new SimpleEventHandler(this),this);

    }
}
