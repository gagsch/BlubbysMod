package com.bmod.event.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class MultiplyTpCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> build()
    {
        return Commands.literal("multp")
                .then(Commands.argument("entity", EntityArgument.entity())
                        .then(Commands.argument("factor", DoubleArgumentType.doubleArg())))
                .requires((commandSourceStack) -> commandSourceStack.hasPermission(2))
                .executes(MultiplyTpCommand::runExecuteTeleport);
    }

    private static int runExecuteTeleport(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
        Double factor = ctx.getArgument("factor", Double.class);

        player.teleportTo(player.getX() * factor, player.getY(), player.getZ() * factor);
        return 0;
    }
}
