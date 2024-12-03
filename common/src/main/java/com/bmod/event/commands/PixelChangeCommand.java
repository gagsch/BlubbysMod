package com.bmod.event.commands;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ScoreHolderArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;

import java.util.*;

public class PixelChangeCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("pixel")
                .then(Commands.argument("position", BlockPosArgument.blockPos())
                        .then(Commands.argument("red", IntegerArgumentType.integer(0, 255))
                                .then(Commands.argument("green", IntegerArgumentType.integer(0, 255))
                                        .then(Commands.argument("blue", IntegerArgumentType.integer(0, 255))
                                                .executes(PixelChangeCommand::runExecuteChange)))));
    }

    public static LiteralArgumentBuilder<CommandSourceStack> buildSecondary() {
        return Commands.literal("advanced_pixels")
                .then(Commands.argument("position", ScoreHolderArgument.scoreHolders())
                        .then(Commands.argument("red", IntegerArgumentType.integer(0, 255))
                                .then(Commands.argument("green", IntegerArgumentType.integer(0, 255))
                                        .then(Commands.argument("blue", IntegerArgumentType.integer(0, 255))
                                                .executes(PixelChangeCommand::runExecuteScoreChange)))))
                .requires((commandSourceStack -> commandSourceStack.hasPermission(2)));
    }

    private static int runExecuteChange(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "position");
        ServerPlayer owner = ctx.getSource().getPlayer();

        if (ctx.getSource().getLevel().getBlockEntity(pos) instanceof PixelBlockEntity pixel && (ctx.getSource().hasPermission(2) || (owner != null && pixel.isOwner(owner))))
        {
            int red = IntegerArgumentType.getInteger(ctx, "red");
            int green = IntegerArgumentType.getInteger(ctx, "green");
            int blue = IntegerArgumentType.getInteger(ctx, "blue");

            pixel.setColor(red, green, blue);
        }

        return 1;
    }

    private static int runExecuteScoreChange(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Scoreboard scoreboard = ctx.getSource().getServer().getScoreboard();
        Objective xObjective = scoreboard.getObjective("x");
        Objective yObjective = scoreboard.getObjective("y");
        Objective zObjective = scoreboard.getObjective("z");

        if (xObjective == null || yObjective == null || zObjective == null) {
            ctx.getSource().sendFailure(Component.literal("Objective does not exist. Make sure you have all three {x, y, z} Objectives!"));
            return 0;
        }

        Collection<String> posHolders = ScoreHolderArgument.getNames(ctx, "position");
        int red = IntegerArgumentType.getInteger(ctx, "red");
        int green = IntegerArgumentType.getInteger(ctx, "green");
        int blue = IntegerArgumentType.getInteger(ctx, "blue");

        Set<BlockPos> uniquePositions = new HashSet<>();

        for (String posHolder : posHolders) {
            Score xScore = scoreboard.getOrCreatePlayerScore(posHolder, xObjective);
            Score yScore = scoreboard.getOrCreatePlayerScore(posHolder, yObjective);
            Score zScore = scoreboard.getOrCreatePlayerScore(posHolder, zObjective);

            uniquePositions.add(new BlockPos(xScore.getScore(), yScore.getScore(), zScore.getScore()));
        }

        PixelBlockEntity.setColors(ctx.getSource().getLevel(), red, green, blue, uniquePositions);

        return 1;
    }
}