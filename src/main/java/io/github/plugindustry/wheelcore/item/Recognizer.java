package io.github.plugindustry.wheelcore.item;

import io.github.plugindustry.wheelcore.interfaces.Interactive;
import io.github.plugindustry.wheelcore.interfaces.item.ItemBase;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.PlayerUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class Recognizer implements ItemBase, Interactive {
    public boolean onInteract(Player player, Action action, ItemStack tool, Block block, InteractActor actor) {
        if (actor == InteractActor.ITEM && block != null && action == Action.RIGHT_CLICK_BLOCK) {
            StringBuilder builder = new StringBuilder();
            builder.append(block.getType().name());
            if (MainManager.hasBlock(block.getLocation())) {
                builder.append(' ');
                builder.append(MainManager.getIdFromInstance(MainManager.getBlockInstance(block.getLocation())));
                builder.append(' ');
                builder.append(MainManager.getBlockData(block.getLocation()));
            }
            PlayerUtil.sendActionBar(player, builder.toString());
        }

        return false;
    }
}
