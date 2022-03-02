package com.endremastered.endrem.mixin.world;

import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(SpringFeature.class)
//public class NoLavaInStructuresMixin {
//    @Inject(
//            method = "generate",
//            at = @At(value = "HEAD"),
//            cancellable = true
//    )
//    private void noLavaInStructures(FeatureContext<SingleStateFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
//
//        if (context.getConfig().state.getFluidState().isIn(FluidTags.LAVA)) {
//            BlockPos.Mutable mutable = new BlockPos.Mutable();
//            ChunkSectionPos chunkPos;
//            for (Direction face : Direction.Type.HORIZONTAL) {
//                mutable.set(context.getOrigin()).move(face);
//                chunkPos = ChunkSectionPos.from(mutable);
//
//                if (context.getWorld().getStructures(chunkPos, ERJigsawStructures.END_GATE).findAny().isPresent() || context.getWorld().getStructures(chunkPos, ERConfiguredStructure.END_CASTLE).findAny().isPresent()) {
//                    cir.setReturnValue(false);
//                }
//            }
//        }
//    }
//}
