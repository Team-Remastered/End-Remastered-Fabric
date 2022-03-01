package com.endremastered.endrem.world.gen;

import com.endremastered.endrem.registry.ERBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;


public class OreSpawnHandler {

   private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_BLACKSTONE = Feature.ORE
           .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE),
                   ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                   3, 0.5F))
           .range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(150)))) // Inclusive min and max height
           .spreadHorizontally()
           .repeat(20); // Number of veins per chunk

    private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS = Feature.ORE
            .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.POLISHED_BLACKSTONE_BRICKS),
                    ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                    3, 0.5F))
            .range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(150)))) // Inclusive min and max height
            .spreadHorizontally()
            .repeat(20); // Number of veins per chunk

    private static ConfiguredFeature<?, ?> END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS = Feature.ORE
            .configure(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS),
                    ERBlocks.END_CRYSTAL_ORE.getDefaultState(),
                    3, 0.5F))
            .range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(150)))) // Inclusive min and max height
            .spreadHorizontally()
            .repeat(20); // Number of veins per chunk

   public static void init() {
       RegistryKey<ConfiguredFeature<?, ?>> end_crystal_ore_gen_b = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
               new Identifier("endrem", "end_crystal_ore_gen_b"));
       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, end_crystal_ore_gen_b.getValue(), END_CRYSTAL_ORE_GEN_BLACKSTONE);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, end_crystal_ore_gen_b);

       RegistryKey<ConfiguredFeature<?, ?>> end_crystal_ore_gen_pbb = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
               new Identifier("endrem", "end_crystal_ore_gen_pbb"));
       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, end_crystal_ore_gen_pbb.getValue(), END_CRYSTAL_ORE_GEN_POLISHED_BLACKSTONE_BRICKS);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, end_crystal_ore_gen_pbb);

       RegistryKey<ConfiguredFeature<?, ?>> end_crystal_ore_gen_cpbb = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
               new Identifier("endrem", "end_crystal_ore_gen_cpbb"));
       Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, end_crystal_ore_gen_cpbb.getValue(), END_CRYSTAL_ORE_GEN_CRACKED_POLISHED_BLACKSTONE_BRICKS);
       BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, end_crystal_ore_gen_cpbb);
   }
}
