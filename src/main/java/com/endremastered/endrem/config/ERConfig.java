package com.endremastered.endrem.config;

import com.endremastered.endrem.EndRemastered;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;


public class ERConfig {

    private static Path configFilePath;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static boolean DISABLE_STRONGHOLD = true;
    public static boolean DISABLE_EYE_OF_ENDER = true;
    public static String EYES_LOCATE_STRUCTURE = "endrem:end_gate";
    public static String MAP_LOCATES_STRUCTURE = "endrem:end_castle";

    public static void load() {

        Reader reader;
        if(getFilePath().toFile().exists()) {
            try {
                reader = Files.newBufferedReader(getFilePath());

                Data data = gson.fromJson(reader, Data.class);

                DISABLE_STRONGHOLD = data.common.DISABLE_STRONGHOLD;
                DISABLE_EYE_OF_ENDER = data.common.DISABLE_EYE_OF_ENDER;
                EYES_LOCATE_STRUCTURE = data.common.EYES_LOCATE_STRUCTURE;
                MAP_LOCATES_STRUCTURE = data.common.MAP_LOCATES_STRUCTURE;
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
            Data data = new Data(new Data.Common(DISABLE_STRONGHOLD, DISABLE_EYE_OF_ENDER, EYES_LOCATE_STRUCTURE, MAP_LOCATES_STRUCTURE));
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
            private final String disableStrongholdComment = "Enable/Disable the Stronghold";
            private final boolean DISABLE_STRONGHOLD;

            private final String disableEyeOfEnderComment = "Enable/Disable usage of Ender Eyes";
            private final boolean DISABLE_EYE_OF_ENDER;

            private final String eyesLocateStructureComment = "Changes the structure that End Remastered eyes track (set value to \"null\" to disable)";
            private final String EYES_LOCATE_STRUCTURE;

            private final String mapLocateStructureComment = "Changes the structure that End Remastered map locate (set value to \"null\" to disable)";
            private final String MAP_LOCATES_STRUCTURE;

            private Common() {
                DISABLE_STRONGHOLD = true;
                DISABLE_EYE_OF_ENDER = true;
                EYES_LOCATE_STRUCTURE = "endrem:end_gate";
                MAP_LOCATES_STRUCTURE = "endrem:end_castle";
            }

            private Common(boolean DISABLE_STRONGHOLD, boolean DISABLE_EYE_OF_ENDER, String EYES_LOCATE_STRUCTURE, String MAP_LOCATES_STRUCTURE) {
                this.DISABLE_STRONGHOLD = DISABLE_STRONGHOLD;
                this.DISABLE_EYE_OF_ENDER = DISABLE_EYE_OF_ENDER;
                this.EYES_LOCATE_STRUCTURE = EYES_LOCATE_STRUCTURE;
                this.MAP_LOCATES_STRUCTURE = MAP_LOCATES_STRUCTURE;
            }
        }
    }
}