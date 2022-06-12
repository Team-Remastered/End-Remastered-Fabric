package com.teamremastered.endrem.registry;

import com.teamremastered.endrem.EndRemastered;
import com.teamremastered.endrem.blocks.AncientPortalFrame;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ERBlocks {

    public static void registerBlock(String blockName, Block block) {
        Registry.register(Registry.BLOCK, EndRemastered.createIdentifier(blockName), block);
    }

    public static void registerBlockItem(String blockItemName, Item blockItem) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(blockItemName), blockItem);
    }

    // === Blocks ===

    public static final Block ANCIENT_PORTAL_FRAME = new AncientPortalFrame(FabricBlockSettings.of(Material.METAL).strength(-1.0F, 3600000.0F).dropsNothing());

    public static void initRegister() {
        registerBlock("ancient_portal_frame", ANCIENT_PORTAL_FRAME);
        registerBlockItem("ancient_portal_frame", new BlockItem(ANCIENT_PORTAL_FRAME, new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
    }
}
