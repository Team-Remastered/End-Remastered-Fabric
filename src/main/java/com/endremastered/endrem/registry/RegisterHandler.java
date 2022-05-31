package com.endremastered.endrem.registry;

import com.endremastered.endrem.config.ERConfig;
import com.endremastered.endrem.items.ERTrades;
import com.endremastered.endrem.util.LootInjection;

public class RegisterHandler {

    public static void init() {
        /* Miscellaneous */
        ERConfig.load();
        ERTrades.registerVillagerTrades();
        LootInjection.initRegister();

        /* Blocks & Items */
        ERBlocks.initRegister();
        ERItems.initRegister();

    }
}
