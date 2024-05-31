package me.stormcph;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class DisableEChest implements ModInitializer {
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient && hitResult.getBlockPos() != null) {
                if (world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.ENDER_CHEST) {
                    Text redText = Text.literal("Enderchests are disabled!").styled(style -> style.withColor(TextColor.fromRgb(0xFF5555)));
                    player.sendMessage(redText, false);
                    return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
}