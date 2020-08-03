package com.industrialworld.world;

import com.industrialworld.interfaces.OreBase;
import com.industrialworld.manager.MainManager;
import com.industrialworld.utils.DebuggingLogger;
import org.bukkit.*;
import org.bukkit.block.Block;

import java.util.Random;

public class WorldGenMinable {
    private final OreBase ore;
    private final int numberOfBlocks;

    public WorldGenMinable(OreBase ore, int numberOfBlocks) {
        if (ore.isOre()) {
            this.ore = ore;
            this.numberOfBlocks = numberOfBlocks;
        } else
            throw new IllegalArgumentException();
    }

    public boolean generate(World worldIn, Random rand, Chunk chunk, Location blockLctn) {
        ChunkSnapshot snapshot = chunk.getChunkSnapshot();
        int x = blockLctn.getBlockX();
        int y = blockLctn.getBlockY();
        int z = blockLctn.getBlockZ();

        Block currentBlock = chunk.getBlock(x, y, z);

        if (snapshot.getBlockType(x, y, z).equals(Material.STONE)) {
            currentBlock.setType(this.ore.getMaterial());
            MainManager.addBlock(this.ore.getId(), currentBlock, null);
            DebuggingLogger.debug("Gen in " + (currentBlock.getLocation().getWorld() == null ?
                                               null :
                                               currentBlock.getLocation().getWorld().getName()) + " at " +
                                  (chunk.getX() << 4) + ", " + (chunk.getZ() << 4));
        } else {
            return false;
        }

        for (int i = 0; i < numberOfBlocks; i++) {  // Number of ore blocks in each ore
            switch (rand.nextInt(6)) {
                case 0:
                    x++;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    z++;
                    break;
                case 3:
                    x--;
                    break;
                case 4:
                    y = Math.max(y - 1, 5);
                    break;
                default:
                    z--;
                    break;
            }

            if (x < 0 || z < 0 || x >= 16 || z >= 16)
                continue;
            currentBlock = chunk.getBlock(x, y, z);

            if (snapshot.getBlockType(x, y, z).equals(Material.STONE)) {
                currentBlock.setType(this.ore.getMaterial());
                MainManager.addBlock(this.ore.getId(), currentBlock, null);
            }
        }
        return true;
    }
}
