package com.teamremastered.endrem.util;

import com.teamremastered.endrem.registry.ERItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootInjection {

    /* Chests */
    private static final Identifier MINESHAFT_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/abandoned_mineshaft");
    private static final Identifier BURIED_TREASURE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier DESERT_PYRAMID_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");
    private static final Identifier IGLOO_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/igloo_chest");
    private static final Identifier JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier NETHER_BRIDGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/nether_bridge");
    private static final Identifier OUTPOST_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier SHIPWRECK_TREASURE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/shipwreck_treasure");
    private static final Identifier DUNGEON_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier BASTION_TREASURE_LOOT_TABLE_ID = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier WOODLAND_MANSION_LOOT_TABLE_ID = new Identifier("minecraft", "chests/woodland_mansion");


    /* Entities */
    private static final Identifier EVOKER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/evoker");
    private static final Identifier WITHER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither");
    private static final Identifier ELDER_GUARDIAN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/elder_guardian");
    private static final Identifier WITCH_LOOT_TABLE_ID = new Identifier("minecraft", "entities/witch");
    private static final Identifier SKELETON_HORSE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/skeleton_horse");



    public static void initRegister() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            /* Chests Loot */
            if (JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F)) // Same as "rolls": 1 in the loot table json
                        .conditionally(RandomChanceLootCondition.builder(0.4F))
                        .with(ItemEntry.builder(ERItems.ROGUE_EYE));
                table.pool(poolBuilder);
            } else if (OUTPOST_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.3F))
                        .with(ItemEntry.builder(ERItems.CORRUPTED_EYE));
                table.pool(poolBuilder);
            }
            else if (BURIED_TREASURE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.3F))
                        .with(ItemEntry.builder(ERItems.BLACK_EYE));
                table.pool(poolBuilder);
            }
            else if (MINESHAFT_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.2F))
                        .with(ItemEntry.builder(ERItems.LOST_EYE));
                table.pool(poolBuilder);
            }
            else if (DUNGEON_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(ERItems.LOST_EYE))
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(ERItems.OLD_EYE));
                table.pool(poolBuilder);
            }
            else if (DESERT_PYRAMID_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.10F))
                        .with(ItemEntry.builder(ERItems.OLD_EYE));
                table.pool(poolBuilder);
            }
            else if (IGLOO_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.70F))
                        .with(ItemEntry.builder(ERItems.COLD_EYE));
                table.pool(poolBuilder);
            }
            else if (NETHER_BRIDGE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.3F))
                        .with(ItemEntry.builder(ERItems.NETHER_EYE));
                table.pool(poolBuilder);
            }
            else if (SHIPWRECK_TREASURE_CHEST_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(ERItems.BLACK_EYE));
                table.pool(poolBuilder);
            }

            /* Entities Loot */
            else if (EVOKER_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(ERItems.MAGICAL_EYE));
                table.pool(poolBuilder);
            }
            else if (WITHER_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.9F))
                        .with(ItemEntry.builder(ERItems.WITHER_EYE));
                table.pool(poolBuilder);
            }
            else if (ELDER_GUARDIAN_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.3F))
                        .with(ItemEntry.builder(ERItems.GUARDIAN_EYE));
                table.pool(poolBuilder);
            }
            else if (WITCH_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.10F))
                        .with(ItemEntry.builder(ERItems.WITCH_PUPIL));
                table.pool(poolBuilder);
            }
            else if (BASTION_TREASURE_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.5F))
                        .with(ItemEntry.builder(ERItems.CURSED_EYE));
                table.pool(poolBuilder);
            }
            else if (WOODLAND_MANSION_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.1F))
                        .with(ItemEntry.builder(ERItems.MAGICAL_EYE));
                table.pool(poolBuilder);
            }
            else if (SKELETON_HORSE_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.25F))
                        .with(ItemEntry.builder(ERItems.UNDEAD_SOUL));
                table.pool(poolBuilder);
            }
        });
    }
}
