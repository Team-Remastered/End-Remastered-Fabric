package com.endremastered.endrem.registry;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.items.ERMap;
import com.endremastered.endrem.util.LootInjection;
import com.endremastered.endrem.util.MultiLocator;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructure;
import com.endremastered.endrem.world.ERStructureConfig.ERJConfiguredStructure;
import com.endremastered.endrem.world.gen.OreSpawnHandler;
import com.endremastered.endrem.world.structures.ERJigsawStructures;
import com.endremastered.endrem.world.util.DimensionCheck;

public class RegisterHandler {

    public static void init() {
        /* Miscellaneous */
        ERConfig.load();
        ERMap.registerVillagerTrades();
        LootInjection.initRegister();

        /* Blocks & Items */
        ERBlocks.initRegister();
        ERItems.initRegister();

        /* Features */
        OreSpawnHandler.init();
        ERJigsawStructures.setupAndRegisterStructureFeatures();
        ERJConfiguredStructure.registerConfiguredStructures();
        ERConfiguredStructure.registerConfiguredStructures();
        DimensionCheck.removeStructureSpawningFromSelectedDimension();
    }

    public final static MultiLocator EYE_ML = new MultiLocator();
    public final static MultiLocator MAP_ML = new MultiLocator();
}
