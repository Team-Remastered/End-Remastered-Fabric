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

    public static void load() {

        Reader reader;
        if(getFilePath().toFile().exists()) {
            try {
                reader = Files.newBufferedReader(getFilePath());

                Data data = gson.fromJson(reader, Data.class);

                ENABLE_EYE_OF_ENDER = data.common.ENABLE_EYE_OF_ENDER;
                EYE_BREAK_PROBABILITY = data.common.EYE_BREAK_PROBABILITY;
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
            Data data = new Data(new Data.Common(ENABLE_EYE_OF_ENDER, EYE_BREAK_PROBABILITY));
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

            private Common() {
                ENABLE_EYE_OF_ENDER = false;
                EYE_BREAK_PROBABILITY = 10;
            }

            private Common(boolean ENABLE_EYE_OF_ENDER, int EYE_BREAK_PROBABILITY) {
                this.ENABLE_EYE_OF_ENDER = ENABLE_EYE_OF_ENDER;
                this.EYE_BREAK_PROBABILITY = EYE_BREAK_PROBABILITY;
            }
        }
    }
}
