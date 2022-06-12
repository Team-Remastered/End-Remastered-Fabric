package com.teamremastered.endrem;

import com.teamremastered.endrem.config.ERConfigHandler;
import com.teamremastered.endrem.registry.ERItems;
import com.teamremastered.endrem.registry.RegisterHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EndRemastered implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("End Remastered Logger");
    public static final String MOD_ID = "endrem";
    public static final ItemGroup ENDREM_TAB = FabricItemGroupBuilder.build(
            createIdentifier("endrem_tab"),
            () -> new ItemStack(ERItems.ROGUE_EYE));

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
