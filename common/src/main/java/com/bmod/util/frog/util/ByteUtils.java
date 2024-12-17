package com.bmod.util.frog.util;

import com.bmod.util.frog.FrogParser;
import java.util.function.BiFunction;

import static com.bmod.util.frog.util.ObjectUtils.findValue;

public class ByteUtils {

    // Runs an IF statement
    public static int ifStatement(FrogParser frogParser, int index)
    {
        // This is here if there is multiple IF statements in the current statement
        int nests = 0;
        Byte currentByte = 0;

        // While the index of the instruction set is not 'end' and it is not currently in another IF statement
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
            // Run the instruction otherwise
            else {
                frogParser.runInstruction(currentByte, index);
            }
            index++;
        }

        return index;
    }

    // Skips an IF statement
    public static int skipIfStatement(FrogParser frogParser, int index)
    {
        // This is here if there is multiple IF statements in the current statement
        int nests = 0;
        Byte currentByte = 0;

        // While the index of the instruction set is not 'end' and it is not currently in another IF statement
        while (currentByte != 0x06 && nests == 0) {
            currentByte = frogParser.BYTECODE_INSTRUCTIONS.get(index);

            // If an IF statement is found, make it nested
            if (currentByte == 0x02 || currentByte == 0x0E) {
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

    public static Byte getOrCreateByte(FrogParser frogParser, String[] words, int index, boolean addInstruction) {
        // Gets the word in the keywords
        Byte resultByte = frogParser.KEY_TO_BYTE.get(words[index]);
        // If the variable doesn't exist, make it exist
        if (resultByte == null) {
            return makeKeyword(frogParser, "Object$" + frogParser.BYTE_KEY_LENGTH + 1, findValue(frogParser, words, index, addInstruction).first());
        }
        if (addInstruction) {
            frogParser.BYTECODE_INSTRUCTIONS.add(resultByte);
        }
        return resultByte;
    }

    public static void queueByte(FrogParser frogParser, BiFunction<FrogParser, Integer, Integer> function) {
        int lastIf = frogParser.BYTECODE_INSTRUCTIONS.lastIndexOf((byte) 0x02);
        int lastWhile = frogParser.BYTECODE_INSTRUCTIONS.lastIndexOf((byte) 0x0E);
        int lastPrint = frogParser.BYTECODE_INSTRUCTIONS.lastIndexOf((byte) 0x03);
        int lastSet = frogParser.BYTECODE_INSTRUCTIONS.lastIndexOf((byte) 0x05) - 1;
        lastSet = frogParser.BYTECODE_INSTRUCTIONS.get(lastSet - 1) == 0x04 ? lastSet - 1 : lastSet;

        int index = Math.max(Math.max(Math.max(lastWhile, lastIf), lastPrint), lastSet);

        frogParser.BYTECODE_INSTRUCTIONS.add(index, makeKeyword(frogParser, "Queue$" + frogParser.BYTE_KEY_LENGTH + 1, function));
    }

    public static byte addNewKeyword(FrogParser frogParser, String word, Object o) {
        byte newByte = makeKeyword(frogParser, word, o);
        frogParser.BYTECODE_INSTRUCTIONS.add(newByte);
        return newByte;
    }

    // Safer, shorter version of simply just adding the keyword, variable and bytes
    public static byte makeKeyword(FrogParser frogParser, String word, Object object)
    {
        // Increase the keyword amount
        frogParser.BYTE_KEY_LENGTH++;

        // Since bytes have 256 possible values, there can't be over 256 variables
        if (frogParser.BYTE_KEY_LENGTH > 256) {
            throw new IndexOutOfBoundsException("Too many variables!");
        }

        // Remove the possibility of a variable overlap
        if (frogParser.KEY_TO_BYTE.get(word) != null) {
            throw new IndexOutOfBoundsException("Variable keyword [" + word + "] is already initialized! Cannot make variables of the same name.");
        }

        frogParser.BYTE_TO_VALUE.put((byte) frogParser.BYTE_KEY_LENGTH, object);
        frogParser.KEY_TO_BYTE.put(word, (byte) frogParser.BYTE_KEY_LENGTH);

        return (byte) frogParser.BYTE_KEY_LENGTH;
    }
}