package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ERJigsawStructures {

    public static StructureFeature<StructurePoolFeatureConfig> END_GATE = new EndGate(StructurePoolFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> ANCIENT_WITCH_HUT = new AncientWitchHut(StructurePoolFeatureConfig.CODEC);


    public static void setupAndRegisterStructureFeatures() {
        /*END GATE*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("end_gate"), END_GATE)
                .step(GenerationStep.Feature.STRONGHOLDS)
                .defaultConfig(new StructureConfig(ERConfig.getData().END_GATE.averageDistance, ERConfig.getData().END_GATE.averageDistance - 30, 324894322))
                .register();

        /*ANCIENT_WITCH_HUT*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("ancient_witch_hut"), ANCIENT_WITCH_HUT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance, ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance - 30, 675594284))
                .adjustsSurface()
                .register();
    }
}
