package com.endremastered.endrem.mixin.accessor;

import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StructuresConfig.class)
public interface StructuresConfigAccessorMixin {

    @Accessor("structures")
    public void setStructures(Map<StructureFeature<?>, StructureConfig> structures);

}
