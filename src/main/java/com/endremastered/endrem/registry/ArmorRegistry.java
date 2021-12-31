package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.items.EndCrystalArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ArmorRegistry {

    public static final ArmorMaterial END_CRYSTAL_MATERIAL = new EndCrystalArmorMaterial();

    public static final Item END_CRYSTAL_HELMET = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_CHESTPLATE = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_LEGGINGS = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_BOOTS = new ArmorItem(END_CRYSTAL_MATERIAL, EquipmentSlot.FEET, new FabricItemSettings().group(EndRemastered.ENDREM_TAB));

    public static void registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(itemName), item);
    }

    public static void init() {
        registerItem("end_crystal_helmet", END_CRYSTAL_HELMET);
        registerItem("end_crystal_chestplate", END_CRYSTAL_CHESTPLATE);
        registerItem("end_crystal_leggings", END_CRYSTAL_LEGGINGS);
        registerItem("end_crystal_boots", END_CRYSTAL_BOOTS);
    }
}
