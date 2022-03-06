package com.endremastered.endrem.util;

import com.endremastered.endrem.config.ERConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public class MultiLocator {
    public List<String> getEyesStructureList() {
        return ERConfig.getData().ER_EYES.structureList;
    }

    public BlockPos getNearestPositionEyes(ServerWorld serverWorld, BlockPos playerPos) {
        // Temporary values
        int shortestStructureDistance = -1;
        BlockPos nearestStructurePos = null;

        for (String structureID : this.getEyesStructureList()) {
            // Verify if structure mod is loaded
            String structureModId = structureID.split(":")[0];
            if (FabricLoader.getInstance().isModLoaded(structureModId)) {
                // Check if structure id is valid
                Identifier structureIdentifier = new Identifier(structureID);
                if (Registry.STRUCTURE_FEATURE.containsId(structureIdentifier)) {
                    // Get distance from player
                    StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get(structureIdentifier);
                    assert structureFeature != null;
                    BlockPos structurePos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, structureFeature, playerPos, 100, false);

                    // Compare distance to previous minimum
                    if (structurePos != null) {
                        int structureDistance = ERUtils.getBlockDistance(structurePos, playerPos);
                        // if distance is smaller or default value is unchanged, set as new minimum
                        if (shortestStructureDistance > structureDistance || shortestStructureDistance == -1) {
                            shortestStructureDistance = structureDistance;
                            nearestStructurePos = structurePos;
                        }
                    }

                }
            }
        }

        return nearestStructurePos;
    }

    public List<String> getMapStructureList() {
        return ERConfig.getData().ER_MAP.structureList;
    }

    public BlockPos getNearestPositionMap(ServerWorld serverWorld, BlockPos playerPos) {
        // Temporary values
        int shortestStructureDistance = -1;
        BlockPos nearestStructurePos = null;

        for (String structureID : this.getMapStructureList()) {
            // Verify if structure mod is loaded
            String structureModId = structureID.split(":")[0];
            if (FabricLoader.getInstance().isModLoaded(structureModId)) {
                // Check if structure id is valid
                Identifier structureIdentifier = new Identifier(structureID);
                if (Registry.STRUCTURE_FEATURE.containsId(structureIdentifier)) {
                    // Get distance from player
                    StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get(structureIdentifier);
                    assert structureFeature != null;
                    BlockPos structurePos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, structureFeature, playerPos, 100, false);

                    // Compare distance to previous minimum
                    if (structurePos != null) {
                        int structureDistance = ERUtils.getBlockDistance(structurePos, playerPos);
                        // if distance is smaller or default value is unchanged, set as new minimum
                        if (shortestStructureDistance > structureDistance || shortestStructureDistance == -1) {
                            shortestStructureDistance = structureDistance;
                            nearestStructurePos = structurePos;
                        }
                    }

                }
            }
        }

        return nearestStructurePos;
    }
}
