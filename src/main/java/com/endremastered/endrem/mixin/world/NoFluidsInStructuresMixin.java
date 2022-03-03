package com.endremastered.endrem.mixin.world;

import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpringFeature.class)
public class NoFluidsInStructuresMixin {

    @Inject(
            method = "generate(Lnet/net/minecraft/world/gen/feature/util/FeatureContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void noFluidsInStructures(FeatureContext<SpringFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        if(context.getConfig().state.isIn(FluidTags.LAVA) || context.getConfig().state.isIn(FluidTags.WATER)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for(Direction face : Direction.Type.HORIZONTAL) {
                mutable.set(context.getOrigin()).move(face);
                ChunkSectionPos sectionPos = ChunkSectionPos.from(context.getOrigin());
                if (!context.getWorld().getStructures(sectionPos, ERStructures.END_CASTLE).isEmpty() || !context.getWorld().getStructures(sectionPos, ERStructures.END_GATE).isEmpty()) {
                    System.out.println("NO FLUIDS IN STRUCTURE");
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
