package com.bmod.util.frog;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

import static com.bmod.util.frog.instruction.ByteBuilder.*;
import static com.bmod.util.frog.instruction.ByteRunner.*;
import static com.bmod.util.frog.util.ByteUtils.*;

public class FrogParser {
    public final ServerLevel level; // Starts at -1 because it adds when used
    public final HashMap<Byte, Object> BYTE_TO_VALUE = new HashMap<>(256); // Bytes are made into usable variables, "false" -> false
    public final HashMap<String, Byte> KEY_TO_BYTE = new HashMap<>(256); // Keywords are made into bytes, "print" -> 0x02
    public final List<Byte> BYTECODE_INSTRUCTIONS = new ArrayList<>(); // Your program is stored here
    public int BYTE_KEY_LENGTH = -1; // Starts at -1 because it adds when used

    public FrogParser(ServerLevel level) {
        this.level = level;
    }

    // This method is for making the built-in keywords.
    public void makeStartBytes()
    {
        makeKeyword(this, "false", false); // 0x00
        makeKeyword(this, "true", true); // 0x01
        makeKeyword(this, "if", ifRunner); // 0x02
        makeKeyword(this, "print", print); // 0x03
        makeKeyword(this, "var", "var"); // 0x04
        makeKeyword(this, "=", setVar); // 0x05
        makeKeyword(this, "end", "end"); // 0x06
        makeKeyword(this, "!!",  "not"); // 0x07
        makeKeyword(this, "null", null); // 0x08
        makeKeyword(this, "is", "is"); // 0x09
        makeKeyword(this, "+", "add"); // 0x0A
        makeKeyword(this, "set_pixels", setPixels); // 0x0B
        makeKeyword(this, "<", "less"); // 0x0C
        makeKeyword(this, ">", "more"); // 0x0D
    }

    // For translating code into bytes
    public void makeInstructions(String code)
    {
        if (code == null || code.isEmpty()) {
            return;
        }

        // Takes the code and splits it into word chunks
        String[] words = code.split(" ");

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];

            if (currentWord.matches("\\s+") || currentWord.isEmpty()) {
                continue;
            }
            // Removes all whitespaces (Spaces, Line Breaks, Tabs)
            currentWord = currentWord.strip();
            if (currentWord.matches("[-*/%]")) {
                i = buildMath(this, words, i, currentWord);
                continue;
            }

            // Gets the byte data of the current word
            Byte instruction = KEY_TO_BYTE.get(currentWord);

            // Checks if the word exists as a variable and crashes if it's not
            if (instruction == null) {
                System.out.println(BYTECODE_INSTRUCTIONS);
                throw new Error("Keyword [" + currentWord + "] does not exist! At Index: " + i + "\n");
            }

            // Add the current byte to the byte-code set
            BYTECODE_INSTRUCTIONS.add(instruction);

            switch (instruction) {
                case 0x02: {
                    if (KEY_TO_BYTE.get(words[i + 1]) == null) {
                        getOrCreateByte(this, words, i + 1, true);
                        i++;
                    }
                    break;
                }
                case 0x03: {
                    i = buildPrint(this, words, i);
                    break;
                }
                case 0x04: {
                    i = buildVar(this, words, i);
                    break;
                }
                case 0x05: {
                    i = buildSetTo(this, words, i);
                    break;
                }
                case 0x07: {
                    buildNot(this);
                    break;
                }
                case 0x09: {
                    i = buildIs(this, words, i);
                    break;
                }
                case 0x0A: {
                    i = buildAddVar(this, words, i);
                    break;
                }
                case 0x0B: {
                    i = buildSetPixels(this, words, i);
                    break;
                }
                case 0x0C: {
                    i = buildComparison(this, words, i, false);
                    break;
                }
                case 0x0D: {
                    i = buildComparison(this, words, i, true);
                    break;
                }
            }
        }
    }

    // Actual running of the BYTECODE_INSTRUCTIONS
    public void runInstructions()
    {
        // Goes through all instructions and runs them
        int i = 0;
        while (i < BYTECODE_INSTRUCTIONS.size()) {
            i = runInstruction(BYTECODE_INSTRUCTIONS.get(i), i);
        }
    }

    @SuppressWarnings("unchecked")
    public int runInstruction(Byte instruction, int index)
    {
        Object val = BYTE_TO_VALUE.get(instruction);
        return (val instanceof BiFunction<?,?,?>) ? ((BiFunction<FrogParser, Integer, Integer>) val).apply(this, index + 1) : index + 1;
    }
}
