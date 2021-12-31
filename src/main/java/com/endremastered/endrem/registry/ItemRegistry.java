package com.endremastered.endrem.registry;

import com.endremastered.endrem.EndRemastered;
import com.endremastered.endrem.items.EyeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    /* Chests Eyes */
    public static final Item BLACK_EYE = new EyeItem();
    public static final Item COLD_EYE = new EyeItem();
    public static final Item CORRUPTED_EYE = new EyeItem();
    public static final Item LOST_EYE = new EyeItem();
    public static final Item NETHER_EYE = new EyeItem();
    public static final Item OLD_EYE = new EyeItem();
    public static final Item ROGUE_EYE = new EyeItem();

    /* Entities Eyes */
    public static final Item GUARDIAN_EYE = new EyeItem();
    public static final Item MAGICAL_EYE = new EyeItem();
    public static final Item WITCH_EYE = new EyeItem();
    public static final Item WITHER_EYE = new EyeItem();

    /* Craftable Eyes */
    public static final Item END_CRYSTAL_EYE = new EyeItem();

    /* Miscellaneous */
    public static final Item WITCH_PUPIL = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_FRAGMENT = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));
    public static final Item END_CRYSTAL_INGOT = new Item(new FabricItemSettings().group(EndRemastered.ENDREM_TAB));

    public static void registerItem(String itemName, Item item) {
        Registry.register(Registry.ITEM, EndRemastered.createIdentifier(itemName), item);
    }

    public static void init() {
        registerItem("black_eye", BLACK_EYE);
        registerItem("cold_eye", COLD_EYE);
        registerItem("corrupted_eye", CORRUPTED_EYE);
        registerItem("lost_eye", LOST_EYE);
        registerItem("nether_eye", NETHER_EYE);
        registerItem("old_eye", OLD_EYE);
        registerItem("rogue_eye", ROGUE_EYE);

        registerItem("guardian_eye", GUARDIAN_EYE);
        registerItem("magical_eye", MAGICAL_EYE);
        registerItem("witch_eye", WITCH_EYE);
        registerItem("wither_eye", WITHER_EYE);

        registerItem("end_crystal_eye", END_CRYSTAL_EYE);

        registerItem("witch_pupil", WITCH_PUPIL);
        registerItem("end_crystal_fragment", END_CRYSTAL_FRAGMENT);
        registerItem("end_crystal_ingot", END_CRYSTAL_INGOT);
    }
}
