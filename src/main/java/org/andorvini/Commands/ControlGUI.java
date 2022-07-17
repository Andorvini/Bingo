package org.andorvini.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ControlGUI implements CommandExecutor {

    public static Inventory controlGUI = Bukkit.createInventory(null,27,"Control Panel");

    private final JavaPlugin plugin;

    public ControlGUI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0) { return false; }

        Player sender = (Player) commandSender;

        ItemStack lever = new ItemStack(Material.LEVER);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName(ChatColor.GREEN + "Start game");
        lever.setItemMeta(leverMeta);
        controlGUI.setItem(13, lever);

        if (commandSender.isOp()) {

            sender.openInventory(controlGUI);

        } else {
            commandSender.sendMessage(ChatColor.RED + "Вы не админ");
        }
        return true;
    }

    public Inventory getControlGUI() {
        return controlGUI;
    }
}
