package net.crsimple.bottledsouls.util;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class CommandHelper {
    public static void reply(CommandContext<ServerCommandSource> ctx, Text text) {
        reply(ctx,text,false);
    }
    public static void reply(CommandContext<ServerCommandSource> ctx, Text text,boolean ops) {
        ctx.getSource().sendFeedback(text, ops);
    }
}
