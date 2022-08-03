package org.andorvini;

import org.andorvini.Commands.Debug;
import org.andorvini.Commands.DebugStop;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;
import java.util.TimerTask;

public class Configuration {

    private static final JavaPlugin plugin = Main.plugin;

    public static boolean randomChoosing;

    public static void debug() {

        Timer debugTimer = new Timer();

        debugTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                plugin.getServer().getPlayer("andorvini").sendMessage(String.valueOf(randomChoosing));

                if (DebugStop.debugStop) {
                    debugTimer.cancel();
                    DebugStop.debugStop = false;
                }

            }
        },0,1000);
    }

}
