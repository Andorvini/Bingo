package org.andorvini.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugStop implements CommandExecutor {

    public static boolean debugStop;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        debugStop = true;

        return true;
    }
}
