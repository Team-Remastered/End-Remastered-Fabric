package com.endremastered.endrem.mixin.world;


import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructures;
import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
import com.endremastered.endrem.world.structures.AncientWitchHut;
import com.endremastered.endrem.world.structures.EndCastle;
import com.endremastered.endrem.world.structures.EndGate;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    @Inject(
            method = "getEntitySpawnList(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/collection/Pool;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void structureMobs(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> cir) {
        Pool<SpawnSettings.SpawnEntry> pool = getStructureSpawns(biome, accessor, group, pos);
        if(pool != null) cir.setReturnValue(pool);
    }

    private static Pool<SpawnSettings.SpawnEntry> getStructureSpawns(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos){

        if (group == SpawnGroup.MONSTER) {
            if (accessor.getStructureAt(pos, ERStructures.END_GATE).hasChildren()) {
                return EndGate.getMonsterSpawns();
            }
            if (accessor.getStructureAt(pos, ERConfiguredStructures.END_CASTLE).hasChildren()) {
                return EndCastle.getMonsterSpawns();
            }
            if (accessor.getStructureAt(pos, ERStructures.ANCIENT_WITCH_HUT).hasChildren()) {
                return AncientWitchHut.getMonsterSpawns();
            }
        }

        return null;
    }
}
