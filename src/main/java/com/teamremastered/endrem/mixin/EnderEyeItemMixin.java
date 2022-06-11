package com.teamremastered.endrem.mixin;

import com.teamremastered.endrem.config.ERConfigHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
@SuppressWarnings("unused")

//TODO: Add a config option for the methods
public class EnderEyeItemMixin {
    @Inject(method = "useOnBlock",
        at = @At(value = "HEAD"),
        cancellable = true)
    private void DisableUsingEnderEyes(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!ERConfigHandler.ENABLE_EYE_OF_ENDER) {
            cir.setReturnValue(ActionResult.PASS);
            context.getPlayer().sendMessage(Text.translatable("block.endrem.ender_eye.warning"), true);
        }
    }

    @Inject(method = "use",
        at = @At(value = "HEAD"),
        cancellable = true)
    private void DisableThrowingEnderEyes(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!ERConfigHandler.ENABLE_EYE_OF_ENDER) {
            ItemStack itemStack = user.getStackInHand(hand);
            cir.setReturnValue(TypedActionResult.pass(itemStack));
            user.sendMessage(Text.translatable("block.endrem.ender_eye.warning"), true);
        }
    }
}
