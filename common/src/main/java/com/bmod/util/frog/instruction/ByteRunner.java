package com.bmod.util.frog.instruction;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.util.frog.FrogParser;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.function.BiFunction;

import static com.bmod.util.frog.util.ByteUtils.ifStatement;
import static com.bmod.util.frog.util.ObjectUtils.tryParseValue;

public class ByteRunner {
    // Gives functionality to keywords instead of leaving them as variables
    public static final BiFunction<FrogParser, Integer, Integer> print = ByteRunner::runPrint;
    public static final BiFunction<FrogParser, Integer, Integer> setVar = ByteRunner::runSetVar;
    public static final BiFunction<FrogParser, Integer, Integer> ifRunner = ByteRunner::runIf;
    public static final BiFunction<FrogParser, Integer, Integer> setPixels = ByteRunner::runPixelSet;

    public static int runPrint(FrogParser frogParser, int index) {
        Object byteValue = frogParser.BYTE_TO_VALUE.get(frogParser.BYTECODE_INSTRUCTIONS.get(index));

        frogParser.level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(String.valueOf(byteValue)), false);
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

        int nests = 0;
        Byte currentByte = 0x00;
        // Find next END if the IF statement was false
        while (currentByte != 0x06 && nests == 0) {
            currentByte = frogParser.BYTECODE_INSTRUCTIONS.get(index);
            // If an IF statement is found, make it nested
            if (currentByte == 0x02) {
                nests++;
            }
            // If an 'end' is found, un-nest the current statement
            else if (currentByte == 0x06) {
                nests--;
            }
            index++;
        }

        return index;
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

                if (frogParser.level.getBlockEntity(new BlockPos(x, y, z)) instanceof PixelBlockEntity pixel) {
                    pixel.setColor(r, g, b);
                }
            }

            return index;
        }

        throw new IllegalArgumentException();
    }
}
