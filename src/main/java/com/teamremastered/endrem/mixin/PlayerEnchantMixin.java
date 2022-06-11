package com.teamremastered.endrem.mixin;

import com.teamremastered.endrem.registry.ERItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(EnchantmentScreenHandler.class)
public class PlayerEnchantMixin {

    @Inject(method = "onButtonClick", at = @At(value = "RETURN", ordinal = 2))
    private void isEnchanting(final PlayerEntity player, final int id, final CallbackInfoReturnable<Boolean> info) {
        Random random = new Random();
        int maxValue = 120;
        int randomNumber = random.nextInt(maxValue);
        if (!player.world.isClient && player != null) {
            if (randomNumber == 69) {
        player.getInventory().insertStack(new ItemStack(ERItems.CRYPTIC_EYE));
            }
        }
    }
}
