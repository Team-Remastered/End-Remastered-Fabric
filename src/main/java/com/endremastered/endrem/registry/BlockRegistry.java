package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.blocks.AncientPortalFrame;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static final Block ANCIENT_PORTAL_FRAME = new AncientPortalFrame(FabricBlockSettings.of(Material.METAL).strength(-1.0F, 3600000.0F).dropsNothing());
    public static final Block END_CRYSTAL_ORE = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(30.0F, 1200.0F), UniformIntProvider.create(3, 7));


    public static void init() {
        registerBlock("ancient_portal_frame", ANCIENT_PORTAL_FRAME);
        registerBlockItem("ancient_portal_frame", new BlockItem(ANCIENT_PORTAL_FRAME, new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));

        registerBlock("end_crystal_ore", END_CRYSTAL_ORE);
        registerBlockItem("end_crystal_ore", new BlockItem(END_CRYSTAL_ORE, new FabricItemSettings().group(EndRemastered.ENDREM_TAB)));
    }

    public static void registerBlock(String blockName, Block block) {
        Registry.register(Registry.BLOCK, EndRemastered.createIdentifier(blockName), block);
    }

    public static void registerBlockItem(String blockItemName, Item blockItem) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(blockItemName), blockItem);
    }

//    private static Block register(String id, Block block) {
//        return Registry.register(Registry.BLOCK, EndRemastered.createIdentifier(id), block);
//    }
}
