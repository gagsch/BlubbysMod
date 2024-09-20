package com.bmod.registry;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.CommandPerformEvent;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

public class ModCommands {
    public static void initialize()
    {
        events();
    }

    public static void events() {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
            LiteralArgumentBuilder<CommandSourceStack> teleportCommand = Commands.literal("multiplytp")
                    .then(
                            Commands.argument("player", EntityArgument.player())
                                    .then(
                                            Commands.argument("factor", DoubleArgumentType.doubleArg())
                                                    .executes(ModCommands::executeTeleportCommand)))
                    .requires(ModCommands::hasPermission);
            dispatcher.register(teleportCommand);
        });
    }

    private static int executeTeleportCommand(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
        Double factor = ctx.getArgument("factor", Double.class);

        player.teleportTo((player.getX() * factor),player.getY(),(player.getZ() * factor));
        return 1;
    }

    private static boolean hasPermission(CommandSourceStack commandSourceStack) {
        return commandSourceStack.hasPermission(2);
    }
}
