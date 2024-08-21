package me.blubby.bmod.server.command;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID)
public class multiplytp {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        LiteralArgumentBuilder<CommandSourceStack> teleportCommand =
                Commands.literal("multiplytp")
                        .then(
                            Commands.argument("player", EntityArgument.player())
                                    .then(
                                        Commands.argument("factor", DoubleArgumentType.doubleArg())
                                                .executes(multiplytp::executeTeleportCommand)))
                        .requires(multiplytp::hasPermission);
        event.getDispatcher().register(teleportCommand);
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
