package com.bmod.util.frog.instruction;

import com.bmod.util.frog.FrogParser;
import com.bmod.util.frog.util.ObjectUtils;
import com.bmod.util.frog.util.Pair;

import java.util.Objects;

import static com.bmod.util.frog.util.ByteUtils.*;
import static com.bmod.util.frog.util.ObjectUtils.findValue;

public class ByteBuilder {
    public static int buildPrint(FrogParser frogParser, String[] words, int index)
    {
        Pair<?, Integer> variableType = findValue(frogParser, words, index + 1, true);

        return variableType.second() - 1;
    }

    public static int buildVar(FrogParser frogParser, String[] words, int index)
    {
        index++;
        // Loads the next word
        String variable = words[index];

        // Makes a keyword and adds it into the instruction set
        addNewKeyword(frogParser, variable, null);

        // Increment 'i' so it skips the next instruction (which would be the variable just set)
        return index;
    }

    public static int buildSetTo(FrogParser frogParser, String[] words, int index)
    {
        Byte prevInstruction = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 2);

        if (prevInstruction == null || prevInstruction.intValue() < 14) // Since there are 14 builtin keywords
        {
            throw new Error("Cannot set non-variable to a value.");
        }

        // Loads the previous variable into the instruction set, this is done because the '=' keyword is a BiConsumer.
        // BiConsumers use the next 2 variables as its arguments
        frogParser.BYTECODE_INSTRUCTIONS.add(prevInstruction);

        if (frogParser.KEY_TO_BYTE.get(words[index + 1]) != null)
            return index;

        Pair<?, Integer> variableType = findValue(frogParser, words, index + 1, true);

        return variableType.second() - 1;
    }

    public static int buildIs(FrogParser frogParser, String[] words, int index) {
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Byte prevByte = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);
        Byte nextByte = getOrCreateByte(frogParser, words, index + 1, false);

        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Object prevByteValue = frogParser.BYTE_TO_VALUE.get(prevByte);
        Object nextByteValue = frogParser.BYTE_TO_VALUE.get(nextByte);
        boolean bool = Objects.equals(prevByteValue, nextByteValue);
        Byte newByte = addNewKeyword(frogParser, "Is$" + frogParser.BYTE_KEY_LENGTH + 1, bool);
        if (prevByteValue == null || nextByteValue == null)
        {
            queueByte(frogParser, (k, integer) -> {
                k.BYTE_TO_VALUE.put(newByte, Objects.equals(k.BYTE_TO_VALUE.get(prevByte), k.BYTE_TO_VALUE.get(nextByte)));
                return integer;
            }, false);
        }

        return index + String.valueOf(frogParser.BYTE_TO_VALUE.get(nextByte)).split(" ").length;
    }

    public static int buildAddVar(FrogParser frogParser, String[] words, int index) {
        // Remove the "+" byte
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Byte prevByte = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);
        Object prevByteValue = frogParser.BYTE_TO_VALUE.get(prevByte);

        // Remove the now accessed byte
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        // See if the next byte is saved
        index++;
        Byte nextByte = getOrCreateByte(frogParser, words, index, false);
        Object nextByteValue = frogParser.BYTE_TO_VALUE.get(nextByte);

        // Create a new byte for this addition
        Byte newByte = addNewKeyword(frogParser, "Sum$" + frogParser.BYTE_KEY_LENGTH + 1, ObjectUtils.add(prevByteValue, nextByteValue));

        if (prevByteValue == null || nextByteValue == null) {
            frogParser.BYTE_TO_VALUE.put(newByte, null);
            queueByte(frogParser, (k, integer) -> {
                k.BYTE_TO_VALUE.put(newByte, ObjectUtils.add(k.BYTE_TO_VALUE.get(prevByte), k.BYTE_TO_VALUE.get(nextByte)));
                return integer;
            }, false);
        }

        String nextByteString = String.valueOf(nextByteValue);
        int extra = nextByteString.endsWith(" ") ? 0 : 1;

        return index + nextByteString.split(" ").length - extra;
    }

    public static int buildMath(FrogParser frogParser, String[] words, int index, String type) {
        Byte prevByte = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);
        Object prevByteValue = frogParser.BYTE_TO_VALUE.get(prevByte);

        // Remove the now accessed byte
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        // See if the next byte is saved
        index++;
        Byte nextByte = getOrCreateByte(frogParser, words, index, false);
        Object nextByteValue = frogParser.BYTE_TO_VALUE.get(nextByte);

        // Create a new byte for this addition
        Byte newByte = addNewKeyword(frogParser, "Math$" + frogParser.BYTE_KEY_LENGTH + 1, ObjectUtils.math(prevByteValue, nextByteValue, type));

        if (prevByteValue == null || nextByteValue == null)
        {
            frogParser.BYTE_TO_VALUE.put(newByte, null);
            queueByte(frogParser, (k, integer) -> {
                k.BYTE_TO_VALUE.put(newByte, ObjectUtils.math(k.BYTE_TO_VALUE.get(prevByte), k.BYTE_TO_VALUE.get(nextByte), type));
                return integer;
            }, false);
        }

        return index;
    }

    public static void buildNot(FrogParser frogParser) {
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Byte lastByte = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);
        queueByte(frogParser, (k, integer) -> {
            k.BYTE_TO_VALUE.put(lastByte, !(boolean) k.BYTE_TO_VALUE.get(lastByte));
            return integer;
        }, false);

    }

    public static int buildSetPixels(FrogParser frogParser, String[] words, int index) {
        Pair<?, Integer> positions = findValue(frogParser, words, index + 1, true);
        Pair<?, Integer> color = findValue(frogParser, words, positions.second(), true);

        return color.second() - 1;
    }


    public static int buildComparison(FrogParser frogParser, String[] words, int index, boolean moreThan) {
        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Byte prevByte = frogParser.BYTECODE_INSTRUCTIONS.get(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);
        Byte nextByte = getOrCreateByte(frogParser, words, index + 1, false);

        frogParser.BYTECODE_INSTRUCTIONS.remove(frogParser.BYTECODE_INSTRUCTIONS.size() - 1);

        Object prevByteValue = frogParser.BYTE_TO_VALUE.get(prevByte);
        Object nextByteValue = frogParser.BYTE_TO_VALUE.get(nextByte);

        boolean comparison = ObjectUtils.lessThan(prevByteValue, nextByteValue, moreThan);

        Byte newByte = addNewKeyword(frogParser, "Com$" + frogParser.BYTE_KEY_LENGTH + 1, comparison);

        if (prevByteValue == null || nextByteValue == null)
        {
            queueByte(frogParser, (k, integer) -> {
                k.BYTE_TO_VALUE.put(newByte, ObjectUtils.lessThan(k.BYTE_TO_VALUE.get(prevByte), k.BYTE_TO_VALUE.get(nextByte), moreThan));
                return integer;
            }, false);
        }

        return index + 1;
    }
}
