package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.util.ERUtils;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class AncientWitchHut extends StructureFeature<StructurePoolFeatureConfig> {

    public AncientWitchHut(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, AncientWitchHut::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    private static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of(
            new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 1, 2),
            new SpawnSettings.SpawnEntry(EntityType.WITCH, 100, 1, 2)
    );


    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return STRUCTURE_MONSTERS;
    }


    protected static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        BlockPos spawnXZPosition = context.chunkPos().getCenterAtY(0);

        // Grab height of land. Will stop at first non-air block.
        int landHeight = context.chunkGenerator().getHeightInGround(spawnXZPosition.getX(), spawnXZPosition.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        // Grabs column of blocks at given position. In overworld, this column will be made of stone, water, and air.
        // In nether, it will be netherrack, lava, and air. End will only be endstone and air. It depends on what block
        // the chunk generator will place for that dimension.
        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(spawnXZPosition.getX(), spawnXZPosition.getZ(), context.world());
        // Combine the column of blocks with land height and you get the top block itself which you can test.
        BlockState topBlock = columnOfBlocks.getState(landHeight);
        // Now we test to make sure our structure is not spawning on water or other fluids.
        // You can do height check instead too to make it spawn at high elevations.
        return ERUtils.getChunkDistanceFromSpawn(context.chunkPos()) >= ERConfig.getData().ANCIENT_WITCH_HUT.spawnDistance && topBlock.getFluidState().isEmpty();
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!AncientWitchHut.isFeatureChunk(context)) {
            return Optional.empty();
        }

        StructurePoolFeatureConfig newConfig = new StructurePoolFeatureConfig(
                () -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY)
                        .get(EndRemastered.createIdentifier("ancient_witch_hut/start_pool")), 1);

        StructureGeneratorFactory.Context<StructurePoolFeatureConfig> newContext = new StructureGeneratorFactory.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.world(),
                context.validBiome(),
                context.structureManager(),
                context.registryManager()
        );

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        newContext, // Used for StructurePoolBasedGenerator to get all the proper behaviors done.
                        PoolStructurePiece::new, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                        blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
                        false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        true // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );
        return structurePiecesGenerator;
    }
}