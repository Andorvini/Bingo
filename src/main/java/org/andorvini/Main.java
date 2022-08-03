package org.andorvini;

import org.andorvini.Commands.AddItem;
import org.andorvini.Commands.ControlGUI;
import org.andorvini.Commands.Debug;
import org.andorvini.Commands.DebugStop;
import org.andorvini.EventHandlers.SimpleEventHandler;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public static ArrayList<ItemStack> items = new ArrayList<>();

    public static Main plugin;

    @Override
    public void onEnable() {
        
        // Variables and Arrays
        plugin = this;
        ItemStack dirt = new ItemStack(Material.DIRT);
        ItemStack stone = new ItemStack(Material.STONE);

        // Some Things
        items.add(dirt);
        items.add(stone);

        // Config Files
        getConfig().addDefault("items",items);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Commands
        getServer().getPluginCommand("controlpanel").setExecutor(new ControlGUI(this));
        getServer().getPluginCommand("additem").setExecutor(new AddItem(this));
        getServer().getPluginCommand("debug").setExecutor(new Debug());
        getServer().getPluginCommand("debugstop").setExecutor(new DebugStop());

        // EventHadlers
        getServer().getPluginManager().registerEvents(new SimpleEventHandler(this),this);

    }
}
