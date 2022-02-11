package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ERJigsawStructures {

    public static StructureFeature<DefaultFeatureConfig> END_GATE = new EndGate(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> ANCIENT_WITCH_HUT = new AncientWitchHut(DefaultFeatureConfig.CODEC);


    public static void setupAndRegisterStructureFeatures() {
        /*END GATE*/
        FabricStructureBuilder<DefaultFeatureConfig, StructureFeature<DefaultFeatureConfig>> end_gate_builder = FabricStructureBuilder.create(EndRemastered.createIdentifier("end_gate"), END_GATE)
                .step(GenerationStep.Feature.STRONGHOLDS)
                .defaultConfig(new StructureConfig(ERConfig.getData().END_GATE.averageDistance, ERConfig.getData().END_GATE.averageDistance - 30, 324894322))
                .superflatFeature(END_GATE.configure(FeatureConfig.DEFAULT));

        if (ERConfig.getData().END_GATE.terraforming) {
            end_gate_builder.adjustsSurface();
        }

        end_gate_builder.register();

        /*ANCIENT_WITCH_HUT*/
        FabricStructureBuilder<DefaultFeatureConfig, StructureFeature<DefaultFeatureConfig>> hut_builder =FabricStructureBuilder.create(EndRemastered.createIdentifier("ancient_witch_hut"), ANCIENT_WITCH_HUT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance, ERConfig.getData().ANCIENT_WITCH_HUT.averageDistance - 30, 675594284))
                .superflatFeature(ANCIENT_WITCH_HUT.configure(FeatureConfig.DEFAULT));

        if (ERConfig.getData().ANCIENT_WITCH_HUT.terraforming) {
            hut_builder.adjustsSurface();
        }

        hut_builder.register();
    }
}
