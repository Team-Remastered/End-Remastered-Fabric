package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.processor.WaterloggedProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.registry.Registry;

public class ERProcessors {
    public static StructureProcessorType<WaterloggedProcessor> WATERLOGGED_PROCESSOR = () -> WaterloggedProcessor.CODEC;

    public static void init() {
        registerProcessors();
    }

    private static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, EndRemastered.createIdentifier("waterlogged_processor"), WATERLOGGED_PROCESSOR);
    }
}
