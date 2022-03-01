package com.endremastered.endrem.world.ERStructureConfig;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.world.structures.EndCastle;
import com.endremastered.endrem.world.structures.EndCastlePieces;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

public class ERConfiguredStructures {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_END_GATE = ERStructures.END_GATE.configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_ANCIENT_WITCH_HUT = ERStructures.ANCIENT_WITCH_HUT.configure(new StructurePoolFeatureConfig(() -> PlainsVillageData.STRUCTURE_POOLS, 0));

    public static StructureFeature<DefaultFeatureConfig> END_CASTLE = new EndCastle(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> END_CASTLE_CONFIGURED = END_CASTLE.configure(DefaultFeatureConfig.DEFAULT);
    public static final StructurePieceType PIECE = EndCastlePieces.EndCastlePiece::new;

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        /* End Gate */
        Registry.register(registry, EndRemastered.createIdentifier("configured_end_gate"), CONFIGURED_END_GATE);
        BiomeModifications.create(EndRemastered.createIdentifier("end_gate_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(ERConfig.getData().END_GATE.getProcessedBiomeWhitelist()),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(ERConfiguredStructures.CONFIGURED_END_GATE);
                        }).add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(ERConfig.getData().END_GATE.getProcessedBiomeBlacklist()),
                        context -> {
                            context.getGenerationSettings().removeBuiltInStructure(CONFIGURED_END_GATE);
                        });

        /* Witch Hut */
        Registry.register(registry, EndRemastered.createIdentifier("configured_ancient_witch_hut"), CONFIGURED_ANCIENT_WITCH_HUT);
        BiomeModifications.create(EndRemastered.createIdentifier("ancient_witch_hut_addition"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(ERConfig.getData().ANCIENT_WITCH_HUT.getProcessedBiomeWhitelist()),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(ERConfiguredStructures.CONFIGURED_ANCIENT_WITCH_HUT);
                        }).add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(ERConfig.getData().ANCIENT_WITCH_HUT.getProcessedBiomeBlacklist()),
                        context -> {
                            context.getGenerationSettings().removeBuiltInStructure(CONFIGURED_ANCIENT_WITCH_HUT);
                        });

        /* End Castle */
        FabricStructureBuilder<DefaultFeatureConfig, StructureFeature<DefaultFeatureConfig>> builder = FabricStructureBuilder.create(EndRemastered.createIdentifier("end_castle"), END_CASTLE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(ERConfig.getData().END_CASTLE.averageDistance, ERConfig.getData().END_CASTLE.averageDistance - 30, 542524543);

        if (ERConfig.getData().END_CASTLE.terraforming) {
            builder.adjustsSurface();
        }

        builder.register();


        RegistryKey<ConfiguredStructureFeature<?, ?>> configuredEndCastle = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                EndRemastered.createIdentifier("end_castle"));

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, configuredEndCastle.getValue(), END_CASTLE_CONFIGURED);

        BiomeModifications.create(EndRemastered.createIdentifier("end_castle")).add(ModificationPhase.ADDITIONS,
                BiomeSelectors.includeByKey(ERConfig.getData().END_CASTLE.getProcessedBiomeWhitelist()),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(END_CASTLE_CONFIGURED);
                }).add(ModificationPhase.REMOVALS, BiomeSelectors.includeByKey(ERConfig.getData().END_CASTLE.getProcessedBiomeBlacklist()),
                context -> {
                    context.getGenerationSettings().removeBuiltInStructure(END_CASTLE_CONFIGURED);
                });
    }



    }
