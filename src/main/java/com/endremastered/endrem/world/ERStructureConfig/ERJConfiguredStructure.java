package com.endremastered.endrem.world.ERStructureConfig;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class ERJConfiguredStructure {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_END_GATE = ERJigsawStructures.END_GATE.configure(DefaultFeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_ANCIENT_WITCH_HUT = ERJigsawStructures.ANCIENT_WITCH_HUT.configure(DefaultFeatureConfig.DEFAULT);


    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, EndRemastered.createIdentifier("configured_end_gate"), CONFIGURED_END_GATE);
        BiomeModifications.create(EndRemastered.createIdentifier("end_gate_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.excludeByKey(BiomeKeys.OCEAN,
                        BiomeKeys.DEEP_OCEAN,
                        BiomeKeys.FROZEN_OCEAN,
                        BiomeKeys.DEEP_FROZEN_OCEAN,
                        BiomeKeys.COLD_OCEAN,
                        BiomeKeys.DEEP_COLD_OCEAN,
                        BiomeKeys.LUKEWARM_OCEAN,
                        BiomeKeys.DEEP_LUKEWARM_OCEAN,
                        BiomeKeys.WARM_OCEAN,
                        BiomeKeys.DEEP_WARM_OCEAN,
                        BiomeKeys.BEACH,
                        BiomeKeys.SNOWY_BEACH,
                        BiomeKeys.RIVER,
                        BiomeKeys.FROZEN_RIVER
                ), context -> {
                    context.getGenerationSettings().addBuiltInStructure(ERJConfiguredStructure.CONFIGURED_END_GATE);
                });

        Registry.register(registry, EndRemastered.createIdentifier("configured_ancient_witch_hut"), CONFIGURED_ANCIENT_WITCH_HUT);
        BiomeModifications.create(EndRemastered.createIdentifier("ancient_witch_hut_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.SWAMP), context -> {
                    context.getGenerationSettings().addBuiltInStructure(ERJConfiguredStructure.CONFIGURED_ANCIENT_WITCH_HUT);
                });
    }
}
