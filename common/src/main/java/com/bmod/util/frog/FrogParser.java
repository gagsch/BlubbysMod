package com.bmod.util.frog;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.BiFunction;

import static com.bmod.util.frog.instruction.ByteBuilder.*;
import static com.bmod.util.frog.instruction.ByteRunner.*;
import static com.bmod.util.frog.util.ByteUtils.*;

public class FrogParser {
    public final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public final ServerLevel level; // The level of the Frog Executor Block
    public final ServerPlayer owner; // The Frog Executor Blocks owner

    public final HashMap<Byte, Object> BYTE_TO_VALUE = new HashMap<>(256); // Bytes are made into usable variables, "false" -> false
    public final HashMap<String, Byte> KEY_TO_BYTE = new HashMap<>(256); // Keywords are made into bytes, "print" -> 0x02
    public final List<Byte> BYTECODE_INSTRUCTIONS = new ArrayList<>(); // Your program is stored here

    public int BYTE_KEY_LENGTH = -1; // Starts at -1 because it adds when used

    public FrogParser(ServerLevel level, ServerPlayer owner) {
        this.level = level;
        this.owner = owner;
    }

    // This method is for making the built-in keywords.
    public void makeStartBytes()
    {
        makeKeyword(this, "false", false); // 0x00
        makeKeyword(this, "true", true); // 0x01
        makeKeyword(this, "if", RUN_IF); // 0x02
        makeKeyword(this, "print", RUN_PRINT); // 0x03
        makeKeyword(this, "var", "var"); // 0x04
        makeKeyword(this, "=", RUN_SET_VAR); // 0x05
        makeKeyword(this, "end", "end"); // 0x06
        makeKeyword(this, "!!",  "not"); // 0x07
        makeKeyword(this, "null", null); // 0x08
        makeKeyword(this, "is", "is"); // 0x09
        makeKeyword(this, "+", "add"); // 0x0A
        makeKeyword(this, "set_pixels", RUN_PIXEL_SET); // 0x0B
        makeKeyword(this, "<", "less"); // 0x0C
        makeKeyword(this, ">", "more"); // 0x0D
        makeKeyword(this, "while", RUN_WHILE_LOOP); // 0x0E
    }

    // For translating code into bytes
    public void makeInstructions(String code)
    {
        if (code == null || code.isEmpty()) {
            return;
        }

        // Takes the code and splits it into word chunks
        String[] words = removeComments(code).split(" ");

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
                case 0x02, 0x0E: {
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

    public static String removeComments(String inputText) {
        // Split the input text into lines based on newline characters
        String[] lines = inputText.split("\n");

        // StringBuilder to hold the cleaned lines
        StringBuilder cleanedText = new StringBuilder();

        for (String line : lines) {
            // Check if the line contains a comment
            int commentIndex = line.indexOf("//");
            if (commentIndex != -1) {
                // Only take the part before the comment
                line = line.substring(0, commentIndex).trim();
            }
            // Add the cleaned line to the final text
            cleanedText.append(line).append(" ");
        }

        // Return the cleaned text as a single string
        return cleanedText.toString().trim();
    }
}
