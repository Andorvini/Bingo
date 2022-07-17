package org.andorvini;

import org.andorvini.Commands.ControlGUI;
import org.andorvini.EventHandlers.SimpleEventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Variables and Arrays
        ArrayList<ItemStack> items = new ArrayList<>();
        ItemStack dirt = new ItemStack(Material.DIRT);

        // Some Things
        items.add(dirt);

        // Config Files
        getConfig().addDefault("items",items);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Commands
        getServer().getPluginCommand("controlpanel").setExecutor(new ControlGUI(this));

        // EventHadlers
        getServer().getPluginManager().registerEvents(new SimpleEventHandler(this),this);

        }
}
