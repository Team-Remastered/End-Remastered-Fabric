package com.endremastered.endrem.world.gen;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.registry.ERBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;


public class OreSpawnHandler {

   private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_BLACKSTONE = Feature.ORE
           .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE),
                   ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                   3, 0));

   public static PlacedFeature END_CRYSTAL_ORE_GEN_BLACKSTONE_PLACED_FEATURE = END_CRYSTAL_ORE_GEN_BLACKSTONE.withPlacement(
           CountPlacementModifier.of(20),
           SquarePlacementModifier.of(),
           HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(100)));

    private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS = Feature.ORE
            .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.POLISHED_BLACKSTONE_BRICKS),
                    ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                    3, 0));

    public static PlacedFeature END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS_PLACED_FEATURE = END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS.withPlacement(
            CountPlacementModifier.of(20),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(100)));

    private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS = Feature.ORE
            .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS),
                    ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                    3, 0));

    public static PlacedFeature END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS_PLACED_FEATURE = END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS.withPlacement(
            CountPlacementModifier.of(20),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(100)));

   public static void init() {

       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_b"), END_CRYSTAL_ORE_GEN_BLACKSTONE);
       Registry.register(BuiltinRegistries.PLACED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_b"), END_CRYSTAL_ORE_GEN_BLACKSTONE_PLACED_FEATURE);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, EndRemastered.createIdentifier("end_crystal_ore_gen_b")));

       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_pbb"), END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS);
       Registry.register(BuiltinRegistries.PLACED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_pbb"), END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS_PLACED_FEATURE);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, EndRemastered.createIdentifier("end_crystal_ore_gen_pbb")));

       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_cpbb"), END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS);
       Registry.register(BuiltinRegistries.PLACED_FEATURE, EndRemastered.createIdentifier("end_crystal_ore_gen_cpbb"), END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS_PLACED_FEATURE);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, EndRemastered.createIdentifier("end_crystal_ore_gen_cpbb")));
   }
}
