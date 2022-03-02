package com.endremastered.endrem.world.util;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.mixin.accessor.StructuresConfigAccessorMixin;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructures;
import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public class DimensionCheck {

    public static void removeStructureSpawningFromSelectedDimension() {

        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld) -> {

            if (!ERConfig.getData().WHITELISTED_DIMENSIONS.contains(serverWorld.getRegistryKey().getValue().toString())) {

                Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());
                tempMap.keySet().remove(ERStructures.END_GATE);
                tempMap.keySet().remove(ERConfiguredStructures.END_CASTLE);
                tempMap.keySet().remove(ERStructures.ANCIENT_WITCH_HUT);
                ((StructuresConfigAccessorMixin) serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(tempMap);
            }

        });
    }
}
