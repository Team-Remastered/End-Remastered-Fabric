package com.endremastered.endrem.client;

import com.endremastered.endrem.registry.ERBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientInitializer implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ERBlocks.END_CRYSTAL_BLOCK, RenderLayer.getTranslucent()); //Makes the End Crystal Block Transparent
    }
}
