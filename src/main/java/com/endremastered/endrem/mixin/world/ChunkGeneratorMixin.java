package com.endremastered.endrem.mixin.world;

import com.endremastered.endrem.config.ERConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Credit to TelepathicGrunt for the class

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    @Inject(method = "generateStrongholdPositions",
            at = @At(value = "HEAD"),
            cancellable = true)
    private void removeVanillaStronghold(CallbackInfo ci) {
        if (ERConfig.getData().STRONGHOLDS_ENABLED)ci.cancel();
    }
}

