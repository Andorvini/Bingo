package org.andorvini;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Random;

public class GameController {

    public static void GameStart(Collection<? extends Player> players, World world) {

        for (Player onlinePlayer : players) {
            while (true) {
                double x = Math.random() * 2000 - 1000;
                double y = new Random().nextInt(256);
                double z = Math.random() * 2000 - 1000;

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
