package com.endremastered.endrem.mixin.world;

import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LakeFeature.class)
public class NoLakesInStructuresMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;down(I)Lnet/minecraft/util/math/BlockPos;"), method = "generate", cancellable = true)
    private void noLakesInStructures(FeatureContext<SingleStateFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        ChunkSectionPos chunkPos = ChunkSectionPos.from(context.getOrigin());

        if (context.getWorld().getStructures(chunkPos, ERStructures.END_GATE).stream().findAny().isPresent() || context.getWorld().getStructures(chunkPos, ERStructures.END_CASTLE).stream().findAny().isPresent()) {
            cir.setReturnValue(false);
            System.out.println("NO LAKE IN THE STRUCTURE");
        }
    }
}
