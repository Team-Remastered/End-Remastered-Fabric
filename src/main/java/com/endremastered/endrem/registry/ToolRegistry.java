package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.items.EndCrystalTool;
import net.minecraft.item.ToolItem;
import net.minecraft.util.registry.Registry;

public class ToolRegistry {

    public static final ToolItem END_CRYSTAL_HOE = new EndCrystalTool.EndCrystalHoe();
    public static final ToolItem END_CRYSTAL_PICKAXE = new EndCrystalTool.EndCrystalPickaxe();
    public static final ToolItem END_CRYSTAL_AXE = new EndCrystalTool.EndCrystalAxe();
    public static final ToolItem END_CRYSTAL_SWORD = new EndCrystalTool.EndCrystalSword();
    public static final ToolItem END_CRYSTAL_SHOVEL = new EndCrystalTool.EndCrystalShovel();

    public static void registerToolItem(String itemName, ToolItem toolItem) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(itemName), toolItem);
    }

    public static void init() {
        registerToolItem("end_crystal_hoe", END_CRYSTAL_HOE);
        registerToolItem("end_crystal_pickaxe", END_CRYSTAL_PICKAXE);
        registerToolItem("end_crystal_axe", END_CRYSTAL_AXE);
        registerToolItem("end_crystal_sword", END_CRYSTAL_SWORD);
        registerToolItem("end_crystal_shovel", END_CRYSTAL_SHOVEL);


    }

}
