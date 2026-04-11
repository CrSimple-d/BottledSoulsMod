package net.crsimple.bottledsouls.registry;

import net.crsimple.bottledsouls.common.commands.BottledSoulsCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, cra, re) -> {
            BottledSoulsCommand.reg(dispatcher);
        });
    }
}
