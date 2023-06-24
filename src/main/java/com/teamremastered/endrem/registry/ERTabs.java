package com.teamremastered.endrem.registry;

import com.teamremastered.endrem.EndRemastered;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ERTabs {

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, EndRemastered.createIdentifier("endrem_tab"));

    public static void initRegister() {

    Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.endrem.endrem_tab"))
            .icon(() -> new ItemStack(ERItems.WITCH_EYE))
            .entries((enabledFeatures, entries) -> {
        entries.add(ERItems.BLACK_EYE);
        entries.add(ERItems.COLD_EYE);
        entries.add(ERItems.CORRUPTED_EYE);
        entries.add(ERItems.LOST_EYE);
        entries.add(ERItems.NETHER_EYE);
        entries.add(ERItems.OLD_EYE);
        entries.add(ERItems.ROGUE_EYE);
        entries.add(ERItems.CURSED_EYE);
        entries.add(ERItems.EVIL_EYE);

        entries.add(ERItems.GUARDIAN_EYE);
        entries.add(ERItems.MAGICAL_EYE);
        entries.add(ERItems.WITHER_EYE);

        entries.add(ERItems.WITCH_EYE);
        entries.add(ERItems.UNDEAD_EYE);
        entries.add(ERItems.EXOTIC_EYE);

        entries.add(ERItems.CRYPTIC_EYE);

        entries.add(ERItems.WITCH_PUPIL);
        entries.add(ERItems.UNDEAD_SOUL);
    }).build());

    }
}
