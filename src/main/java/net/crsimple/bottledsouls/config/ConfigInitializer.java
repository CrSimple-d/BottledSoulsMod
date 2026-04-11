package net.crsimple.bottledsouls.config;

import net.crsimple.bottledsouls.ModMain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ConfigInitializer {
    public static void init() {
        reload();
        ServerLifecycleEvents.SERVER_STOPPING.register(s -> ConfigManager.save());
    }
    public static void reload() {
        ModMain.CONFIG = ConfigManager.load();
    }
}
