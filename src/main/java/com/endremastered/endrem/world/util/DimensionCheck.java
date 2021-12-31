package com.endremastered.endrem.world.util;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.mixin.accessor.StructuresConfigAccessorMixin;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
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

            if (!EndRemastered.whitelistedDimensions.contains(serverWorld.getRegistryKey().getValue().toString())) {

                Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());
                tempMap.keySet().remove(ERJigsawStructures.END_GATE);
                tempMap.keySet().remove(ERConfiguredStructure.END_CASTLE);
                tempMap.keySet().remove(ERJigsawStructures.ANCIENT_WITCH_HUT);
                ((StructuresConfigAccessorMixin) serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(tempMap);
            }

        });
    }
}
