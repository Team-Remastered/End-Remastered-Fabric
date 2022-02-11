package com.endremastered.endrem;

import com.endremastered.endrem.commands.GetEndremMapCommand;
import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.items.ERMap;
import com.endremastered.endrem.registry.ERBlocks;
import com.endremastered.endrem.registry.ERItems;
import com.endremastered.endrem.registry.RegisterHandler;
import com.endremastered.endrem.util.LootInjection;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.endremastered.endrem.world.ERStructureConfig.ERJConfiguredStructure;
import com.endremastered.endrem.world.gen.OreSpawnHandler;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import com.endremastered.endrem.world.util.DimensionCheck;
import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


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
        registerCommands();

        // Register Config
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            System.out.println("PREPARING FOR RELOAD");
            ERConfig.load();
        });
    }


    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            new GetEndremMapCommand(dispatcher);
        });
    }
}
