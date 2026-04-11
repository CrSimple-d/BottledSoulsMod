package net.crsimple.bottledsouls;

import net.crsimple.bottledsouls.config.Config;
import net.crsimple.bottledsouls.config.ConfigInitializer;
import net.crsimple.bottledsouls.registry.ModCommands;
import net.crsimple.bottledsouls.registry.ModCompats;
import net.crsimple.bottledsouls.registry.ModGroups;
import net.crsimple.bottledsouls.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMain implements ModInitializer {
    public static String MOD_ID = "bottled_souls";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Config CONFIG = new Config();

    @Override
    public void onInitialize() {
        ConfigInitializer.init();
        ModItems.init();
        ModCommands.init();
        ModGroups.init();
        ModCompats.init();
        LOGGER.info("Hello! Bottled Souls successfully initialized");
    }

    public static Identifier idOf(String s) {
        return new Identifier(MOD_ID,s);
    }
}
