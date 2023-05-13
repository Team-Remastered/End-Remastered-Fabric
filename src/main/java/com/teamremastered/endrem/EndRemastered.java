package com.teamremastered.endrem;

import com.teamremastered.endrem.config.ERConfigHandler;
import com.teamremastered.endrem.registry.ERItems;
import com.teamremastered.endrem.registry.RegisterHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EndRemastered implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("End Remastered Logger");
    public static final String MOD_ID = "endrem";
    public static final ItemGroup ENDREM_TAB = FabricItemGroup.builder(createIdentifier("endrem_tab"))
            .displayName(Text.translatable("itemGroup.endrem.endrem_tab"))
            .icon(() -> new ItemStack(ERItems.WITCH_EYE))
            .entries((properties, entries) -> {
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
            }).build();

    public static Identifier createIdentifier(String name) {
        return new Identifier(EndRemastered.MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        RegisterHandler.init();

        // Register Config
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            System.out.println("PREPARING FOR RELOAD");
            ERConfigHandler.load();
        });
    }
}
