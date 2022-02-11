package com.endremastered.endrem.world.ERStructureConfig;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class ERJConfiguredStructure {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_END_GATE = ERJigsawStructures.END_GATE.configure(DefaultFeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_ANCIENT_WITCH_HUT = ERJigsawStructures.ANCIENT_WITCH_HUT.configure(DefaultFeatureConfig.DEFAULT);


    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, EndRemastered.createIdentifier("configured_end_gate"), CONFIGURED_END_GATE);
        BiomeModifications.create(EndRemastered.createIdentifier("end_gate_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(ERConfig.getData().END_GATE.getProcessedBiomeWhitelist()),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(ERJConfiguredStructure.CONFIGURED_END_GATE);
                        }).add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(ERConfig.getData().END_GATE.getProcessedBiomeBlacklist()),
                        context -> {
                            context.getGenerationSettings().removeBuiltInStructure(CONFIGURED_END_GATE);
                        });

        Registry.register(registry, EndRemastered.createIdentifier("configured_ancient_witch_hut"), CONFIGURED_ANCIENT_WITCH_HUT);
        BiomeModifications.create(EndRemastered.createIdentifier("ancient_witch_hut_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(ERConfig.getData().ANCIENT_WITCH_HUT.getProcessedBiomeWhitelist()),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(ERJConfiguredStructure.CONFIGURED_ANCIENT_WITCH_HUT);
                        }).add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(ERConfig.getData().ANCIENT_WITCH_HUT.getProcessedBiomeBlacklist()),
                        context -> {
                            context.getGenerationSettings().removeBuiltInStructure(CONFIGURED_ANCIENT_WITCH_HUT);
                        });
    }
}
