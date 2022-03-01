package com.endremastered.endrem.world.ERStructureConfig;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.world.structures.AncientWitchHut;
import com.endremastered.endrem.world.structures.EndCastle;
import com.endremastered.endrem.world.structures.EndCastlePieces;
import com.endremastered.endrem.world.structures.EndGate;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;

public class ERStructures {

    public static StructureFeature<StructurePoolFeatureConfig> END_GATE = new EndGate(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> ANCIENT_WITCH_HUT = new AncientWitchHut(StructurePoolFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {
        /*END GATE*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("end_gate"), END_GATE)
                .step(GenerationStep.Feature.STRONGHOLDS)
                .defaultConfig(new StructureConfig(ERConfig.getData().END_GATE.averageDistance, ERConfig.getData().END_GATE.averageDistance - 30, 324894322))
                .register();

        /*ANCIENT WITCH HUT*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("ancient_witch_hut"), ANCIENT_WITCH_HUT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance, ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance - 30, 675594284))
                .adjustsSurface()
                .register();
    }
}
