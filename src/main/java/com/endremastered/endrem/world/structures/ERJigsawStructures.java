package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.ERStructureConfig.ERJConfiguredStructure;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.IglooFeature;
import net.minecraft.world.gen.feature.StructureFeature;

public class ERJigsawStructures {

    public static StructureFeature<DefaultFeatureConfig> END_GATE = new EndGate(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> ANCIENT_WITCH_HUT = new AncientWitchHut(DefaultFeatureConfig.CODEC);


    public static void setupAndRegisterStructureFeatures() {
        /*END GATE*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("end_gate"), END_GATE)
                .step(GenerationStep.Feature.STRONGHOLDS)
                .defaultConfig(new StructureConfig(270, 230, 324894322))
                .superflatFeature(END_GATE.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();

        /*ANCIENT_WITCH_HUT*/
        FabricStructureBuilder.create(EndRemastered.createIdentifier("ancient_witch_hut"), ANCIENT_WITCH_HUT)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(100, 50, 324894322))
                .superflatFeature(ANCIENT_WITCH_HUT.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();
    }
}
