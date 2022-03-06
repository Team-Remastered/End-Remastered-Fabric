package com.endremastered.endrem.mixin;

import com.endremastered.endrem.config.ERConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)

//TODO: Add a config option for the methods
public class EnderEyeItemMixin {
    @Inject(method = "useOnBlock",
        at = @At(value = "HEAD"),
        cancellable = true)
    private void DisableUsingEnderEyes(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!ERConfig.getData().ENDER_EYES_ENABLED) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }

    @Inject(method = "use",
        at = @At(value = "HEAD"),
        cancellable = true)
    private void DisableThrowingEnderEyes(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!ERConfig.getData().ENDER_EYES_ENABLED) {
            ItemStack itemStack = user.getStackInHand(hand);
            cir.setReturnValue(TypedActionResult.pass(itemStack));
        }
    }
}
