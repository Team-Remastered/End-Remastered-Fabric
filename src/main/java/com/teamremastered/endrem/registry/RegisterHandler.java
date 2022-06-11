package com.teamremastered.endrem.registry;

import com.teamremastered.endrem.config.ERConfigHandler;
import com.teamremastered.endrem.items.ERTrades;
import com.teamremastered.endrem.util.LootInjection;

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
