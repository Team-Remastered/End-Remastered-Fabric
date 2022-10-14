package com.teamremastered.endrem.config;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import com.teamremastered.endrem.EndRemastered;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;


public class ERConfigHandler {

    private static Path configFilePath;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static boolean ENABLE_EYE_OF_ENDER = false;
    public static int EYE_BREAK_PROBABILITY = 10;
    public static boolean IS_CRYPTIC_EYE_OBTAINABLE = true;
    public static boolean IS_EVIL_EYE_OBTAINABLE = true;


    public static String ROGUE_EYE_LOOT_TABLE_ID = "minecraft:chests/jungle_temple";
    public static float ROGUE_EYE_WEIGHT = 0.40F;
    public static String CORRUPTED_EYE_LOOT_TABLE_ID = "minecraft:chests/pillager_outpost";
    public static float CORRUPTED_EYE_WEIGHT = 0.30F;
    public static String BLACK_EYE_LOOT_TABLE_ID = "minecraft:chests/buried_treasure";
    public static float BLACK_EYE_WEIGHT = 0.30F;
    public static String LOST_EYE_LOOT_TABLE_ID = "minecraft:chests/abandoned_mineshaft";
    public static float LOST_EYE_WEIGHT = 0.20F;
    public static String OLD_EYE_LOOT_TABLE_ID = "minecraft:chests/desert_pyramid";
    public static float OLD_EYE_WEIGHT = 0.15F;
    public static String COLD_EYE_LOOT_TABLE_ID = "minecraft:chests/igloo_chest";
    public static float COLD_EYE_WEIGHT = 0.80F;
    public static String NETHER_EYE_LOOT_TABLE_ID = "minecraft:chests/nether_bridge";
    public static float NETHER_EYE_WEIGHT = 0.30F;
    public static String CURSED_EYE_LOOT_TABLE_ID = "minecraft:chests/bastion_treasure";
    public static float CURSED_EYE_WEIGHT = 0.50F;
    public static String MAGICAL_EYE_MANSION_LOOT_TABLE_ID = "minecraft:chests/woodland_mansion";
    public static float MAGICAL_EYE_MANSION_WEIGHT = 0.15F;

    public static String MAGICAL_EYE_EVOKER_LOOT_TABLE_ID = "minecraft:entities/evoker";
    public static float MAGICAL_EYE_EVOKER_WEIGHT = 0.05F;
    public static String WITHER_EYE_LOOT_TABLE_ID = "minecraft:entities/wither";
    public static float WITHER_EYE_WEIGHT = 0.90F;
    public static String GUARDIAN_EYE_LOOT_TABLE_ID = "minecraft:entities/elder_guardian";
    public static float GUARDIAN_EYE_WEIGHT = 0.30F;
    public static String WITCH_PUPIL_LOOT_TABLE_ID = "minecraft:entities/witch";
    public static float WITCH_PUPIL_WEIGHT = 0.10F;
    public static String UNDEAD_SOUL_LOOT_TABLE_ID = "minecraft:entities/skeleton_horse";
    public static float UNDEAD_SOUL_WEIGHT = 0.25F;

    public static void load() {

        Reader reader;
        if(getFilePath().toFile().exists()) {
            try {
                reader = Files.newBufferedReader(getFilePath());

                Data data = gson.fromJson(reader, Data.class);

                ENABLE_EYE_OF_ENDER = data.common.ENABLE_EYE_OF_ENDER;
                EYE_BREAK_PROBABILITY = data.common.EYE_BREAK_PROBABILITY;
                IS_CRYPTIC_EYE_OBTAINABLE = data.common.IS_CRYPTIC_EYE_OBTAINABLE;
                IS_EVIL_EYE_OBTAINABLE = data.common.IS_EVIL_EYE_OBTAINABLE;

                ROGUE_EYE_LOOT_TABLE_ID = data.common.ROGUE_EYE_LOOT_TABLE_ID;
                ROGUE_EYE_WEIGHT = data.common.ROGUE_EYE_WEIGHT;
                CORRUPTED_EYE_LOOT_TABLE_ID = data.common.CORRUPTED_EYE_LOOT_TABLE_ID;
                CORRUPTED_EYE_WEIGHT = data.common.CORRUPTED_EYE_WEIGHT;
                BLACK_EYE_LOOT_TABLE_ID = data.common.BLACK_EYE_LOOT_TABLE_ID;
                BLACK_EYE_WEIGHT = data.common.BLACK_EYE_WEIGHT;
                LOST_EYE_LOOT_TABLE_ID = data.common.LOST_EYE_LOOT_TABLE_ID;
                LOST_EYE_WEIGHT = data.common.LOST_EYE_WEIGHT;
                OLD_EYE_LOOT_TABLE_ID = data.common.OLD_EYE_LOOT_TABLE_ID;
                OLD_EYE_WEIGHT = data.common.OLD_EYE_WEIGHT;
                COLD_EYE_LOOT_TABLE_ID = data.common.COLD_EYE_LOOT_TABLE_ID;
                COLD_EYE_WEIGHT = data.common.COLD_EYE_WEIGHT;
                NETHER_EYE_LOOT_TABLE_ID = data.common.NETHER_EYE_LOOT_TABLE_ID;
                NETHER_EYE_WEIGHT = data.common.NETHER_EYE_WEIGHT;
                CURSED_EYE_LOOT_TABLE_ID = data.common.CURSED_EYE_LOOT_TABLE_ID;
                CURSED_EYE_WEIGHT = data.common.CURSED_EYE_WEIGHT;
                MAGICAL_EYE_MANSION_LOOT_TABLE_ID = data.common.MAGICAL_EYE_MANSION_LOOT_TABLE_ID;
                MAGICAL_EYE_MANSION_WEIGHT = data.common.MAGICAL_EYE_MANSION_WEIGHT;
                MAGICAL_EYE_EVOKER_LOOT_TABLE_ID = data.common.MAGICAL_EYE_EVOKER_LOOT_TABLE_ID;
                MAGICAL_EYE_EVOKER_WEIGHT = data.common.MAGICAL_EYE_EVOKER_WEIGHT;
                WITHER_EYE_LOOT_TABLE_ID = data.common.WITHER_EYE_LOOT_TABLE_ID;
                WITHER_EYE_WEIGHT = data.common.WITHER_EYE_WEIGHT;
                GUARDIAN_EYE_LOOT_TABLE_ID = data.common.GUARDIAN_EYE_LOOT_TABLE_ID;
                GUARDIAN_EYE_WEIGHT = data.common.GUARDIAN_EYE_WEIGHT;
                WITCH_PUPIL_LOOT_TABLE_ID = data.common.WITCH_PUPIL_LOOT_TABLE_ID;
                WITCH_PUPIL_WEIGHT = data.common.WITCH_PUPIL_WEIGHT;
                UNDEAD_SOUL_LOOT_TABLE_ID = data.common.UNDEAD_SOUL_LOOT_TABLE_ID;
                UNDEAD_SOUL_WEIGHT = data.common.UNDEAD_SOUL_WEIGHT;
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        save();
    }

    public static void save() {
        try {
            Writer writer = Files.newBufferedWriter(getFilePath());
            Data data = new Data(new Data.Common(
                    ENABLE_EYE_OF_ENDER,
                    EYE_BREAK_PROBABILITY,
                    IS_CRYPTIC_EYE_OBTAINABLE,
                    IS_EVIL_EYE_OBTAINABLE,

                    ROGUE_EYE_LOOT_TABLE_ID,
                    ROGUE_EYE_WEIGHT,
                    CORRUPTED_EYE_LOOT_TABLE_ID,
                    CORRUPTED_EYE_WEIGHT,
                    BLACK_EYE_LOOT_TABLE_ID,
                    BLACK_EYE_WEIGHT,
                    LOST_EYE_LOOT_TABLE_ID,
                    LOST_EYE_WEIGHT,
                    OLD_EYE_LOOT_TABLE_ID,
                    OLD_EYE_WEIGHT,
                    COLD_EYE_LOOT_TABLE_ID,
                    COLD_EYE_WEIGHT,
                    NETHER_EYE_LOOT_TABLE_ID,
                    NETHER_EYE_WEIGHT,
                    CURSED_EYE_LOOT_TABLE_ID,
                    CURSED_EYE_WEIGHT,
                    MAGICAL_EYE_MANSION_LOOT_TABLE_ID,
                    MAGICAL_EYE_MANSION_WEIGHT,
                    MAGICAL_EYE_EVOKER_LOOT_TABLE_ID,
                    MAGICAL_EYE_EVOKER_WEIGHT,
                    WITHER_EYE_LOOT_TABLE_ID,
                    WITHER_EYE_WEIGHT,
                    GUARDIAN_EYE_LOOT_TABLE_ID,
                    GUARDIAN_EYE_WEIGHT,
                    WITCH_PUPIL_LOOT_TABLE_ID,
                    WITCH_PUPIL_WEIGHT,
                    UNDEAD_SOUL_LOOT_TABLE_ID,
                    UNDEAD_SOUL_WEIGHT));
            gson.toJson(data, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getFilePath() {
        if(configFilePath == null) {
            configFilePath = FabricLoader.getInstance().getConfigDir().resolve(EndRemastered.MOD_ID + ".json");
        }
        return configFilePath;
    }

    private static class Data {

        private Common common;

        public Data(Common common) {
            this.common = common;
        }

        private static class Common {

            private final String enableEyeOfEnderComment = "Enable/Disable usage of Ender Eyes";
            private final boolean ENABLE_EYE_OF_ENDER;

            private final String eyeBreakProbabilityComment = "Percentage chance of eyes breaking when thrown";
            private final int EYE_BREAK_PROBABILITY;

            private final String crypticAndEvilEyeComment = "Decides if the cryptic eye and the evil eye is obtainable in game";
            private final boolean IS_CRYPTIC_EYE_OBTAINABLE;
            private final boolean IS_EVIL_EYE_OBTAINABLE;

            private final String lootTablesIdComment = "this below is what you want to modify if you wanna change the loot tables of the eyes and their chance to appear.";
            private final String ROGUE_EYE_LOOT_TABLE_ID;
            private final float ROGUE_EYE_WEIGHT;
            private final String CORRUPTED_EYE_LOOT_TABLE_ID;
            private final float CORRUPTED_EYE_WEIGHT;
            private final String BLACK_EYE_LOOT_TABLE_ID;
            private final float BLACK_EYE_WEIGHT;
            private final String LOST_EYE_LOOT_TABLE_ID;
            private final float LOST_EYE_WEIGHT;
            private final String OLD_EYE_LOOT_TABLE_ID;
            private final float OLD_EYE_WEIGHT;
            private final String COLD_EYE_LOOT_TABLE_ID;
            private final float COLD_EYE_WEIGHT;
            private final String NETHER_EYE_LOOT_TABLE_ID;
            private final float NETHER_EYE_WEIGHT;
            private final String CURSED_EYE_LOOT_TABLE_ID;
            private final float CURSED_EYE_WEIGHT;
            private final String MAGICAL_EYE_MANSION_LOOT_TABLE_ID;
            private final float MAGICAL_EYE_MANSION_WEIGHT;
            private final String MAGICAL_EYE_EVOKER_LOOT_TABLE_ID;
            private final float MAGICAL_EYE_EVOKER_WEIGHT;
            private final String WITHER_EYE_LOOT_TABLE_ID;
            private final float WITHER_EYE_WEIGHT;
            private final String GUARDIAN_EYE_LOOT_TABLE_ID;
            private final float GUARDIAN_EYE_WEIGHT;
            private final String WITCH_PUPIL_LOOT_TABLE_ID;
            private final float WITCH_PUPIL_WEIGHT;
            private final String UNDEAD_SOUL_LOOT_TABLE_ID;
            private final float UNDEAD_SOUL_WEIGHT;


            private Common() {
                 ENABLE_EYE_OF_ENDER = false;
                 EYE_BREAK_PROBABILITY = 10;
                 IS_CRYPTIC_EYE_OBTAINABLE = true;
                 IS_EVIL_EYE_OBTAINABLE = true;

                 ROGUE_EYE_LOOT_TABLE_ID = "minecraft:chests/jungle_temple";
                 ROGUE_EYE_WEIGHT = 0.40F;
                 CORRUPTED_EYE_LOOT_TABLE_ID = "minecraft:chests/pillager_outpost";
                 CORRUPTED_EYE_WEIGHT = 0.30F;
                 BLACK_EYE_LOOT_TABLE_ID = "minecraft:chests/buried_treasure";
                 BLACK_EYE_WEIGHT = 0.30F;
                 LOST_EYE_LOOT_TABLE_ID = "minecraft:chests/abandoned_mineshaft";
                 LOST_EYE_WEIGHT = 0.20F;
                 OLD_EYE_LOOT_TABLE_ID = "minecraft:chests/desert_pyramid";
                 OLD_EYE_WEIGHT = 0.15F;
                 COLD_EYE_LOOT_TABLE_ID = "minecraft:chests/igloo_chest";
                 COLD_EYE_WEIGHT = 0.80F;
                 NETHER_EYE_LOOT_TABLE_ID = "minecraft:chests/nether_bridge";
                 NETHER_EYE_WEIGHT = 0.30F;
                 CURSED_EYE_LOOT_TABLE_ID = "minecraft:chests/bastion_treasure";
                 CURSED_EYE_WEIGHT = 0.50F;
                 MAGICAL_EYE_MANSION_LOOT_TABLE_ID = "minecraft:chests/woodland_mansion";
                 MAGICAL_EYE_MANSION_WEIGHT = 0.15F;

                 MAGICAL_EYE_EVOKER_LOOT_TABLE_ID = "minecraft:entities/evoker";
                 MAGICAL_EYE_EVOKER_WEIGHT = 0.05F;
                 WITHER_EYE_LOOT_TABLE_ID = "minecraft:entities/wither";
                 WITHER_EYE_WEIGHT = 0.90F;
                 GUARDIAN_EYE_LOOT_TABLE_ID = "minecraft:entities/elder_guardian";
                 GUARDIAN_EYE_WEIGHT = 0.30F;
                 WITCH_PUPIL_LOOT_TABLE_ID = "minecraft:entities/witch";
                 WITCH_PUPIL_WEIGHT = 0.10F;
                 UNDEAD_SOUL_LOOT_TABLE_ID = "minecraft:entities/skeleton_horse";
                 UNDEAD_SOUL_WEIGHT = 0.25F;
            }

            private Common(
                                   boolean ENABLE_EYE_OF_ENDER,
                                   int EYE_BREAK_PROBABILITY,
                                   boolean IS_CRYPTIC_EYE_OBTAINABLE,
                                   boolean IS_EVIL_EYE_OBTAINABLE,

                                   String ROGUE_EYE_LOOT_TABLE_ID,
                                   float ROGUE_EYE_WEIGHT,
                                   String CORRUPTED_EYE_LOOT_TABLE_ID,
                                   float CORRUPTED_EYE_WEIGHT,
                                   String BLACK_EYE_LOOT_TABLE_ID,
                                   float BLACK_EYE_WEIGHT,
                                   String LOST_EYE_LOOT_TABLE_ID,
                                   float LOST_EYE_WEIGHT,
                                   String OLD_EYE_LOOT_TABLE_ID,
                                   float OLD_EYE_WEIGHT,
                                   String COLD_EYE_LOOT_TABLE_ID,
                                   float COLD_EYE_WEIGHT,
                                   String NETHER_EYE_LOOT_TABLE_ID,
                                   float NETHER_EYE_WEIGHT,
                                   String CURSED_EYE_LOOT_TABLE_ID,
                                   float CURSED_EYE_WEIGHT,
                                   String MAGICAL_EYE_MANSION_LOOT_TABLE_ID,
                                   float MAGICAL_EYE_MANSION_WEIGHT,
                                   String MAGICAL_EYE_EVOKER_LOOT_TABLE_ID,
                                   float MAGICAL_EYE_EVOKER_WEIGHT,
                                   String WITHER_EYE_LOOT_TABLE_ID,
                                   float WITHER_EYE_WEIGHT,
                                   String GUARDIAN_EYE_LOOT_TABLE_ID,
                                   float GUARDIAN_EYE_WEIGHT,
                                   String WITCH_PUPIL_LOOT_TABLE_ID,
                                   float WITCH_PUPIL_WEIGHT,
                                   String UNDEAD_SOUL_LOOT_TABLE_ID,
                                   float UNDEAD_SOUL_WEIGHT)
            {
                this.ENABLE_EYE_OF_ENDER = ENABLE_EYE_OF_ENDER;
                this.EYE_BREAK_PROBABILITY = EYE_BREAK_PROBABILITY;
                this.IS_CRYPTIC_EYE_OBTAINABLE = IS_CRYPTIC_EYE_OBTAINABLE;
                this.IS_EVIL_EYE_OBTAINABLE = IS_EVIL_EYE_OBTAINABLE;

                this.ROGUE_EYE_LOOT_TABLE_ID = ROGUE_EYE_LOOT_TABLE_ID;
                this.ROGUE_EYE_WEIGHT = ROGUE_EYE_WEIGHT;
                this.CORRUPTED_EYE_LOOT_TABLE_ID = CORRUPTED_EYE_LOOT_TABLE_ID;
                this.CORRUPTED_EYE_WEIGHT = CORRUPTED_EYE_WEIGHT;
                this.BLACK_EYE_LOOT_TABLE_ID = BLACK_EYE_LOOT_TABLE_ID;
                this.BLACK_EYE_WEIGHT = BLACK_EYE_WEIGHT;
                this.LOST_EYE_LOOT_TABLE_ID = LOST_EYE_LOOT_TABLE_ID;
                this.LOST_EYE_WEIGHT = LOST_EYE_WEIGHT;
                this.OLD_EYE_LOOT_TABLE_ID = OLD_EYE_LOOT_TABLE_ID;
                this.OLD_EYE_WEIGHT = OLD_EYE_WEIGHT;
                this.COLD_EYE_LOOT_TABLE_ID = COLD_EYE_LOOT_TABLE_ID;
                this.COLD_EYE_WEIGHT = COLD_EYE_WEIGHT;
                this.NETHER_EYE_LOOT_TABLE_ID = NETHER_EYE_LOOT_TABLE_ID;
                this.NETHER_EYE_WEIGHT = NETHER_EYE_WEIGHT;
                this.CURSED_EYE_LOOT_TABLE_ID = CURSED_EYE_LOOT_TABLE_ID;
                this.CURSED_EYE_WEIGHT = CURSED_EYE_WEIGHT;
                this.MAGICAL_EYE_MANSION_LOOT_TABLE_ID = MAGICAL_EYE_MANSION_LOOT_TABLE_ID;
                this.MAGICAL_EYE_MANSION_WEIGHT = MAGICAL_EYE_MANSION_WEIGHT;
                this.MAGICAL_EYE_EVOKER_LOOT_TABLE_ID = MAGICAL_EYE_EVOKER_LOOT_TABLE_ID;
                this.MAGICAL_EYE_EVOKER_WEIGHT = MAGICAL_EYE_EVOKER_WEIGHT;
                this.WITHER_EYE_LOOT_TABLE_ID = WITHER_EYE_LOOT_TABLE_ID;
                this.WITHER_EYE_WEIGHT = WITHER_EYE_WEIGHT;
                this.GUARDIAN_EYE_LOOT_TABLE_ID = GUARDIAN_EYE_LOOT_TABLE_ID;
                this.GUARDIAN_EYE_WEIGHT = GUARDIAN_EYE_WEIGHT;
                this.WITCH_PUPIL_LOOT_TABLE_ID = WITCH_PUPIL_LOOT_TABLE_ID;
                this.WITCH_PUPIL_WEIGHT = WITCH_PUPIL_WEIGHT;
                this.UNDEAD_SOUL_LOOT_TABLE_ID = UNDEAD_SOUL_LOOT_TABLE_ID;
                this.UNDEAD_SOUL_WEIGHT = UNDEAD_SOUL_WEIGHT;

            }
        }
    }
}
