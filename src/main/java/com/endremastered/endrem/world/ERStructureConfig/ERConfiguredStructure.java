package com.endremastered.endrem.world.ERStructureConfig;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.world.structures.EndCastle;
import com.endremastered.endrem.world.structures.EndCastlePieces;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ERConfiguredStructure {

    public static StructureFeature<DefaultFeatureConfig> END_CASTLE = new EndCastle(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> END_CASTLE_CONFIGURED = END_CASTLE.configure(DefaultFeatureConfig.DEFAULT);
    public static final StructurePieceType PIECE = StructurePieceType.register(EndCastlePieces.Piece::new, "end_castle");

    public static void registerConfiguredStructures() {
        FabricStructureBuilder.create(EndRemastered.createIdentifier("end_castle"), END_CASTLE)
                .step(GenerationStep.Feature.UNDERGROUND_DECORATION)
                .defaultConfig(270, 230, 542524543)
                .adjustsSurface()
                .register();

        Registry<ConfiguredStructureFeature<?, ?>> myConfigured = (BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE);
        Registry.register(myConfigured,(EndRemastered.createIdentifier("end_castle")), END_CASTLE_CONFIGURED);

        BiomeModifications.create(EndRemastered.createIdentifier("end_castle")).add(ModificationPhase.ADDITIONS, BiomeSelectors.excludeByKey(
                BiomeKeys.OCEAN,
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
                BiomeKeys.FROZEN_RIVER,

                BiomeKeys.MOUNTAINS,
                BiomeKeys.MOUNTAIN_EDGE,
                BiomeKeys.GRAVELLY_MOUNTAINS,
                BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS,
                BiomeKeys.SNOWY_MOUNTAINS,
                BiomeKeys.SNOWY_TAIGA_MOUNTAINS,
                BiomeKeys.TAIGA_MOUNTAINS,
                BiomeKeys.WOODED_MOUNTAINS,

                BiomeKeys.DRIPSTONE_CAVES,
                BiomeKeys.LUSH_CAVES
        ), context -> {
            context.getGenerationSettings().addBuiltInStructure(END_CASTLE_CONFIGURED);
        });
        }
    }
