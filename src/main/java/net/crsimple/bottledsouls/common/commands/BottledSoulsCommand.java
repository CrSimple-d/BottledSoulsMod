package net.crsimple.bottledsouls.common.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.crsimple.bottledsouls.util.CommandHelper;
import net.crsimple.bottledsouls.compat.LuckPermsCompat;
import net.crsimple.bottledsouls.config.ConfigInitializer;
import net.minecraft.command.DefaultPermissions;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class BottledSoulsCommand {
    public static void reg(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("bottledsouls")
                .then(CommandManager.literal("reload")
                        .executes(BottledSoulsCommand::reloadConfig)
                        .requires(s -> LuckPermsCompat.checkPermissionIfEnable(s.getPlayer(),LuckPermsCompat.Perm.CONFIG_RELOADING_PERM) ||
                                s.getPermissions().hasPermission(DefaultPermissions.OWNERS))));
    }

    private static int reloadConfig(CommandContext<ServerCommandSource> ctx) {
        ConfigInitializer.reload();
        CommandHelper.reply(ctx, Text.translatable("command.bottled_souls.config:reloaded")
                .formatted(Formatting.YELLOW));
        return Command.SINGLE_SUCCESS;
    }
}
