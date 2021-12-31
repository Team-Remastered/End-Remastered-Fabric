package com.endremastered.endrem;

import com.endremastered.endrem.config.ConfigHandler;
import com.endremastered.endrem.items.CustomMap;
import com.endremastered.endrem.registry.ArmorRegistry;
import com.endremastered.endrem.registry.BlockRegistry;
import com.endremastered.endrem.registry.ItemRegistry;
import com.endremastered.endrem.registry.ToolRegistry;
import com.endremastered.endrem.util.LootInjection;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.endremastered.endrem.world.ERStructureConfig.ERJConfiguredStructure;
import com.endremastered.endrem.world.gen.OreSpawnHandler;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import com.endremastered.endrem.world.util.DimensionCheck;
import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class EndRemastered implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("End Remastered Logger");
	public static final String MOD_ID = "endrem";
	public static final ItemGroup ENDREM_TAB = FabricItemGroupBuilder.build(
			createIdentifier("endrem_tab"),
			() -> new ItemStack(ItemRegistry.ROGUE_EYE));

	public static Identifier createIdentifier(String name) {
		return new Identifier(EndRemastered.MOD_ID, name);
	}
	public static List<String> whitelistedDimensions = Lists.newArrayList("minecraft:overworld");

	@Override
	public void onInitialize() {
		/* Miscellaneous */
		LootInjection.init();
		ConfigHandler.load();
		CustomMap.addTradeToVillager();
		/* Items & Blocks */
		ItemRegistry.init();
		ArmorRegistry.init();
		ToolRegistry.init();
		BlockRegistry.init();
		/* Features */
		OreSpawnHandler.init();
		ERJigsawStructures.setupAndRegisterStructureFeatures();
		ERJConfiguredStructure.registerConfiguredStructures();
		ERConfiguredStructure.registerConfiguredStructures();
		DimensionCheck.removeStructureSpawningFromSelectedDimension();

		/* Reloads the Configs When the Server Starts */
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			System.out.println("PREPARING FOR RELOAD");
			ConfigHandler.load();
		});
	}
}
