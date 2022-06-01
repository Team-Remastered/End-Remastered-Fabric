package com.endremastered.endrem.registry;

import com.endremastered.endrem.config.ERConfigHandler;
import com.endremastered.endrem.items.ERTrades;
import com.endremastered.endrem.util.LootInjection;

public class RegisterHandler {

    public static void init() {
        /* Miscellaneous */
        ERConfigHandler.load();
        ERTrades.registerVillagerTrades();
        LootInjection.initRegister();

        /* Blocks & Items */
        ERBlocks.initRegister();
        ERItems.initRegister();

    }
}
