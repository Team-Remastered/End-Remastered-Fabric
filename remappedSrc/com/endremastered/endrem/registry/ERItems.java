package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.items.EREnderEye;
import com.endremastered.endrem.items.EndCrystalArmor;
import com.endremastered.endrem.items.EndCrystalTools;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.registry.Registry;

public class ERItems {

    public static void registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(itemName), item);
    }

    // ==== Eyes ====

    /* Chests */
    public static final Item BLACK_EYE = new EREnderEye();
    public static final Item COLD_EYE = new EREnderEye();
    public static final Item CORRUPTED_EYE = new EREnderEye();
    public static final Item LOST_EYE = new EREnderEye();
    public static final Item NETHER_EYE = new EREnderEye();
    public static final Item OLD_EYE = new EREnderEye();
    public static final Item ROGUE_EYE = new EREnderEye();

    /* Entities */
    public static final Item GUARDIAN_EYE = new EREnderEye();
    public static final Item MAGICAL_EYE = new EREnderEye();
    public static final Item WITHER_EYE = new EREnderEye();

    /* Craftable */
    public static final Item END_CRYSTAL_EYE = new EREnderEye();
    public static final Item WITCH_EYE = new EREnderEye();


    // ==== Armor ====
    public static final ArmorMaterial END_CRYSTAL_MATERIAL = new EndCrystalArmor();

    public static final Item END_CRYSTAL_HELMET = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_CHESTPLATE = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_LEGGINGS = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_BOOTS = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.FEET, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));

    // ==== Tools =====
    public static final ToolItem END_CRYSTAL_HOE = new EndCrystalTools.EndCrystalHoe();
    public static final ToolItem END_CRYSTAL_PICKAXE = new EndCrystalTools.EndCrystalPickaxe();
    public static final ToolItem END_CRYSTAL_AXE = new EndCrystalTools.EndCrystalAxe();
    public static final ToolItem END_CRYSTAL_SWORD = new EndCrystalTools.EndCrystalSword();
    public static final ToolItem END_CRYSTAL_SHOVEL = new EndCrystalTools.EndCrystalShovel();

    // === Miscellaneous ===
    public static final Item WITCH_PUPIL = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_FRAGMENT = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_INGOT = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));


    public static void initRegister() {
        /* Chests Eyes */
        registerItem("black_eye", BLACK_EYE);
        registerItem("cold_eye", COLD_EYE);
        registerItem("corrupted_eye", CORRUPTED_EYE);
        registerItem("lost_eye", LOST_EYE);
        registerItem("nether_eye", NETHER_EYE);
        registerItem("old_eye", OLD_EYE);
        registerItem("rogue_eye", ROGUE_EYE);

        /* Entities Eyes */
        registerItem("guardian_eye", GUARDIAN_EYE);
        registerItem("magical_eye", MAGICAL_EYE);
        registerItem("wither_eye", WITHER_EYE);

        /* Craftable Eyes */
        registerItem("end_crystal_eye", END_CRYSTAL_EYE);
        registerItem("witch_eye", WITCH_EYE);

        /* Miscellaneous */
        registerItem("witch_pupil", WITCH_PUPIL);
        registerItem("end_crystal_fragment", END_CRYSTAL_FRAGMENT);
        registerItem("end_crystal_ingot", END_CRYSTAL_INGOT);

        /* Armor */
        registerItem("end_crystal_helmet", END_CRYSTAL_HELMET);
        registerItem("end_crystal_chestplate", END_CRYSTAL_CHESTPLATE);
        registerItem("end_crystal_leggings", END_CRYSTAL_LEGGINGS);
        registerItem("end_crystal_boots", END_CRYSTAL_BOOTS);

        /* Tools */
        registerItem("end_crystal_hoe", END_CRYSTAL_HOE);
        registerItem("end_crystal_pickaxe", END_CRYSTAL_PICKAXE);
        registerItem("end_crystal_axe", END_CRYSTAL_AXE);
        registerItem("end_crystal_sword", END_CRYSTAL_SWORD);
        registerItem("end_crystal_shovel", END_CRYSTAL_SHOVEL);
    }
}
