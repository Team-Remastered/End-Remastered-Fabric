package com.endremastered.endrem.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;


public class StructureLocator {
    public static StructureFeature<?> getStructureToLocate(String structureID) {
        String mod_id = structureID.split(":")[0];

        // Checks if mod is loaded
        if (FabricLoader.getInstance().isModLoaded(mod_id)) {
            Identifier resourceLocation = new Identifier(structureID);

            // Checks if structure exists
            if (Registry.STRUCTURE_FEATURE.containsId(resourceLocation)) {
                return Registry.STRUCTURE_FEATURE.get(resourceLocation);
            }
        }

        // Returns null if structure is invalid
        return null;
    }
}
