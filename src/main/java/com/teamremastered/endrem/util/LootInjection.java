package com.teamremastered.endrem.util;

import com.teamremastered.endrem.config.ERConfigHandler;
import com.teamremastered.endrem.registry.ERItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;


public class LootInjection {

    //Function to get the injection path and ModId

    private static String getInjection(String lootTableID, Boolean isModId) {

        String[] injectionParts = lootTableID.split(":");
        String modId = injectionParts[0];
        String path = injectionParts[1];

        if (isModId) {
            return modId;
        }
        else {
            return  path;
        }
    }

    /* Chests */
    private static final Identifier JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.ROGUE_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.ROGUE_EYE_LOOT_TABLE_ID,false));

    private static final Identifier OUTPOST_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.CORRUPTED_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.CORRUPTED_EYE_LOOT_TABLE_ID,false));

    private static final Identifier BURIED_TREASURE_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.BLACK_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.BLACK_EYE_LOOT_TABLE_ID,false));

    private static final Identifier MINESHAFT_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.LOST_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.LOST_EYE_LOOT_TABLE_ID,false));

    private static final Identifier DESERT_PYRAMID_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.OLD_EYE_LOOT_TABLE_ID, true), getInjection(ERConfigHandler.OLD_EYE_LOOT_TABLE_ID, false));

    private static final Identifier IGLOO_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.COLD_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.COLD_EYE_LOOT_TABLE_ID,false));

    private static final Identifier NETHER_BRIDGE_CHEST_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.NETHER_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.NETHER_EYE_LOOT_TABLE_ID,false));

    private static final Identifier BASTION_TREASURE_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.CURSED_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.CURSED_EYE_LOOT_TABLE_ID,false));

    private static final Identifier WOODLAND_MANSION_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.MAGICAL_EYE_MANSION_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.MAGICAL_EYE_MANSION_LOOT_TABLE_ID,false));


    /* Entities */
    private static final Identifier EVOKER_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.MAGICAL_EYE_EVOKER_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.MAGICAL_EYE_EVOKER_LOOT_TABLE_ID,false));
    private static final Identifier WITHER_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.WITHER_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.WITHER_EYE_LOOT_TABLE_ID,false));
    private static final Identifier ELDER_GUARDIAN_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.GUARDIAN_EYE_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.GUARDIAN_EYE_LOOT_TABLE_ID,false));
    private static final Identifier WITCH_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.WITCH_PUPIL_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.WITCH_PUPIL_LOOT_TABLE_ID,false));
    private static final Identifier SKELETON_HORSE_LOOT_TABLE_ID = new Identifier(getInjection(ERConfigHandler.UNDEAD_SOUL_LOOT_TABLE_ID,true), getInjection(ERConfigHandler.UNDEAD_SOUL_LOOT_TABLE_ID,false));



    public static void initRegister() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            /* Chests Loot */
            if (JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F)) // Same as "rolls": 1 in the loot table json
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.ROGUE_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.ROGUE_EYE));
                table.pool(poolBuilder);
            } else if (OUTPOST_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.CORRUPTED_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.CORRUPTED_EYE));
                table.pool(poolBuilder);
            }
            else if (BURIED_TREASURE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.BLACK_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.BLACK_EYE));
                table.pool(poolBuilder);
            }
            else if (MINESHAFT_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.LOST_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.LOST_EYE));
                table.pool(poolBuilder);
            }
            else if (DESERT_PYRAMID_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.OLD_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.OLD_EYE));
                table.pool(poolBuilder);
            }
            else if (IGLOO_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.COLD_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.COLD_EYE));
                table.pool(poolBuilder);
            }
            else if (NETHER_BRIDGE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.NETHER_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.NETHER_EYE));
                table.pool(poolBuilder);
            }

            else if (BASTION_TREASURE_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.CURSED_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.CURSED_EYE));
                table.pool(poolBuilder);
            }
            else if (WOODLAND_MANSION_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.MAGICAL_EYE_MANSION_WEIGHT))
                        .with(ItemEntry.builder(ERItems.MAGICAL_EYE));
                table.pool(poolBuilder);
            }

            /* Entities Loot */
            else if (EVOKER_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.MAGICAL_EYE_EVOKER_WEIGHT))
                        .with(ItemEntry.builder(ERItems.MAGICAL_EYE));
                table.pool(poolBuilder);
            }
            else if (WITHER_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.WITHER_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.WITHER_EYE));
                table.pool(poolBuilder);
            }
            else if (ELDER_GUARDIAN_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.GUARDIAN_EYE_WEIGHT))
                        .with(ItemEntry.builder(ERItems.GUARDIAN_EYE));
                table.pool(poolBuilder);
            }
            else if (WITCH_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.WITCH_PUPIL_WEIGHT))
                        .with(ItemEntry.builder(ERItems.WITCH_PUPIL));
                table.pool(poolBuilder);
            }
            else if (SKELETON_HORSE_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(ERConfigHandler.UNDEAD_SOUL_WEIGHT))
                        .with(ItemEntry.builder(ERItems.UNDEAD_SOUL));
                table.pool(poolBuilder);
            }
        });
    }
}
