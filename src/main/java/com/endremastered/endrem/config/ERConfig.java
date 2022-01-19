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
    private static final Path FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve(
            String.format("%s.json", EndRemastered.MOD_ID));
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static ConfigData configData = new ConfigData();

    public static void load() {
        if(FILE_PATH.toFile().exists()) {
            try {
                Reader reader = Files.newBufferedReader(FILE_PATH);
                configData = gson.fromJson(reader, ConfigData.class);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        save();
    }

    private static void save() {
        try {
            Writer writer = Files.newBufferedWriter(FILE_PATH);
            gson.toJson(configData, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigData getData(){
        load();
        return configData;
    }
}