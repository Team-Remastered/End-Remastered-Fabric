package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.util.ERUtils;
import com.endremastered.endrem.world.util.CustomMonsterSpawn;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.Random;

public class EndCastle extends StructureFeature<DefaultFeatureConfig> {
    private static final List<CustomMonsterSpawn> STRUCTURE_MONSTERS = List.of(
            new CustomMonsterSpawn(EntityType.PILLAGER, 30, 30, 35),
            new CustomMonsterSpawn(EntityType.VINDICATOR, 20, 25, 30),
            new CustomMonsterSpawn(EntityType.EVOKER, 20, 10, 15),
            new CustomMonsterSpawn(EntityType.ILLUSIONER, 5, 5, 10)
    );

    public EndCastle(Codec<DefaultFeatureConfig> codec) {
        super(codec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), EndCastle::addPieces), EndCastle::postPlace);
    }

    protected static boolean isFeatureChunk(StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        BlockPos centerOfChunk = new BlockPos(context.chunkPos().x * 16, 0, context.chunkPos().z * 16);
        int landHeight = context.chunkGenerator().getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(), context.world());
        BlockState topBlock = columnOfBlocks.getState(landHeight);
        return ERUtils.getChunkDistanceFromSpawn(context.chunkPos()) >= ERConfig.getData().END_CASTLE.spawnDistance && topBlock.getFluidState().isEmpty();
    }


    public static Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return CustomMonsterSpawn.getPoolFromList(STRUCTURE_MONSTERS);
    }

    private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        if (!isFeatureChunk(context)) {
            return;
        }
            BlockRotation rotation = BlockRotation.values()[context.random().nextInt(BlockRotation.values().length)];

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        int x = (context.chunkPos().x << 4);
        int z = (context.chunkPos().z << 4);

        if (rotation == BlockRotation.CLOCKWISE_90) {
            x += 17;
            z += 43;
        }
        else if (rotation == BlockRotation.CLOCKWISE_180) {
            x -= 43;
            z += 17;
        }
        else if (rotation == BlockRotation.COUNTERCLOCKWISE_90) {
            x -= 17;
            z -= 43;
        }
        else {
            x += 43;
            z -= 17;
        }
            int y = context.chunkGenerator().getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG, context.world());
            BlockPos newPos = new BlockPos(x, y + ERConfig.getData().END_CASTLE.height, z);
            EndCastlePieces.addPieces(context.structureManager(), newPos, rotation, collector, context.random());
        }

    private static void postPlace(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, StructurePiecesList children) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = world.getBottomY();
        BlockBox blockBox = children.getBoundingBox();
        int j = blockBox.getMinY();

        for(int k = chunkBox.getMinX(); k <= chunkBox.getMaxX(); ++k) {
            for(int l = chunkBox.getMinZ(); l <= chunkBox.getMaxZ(); ++l) {
                mutable.set(k, j, l);
                if (!world.isAir(mutable) && blockBox.contains(mutable) && children.contains(mutable)) {
                    for(int m = j - 1; m > i; --m) {
                        mutable.setY(m);
                        if (!world.isAir(mutable) && !world.getBlockState(mutable).getMaterial().isLiquid()) {
                            break;
                        }
                        double randomBlock = Math.random();
                        if (randomBlock <= 0.05) {
                            world.setBlockState(mutable, Blocks.STONE.getDefaultState(), 2);
                        } else if (randomBlock <= 0.1) {
                            world.setBlockState(mutable, Blocks.COBBLESTONE.getDefaultState(), 2);
                        } else if (randomBlock <= 0.2) {
                            world.setBlockState(mutable, Blocks.ANDESITE.getDefaultState(), 2);
                        } else if (randomBlock <= 0.3) {
                            world.setBlockState(mutable, Blocks.STONE_BRICKS.getDefaultState(), 2);
                        } else if (randomBlock <= 0.4) {
                            world.setBlockState(mutable, Blocks.CRACKED_STONE_BRICKS.getDefaultState(), 2);
                        } else if (randomBlock <= 0.5) {
                            world.setBlockState(mutable, Blocks.ANDESITE.getDefaultState(), 2);
                        } else {
                            world.setBlockState(mutable, Blocks.POLISHED_ANDESITE.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

    }
}
