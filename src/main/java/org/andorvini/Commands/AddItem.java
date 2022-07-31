package org.andorvini.Commands;

import org.andorvini.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class AddItem implements CommandExecutor {

    private final JavaPlugin plugin;

    public AddItem(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String pizda, String[] arguments) {
        ArrayList<ItemStack> itemsCopy = Main.items;

        if (!(commandSender instanceof Player)) { return true; }

        if (strings.length != 0) { return false; }

        if (!commandSender.isOp()) {
            commandSender.sendMessage(ChatColor.RED + "Вы не админ (не смешарик)");

            return true;
        }

        ItemStack heldItem = ((Player) commandSender).getItemInHand();
        Material heldItemMaterial = heldItem.getType();
        ItemStack heldItemNoAmount = new ItemStack(heldItemMaterial);

        if (heldItem.getType() == Material.AIR) {
            commandSender.sendMessage(ChatColor.RED + "В ваших руках пусто");
        } else {
            itemsCopy.add(heldItemNoAmount);
            plugin.getConfig().set("items",itemsCopy);
            commandSender.sendMessage(ChatColor.GREEN + "Добавлен предмет: " + heldItemMaterial);
        }

        return true;
    }

}
