package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.util.ERUtils;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.jetbrains.annotations.Nullable;

public class EndGate extends StructureFeature<DefaultFeatureConfig>{
    public EndGate(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return EndGate.Start::new;
    }

    private static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of(
            new SpawnSettings.SpawnEntry(EntityType.SKELETON, 30, 30, 35),
            new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 20, 25, 30),
            new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 20, 20, 30),
            new SpawnSettings.SpawnEntry(EntityType.WITCH, 10, 10, 15)
            );

    @Override
    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return STRUCTURE_MONSTERS;
    }


    @Nullable
    @Override
    public BlockPos locateStructure(WorldView world, StructureAccessor structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureConfig config) {
        int i = config.getSpacing();
        int j = ChunkSectionPos.getSectionCoord(searchStartPos.getX());
        int k = ChunkSectionPos.getSectionCoord(searchStartPos.getZ());
        int l = 0;

        for(ChunkRandom chunkRandom = new ChunkRandom(); l <= searchRadius; ++l) {
            for(int m = -l; m <= l; ++m) {
                boolean bl = m == -l || m == l;

                for(int n = -l; n <= l; ++n) {
                    boolean bl2 = n == -l || n == l;
                    if (bl || bl2) {
                        int o = j + i * m;
                        int p = k + i * n;
                        ChunkPos chunkPos = this.getStartChunk(config, worldSeed, chunkRandom, o, p);
                        boolean bl3 = world.getBiomeAccess().getBiomeForNoiseGen(chunkPos).getGenerationSettings().hasStructureFeature(this);
                        if (bl3) {
                            Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structureStart = structureAccessor.getStructureStart(ChunkSectionPos.from(chunk), this, chunk);
                            if (structureStart != null && structureStart.hasChildren()) {
                                if (skipExistingChunks && structureStart.isInExistingChunk()) {
                                    structureStart.incrementReferences();
                                    return new BlockPos(structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinX(),
                                            structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinY(),
                                            structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinZ());
                                }

                                if (!skipExistingChunks) {
                                    return new BlockPos(structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinX(),
                                            structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinY(),
                                            structureStart.getChildren().get(((Start) structureStart).getLocatedRoom()).getBoundingBox().getMinZ());
                                }
                            }
                        }

                        if (l == 0) {
                            break;
                        }
                    }
                }

                if (l == 0) {
                    break;
                }
            }
        }

        return null;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos, Biome biome, ChunkPos chunkPos2, DefaultFeatureConfig featureConfig, HeightLimitView heightLimitView) {
        return ERUtils.getChunkDistanceFromSpawn(chunkPos) >= ERConfig.getData().END_GATE.spawnDistance;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos, int referenceIn, long seedIn) {
            super(structureIn, chunkPos, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;

            BlockPos.Mutable centerPos = new BlockPos.Mutable(x, ERConfig.getData().END_GATE.height, z);
            StructurePoolFeatureConfig structureSettingsAndStartPool = new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY)
                    .get(EndRemastered.createIdentifier("end_gate/start_pool")), ERConfig.getData().END_GATE.size);

            StructurePoolBasedGenerator.generate(
                    dynamicRegistryManager,
                    structureSettingsAndStartPool,
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    centerPos, // Position of the structure. Y value is ignored if last parameter is set to true.
                    this, // The class instance that holds the list that will be populated with the jigsaw pieces after this method.
                    this.random,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    false,
                    heightLimitView);
        }
        public int getLocatedRoom() {
            return Math.min(16, this.children.size()) - 1;
        }
    }
}
