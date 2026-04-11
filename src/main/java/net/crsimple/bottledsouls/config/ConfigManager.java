package net.crsimple.bottledsouls.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.crsimple.bottledsouls.ModMain;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ConfigManager {
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(ModMain.MOD_ID + ".json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        save(ModMain.CONFIG);
    }

    public static Config load() {
        return load(ModMain.CONFIG);
    }

    public static void save(Config config) {
        Objects.requireNonNull(config);
        try {
            Files.writeString(CONFIG_PATH,GSON.toJson(config));
        } catch (IOException e) {
            ModMain.LOGGER.error("Failed to save {} config!",ModMain.MOD_ID);
            throw new RuntimeException(e);
        }
    }
    public static @NotNull Config load(Config def) {
        Objects.requireNonNull(def);
        try {
            if (Files.exists(CONFIG_PATH)) {
                return GSON.fromJson(Files.newBufferedReader(CONFIG_PATH), Config.class);
            }
            save(def);
        } catch (IOException e) {
            ModMain.LOGGER.error("Failed to load {} config!",ModMain.MOD_ID);
            throw new RuntimeException(e);
        }
        ModMain.LOGGER.warn("Loading default {} config",ModMain.MOD_ID);
        return def;
    }
}
