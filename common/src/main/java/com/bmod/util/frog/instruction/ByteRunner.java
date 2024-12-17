package com.bmod.util.frog.instruction;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.util.frog.FrogParser;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

import static com.bmod.util.frog.util.ByteUtils.ifStatement;
import static com.bmod.util.frog.util.ByteUtils.skipIfStatement;
import static com.bmod.util.frog.util.ObjectUtils.tryParseValue;

public class ByteRunner {
    // Gives functionality to keywords instead of leaving them as variables
    public static final BiFunction<FrogParser, Integer, Integer> RUN_PRINT = ByteRunner::runPrint;
    public static final BiFunction<FrogParser, Integer, Integer> RUN_SET_VAR = ByteRunner::runSetVar;
    public static final BiFunction<FrogParser, Integer, Integer> RUN_IF = ByteRunner::runIf;
    public static final BiFunction<FrogParser, Integer, Integer> RUN_PIXEL_SET = ByteRunner::runPixelSet;
    public static final BiFunction<FrogParser, Integer, Integer> RUN_WHILE_LOOP = ByteRunner::runWhileLoop;

    public static int runPrint(FrogParser frogParser, int index) {
        Object byteValue = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index));
        frogParser.owner.displayClientMessage(Component.literal(String.valueOf(byteValue)), false);
        return index + 1;
    }

    public static int runSetVar(FrogParser frogParser, int index) {
        // Which byte to set
        Byte value1 = frogParser.BYTECODE_INSTRUCTIONS.get(index++);
        // Gets what to set the previous byte
        Object value2 = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index++));

        frogParser.BYTE_TO_VALUE.put(value1, value2);

        return index;
    }

    public static int runIf(FrogParser frogParser, int index) {
        Object valueFromByte = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index));

        if (valueFromByte instanceof Boolean bool && bool)
        {
            // Run the IF statement and set the new index to after the statement
            return ifStatement(frogParser, index);
        }

        return skipIfStatement(frogParser, index);
    }

    public static int runWhileLoop(FrogParser frogParser, int index) {
        final AtomicInteger atomicIndex = new AtomicInteger(index);
        frogParser.scheduler.scheduleAtFixedRate(() -> {
            if (frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index)) instanceof Boolean bool && bool) {
                atomicIndex.set(ifStatement(frogParser, index));
            }
            else {
                atomicIndex.set(skipIfStatement(frogParser, index));
                frogParser.scheduler.shutdown();
            }
        }, 0, 33, TimeUnit.MILLISECONDS);

        return atomicIndex.get();
    }

    public static int runPixelSet(FrogParser frogParser, int index) {
        Object valueFromByte = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index));
        index++;
        Object colorFromByte = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index));

        if (valueFromByte instanceof Object[] positions && colorFromByte instanceof Object[] colors) {
            int r = (int) tryParseValue(String.valueOf(colors[0]));
            int g = (int) tryParseValue(String.valueOf(colors[1]));
            int b = (int) tryParseValue(String.valueOf(colors[2]));

            for (int i = 0; i < positions.length / 3; i++) {
                int x = (int) tryParseValue(String.valueOf(positions[3 * i]));
                int y = (int) tryParseValue(String.valueOf(positions[3 * i + 1]));
                int z = (int) tryParseValue(String.valueOf(positions[3 * i + 2]));

                if (frogParser.level.getBlockEntity(new BlockPos(x, y, z)) instanceof PixelBlockEntity pixel && pixel.isOwner(frogParser.owner)) {
                    pixel.setColor(r, g, b);
                }
            }

            return index;
        }

        throw new IllegalArgumentException();
    }
}
