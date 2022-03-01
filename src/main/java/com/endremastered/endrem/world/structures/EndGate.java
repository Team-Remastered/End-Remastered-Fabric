package com.endremastered.endrem.world.structures;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.util.ERUtils;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructurePresence;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EndGate extends StructureFeature<StructurePoolFeatureConfig>{
    public EndGate(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, EndGate::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    private static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of(
            new SpawnSettings.SpawnEntry(EntityType.SKELETON, 30, 30, 35),
            new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 20, 25, 30),
            new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 20, 20, 30),
            new SpawnSettings.SpawnEntry(EntityType.WITCH, 10, 10, 15)
            );

    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return STRUCTURE_MONSTERS;
    }


    @Nullable
    @Override
    public BlockPos locateStructure(WorldView world, StructureAccessor structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureConfig config) {
        int i = config.getSpacing();
        int j = ChunkSectionPos.getSectionCoord(searchStartPos.getX());
        int k = ChunkSectionPos.getSectionCoord(searchStartPos.getZ());

        for(int l = 0; l <= searchRadius; ++l) {
            for(int m = -l; m <= l; ++m) {
                boolean bl = m == -l || m == l;

                for(int n = -l; n <= l; ++n) {
                    boolean bl2 = n == -l || n == l;
                    if (bl || bl2) {
                        int o = j + i * m;
                        int p = k + i * n;
                        ChunkPos chunkPos = this.getStartChunk(config, worldSeed, o, p);
                        StructurePresence structurePresence = structureAccessor.getStructurePresence(chunkPos, this, skipExistingChunks);
                        if (structurePresence != StructurePresence.START_NOT_PRESENT) {
                            if (!skipExistingChunks && structurePresence == StructurePresence.START_PRESENT) {
                                return this.getLocatedPos(chunkPos);
                            }
                            Chunk chunk = world.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structureStart = structureAccessor.getStructureStart(ChunkSectionPos.from(chunk), this, chunk);
                            if (structureStart != null && structureStart.hasChildren()) {
                                if (skipExistingChunks && structureStart.isInExistingChunk()) {
                                    structureAccessor.incrementReferences(structureStart);
                                    return this.getLocatedPos(structureStart.getPos());
                                }

                                if (!skipExistingChunks) {
                                    return this.getLocatedPos(structureStart.getPos());
                                }
                            if (!skipExistingChunks) {
                                return this.getLocatedPos(structureStart.getPos());
                            }
                        }

                        if (l == 0) {
                            break;
                        }
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

    protected static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        return ERUtils.getChunkDistanceFromSpawn(context.chunkPos()) >= ERConfig.getData().END_GATE.spawnDistance;
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!AncientWitchHut.isFeatureChunk(context)) {
            return Optional.empty();
        }

        StructurePoolFeatureConfig newConfig = new StructurePoolFeatureConfig(
                () -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY)
                        .get(EndRemastered.createIdentifier("end_gate/start_pool")), ERConfig.getData().END_GATE.size);

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
                        false // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );
        return structurePiecesGenerator;
    }
}
