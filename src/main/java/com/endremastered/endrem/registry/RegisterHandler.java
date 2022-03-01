package com.endremastered.endrem.registry;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.items.ERMap;
import com.endremastered.endrem.util.LootInjection;
import com.endremastered.endrem.util.MultiLocator;
import com.endremastered.endrem.world.ERStructureConfig.ERConfiguredStructures;
import com.endremastered.endrem.world.gen.OreSpawnHandler;
import com.endremastered.endrem.world.ERStructureConfig.ERStructures;
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
        ERStructures.setupAndRegisterStructureFeatures();
        ERConfiguredStructures.registerConfiguredStructures();
        DimensionCheck.removeStructureSpawningFromSelectedDimension();
        ERProcessors.init();
    }

    public final static MultiLocator EYE_ML = new MultiLocator();
    public final static MultiLocator MAP_ML = new MultiLocator();
}
