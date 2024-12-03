package com.bmod.util.frog.util;

import com.bmod.util.frog.FrogParser;

import java.util.Map;
import java.util.function.BiFunction;

import static com.bmod.util.frog.util.ByteUtils.addNewKeyword;

public class ObjectUtils {
    private static final Map<String, BiFunction<Number, Number, Double>> OPERATIONS = Map.of(
            "-", (a, b) -> a.doubleValue() - b.doubleValue(),
            "*", (a, b) -> a.doubleValue() * b.doubleValue(),
            "/", (a, b) -> a.doubleValue() / b.doubleValue(),
            "%", (a, b) -> a.doubleValue() % b.doubleValue()
    );

    public static Object add(Object obj1, Object obj2) {
        if (obj1 instanceof Number && obj2 instanceof Number) {
            double num = ((Number) obj1).doubleValue() + ((Number) obj2).doubleValue();

            if (num % 1 == 0)
                return (int) num;

            return num;
        } else if (obj1 instanceof String || obj2 instanceof String) {
            // Concatenate as strings
            return String.valueOf(obj1) + obj2;
        } else {
            return null;
        }
    }

    public static Object math(Object obj1, Object obj2, String type) {
        if (obj1 instanceof Number && obj2 instanceof Number) {
            double num = OPERATIONS.get(type).apply((Number) obj1, (Number) obj2);

            if (num % 1 == 0)
                return (int) num;

            return num;
        }
        else {
            return null;
        }
    }

    public static boolean lessThan(Object obj1, Object obj2, boolean flipped) {
        if (obj1 instanceof Number && obj2 instanceof Number) {
            if (flipped) {
                return ((Number) obj1).doubleValue() > ((Number) obj2).doubleValue();
            }

            return ((Number) obj1).doubleValue() < ((Number) obj2).doubleValue();
        }
        else {
            return false;
        }
    }

    public static Pair<?, Integer> findValue(FrogParser frogParser, String[] words, int i, boolean addInstruction)
    {
        String word = words[i];
        Byte keyByte = frogParser.KEY_TO_BYTE.get(word);
        Object value;

        if (keyByte == null) {
            if (word.startsWith("\"")) {
                return string(frogParser, words, word, i, addInstruction);
            }
            else if (word.startsWith("[")) {
                return list(frogParser, words, word, i, addInstruction);
            }
            else {
                value = tryParseValue(word);

                if (addInstruction)
                    addNewKeyword(frogParser, word, value);
            }
        }
        else {
            value = frogParser.BYTE_TO_VALUE.get(keyByte);

            if (addInstruction)
                frogParser.BYTECODE_INSTRUCTIONS.add(keyByte);
        }

        return new Pair<>(value, i + 1);
    }

    public static Object tryParseValue(String word) {
        try {
            // Try to parse an integer
            return Integer.parseInt(word);
        } catch (NumberFormatException e1) {
            try {
                // If it fails, try to parse as a double
                double d = Double.parseDouble(word);

                if (d % 1 == 0)
                    return (int) d;

                return d;
            } catch (NumberFormatException e2) {
                // If both parsing attempts fail, return the original value
                return word;
            }
        }
    }

    // Reads a string that starts with "
    public static Pair<String, Integer> string(FrogParser frogParser, String[] words, String word, int i, boolean addInstruction) {
        StringBuilder stringBuilder = new StringBuilder();

        // Add the current word and a space to the builder
        word = word.substring(1);
        stringBuilder.append(word).append(" ");

        // While the next word doesn't have " at the end
        while (!word.endsWith("\"")) {
            // Add the next word to the StringBuilder
            i++;
            word = words[i];
            stringBuilder.append(word).append(" ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        // Turn the word into the string found
        word = stringBuilder.toString().replace("\"", "");

        // Make the new string a keyword
        if (addInstruction) {
            addNewKeyword(frogParser, "String$" + frogParser.BYTE_KEY_LENGTH + 1, word);
        }

        return new Pair<>(word, i + 1);
    }

    // Reads a list that starts with (
    public static Pair<Object[], Integer> list(FrogParser frogParser, String[] words, String word, int i, boolean addInstruction) {
        StringBuilder stringBuilder = new StringBuilder();

        // Add the current word and a space to the builder
        stringBuilder.append(word).append(" ");

        // While the next word doesn't have " at the end
        while (!words[i].endsWith("]")) {
            // Add the next word to the StringBuilder
            i++;
            stringBuilder.append(words[i]).append(" ");
        }

        Object[] values = parseStringArray(stringBuilder.toString().replace("[", "").replace("]", "").replace(",", "").split(" "));

        // Make the new string a keyword
        if (addInstruction) {
            addNewKeyword(frogParser, "List$" + frogParser.BYTE_KEY_LENGTH + 1, values);
        }

        return new Pair<>(values, i + 1);
    }

    public static Object[] parseStringArray(String[] input) {
        Object[] result = new Object[input.length];

        for (int i = 0; i < input.length; i++) {
            String str = input[i];

            // Try parsing as a Boolean
            if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
                result[i] = Boolean.valueOf(str);
            }
            // Try parsing as a Double
            else if (str.matches("-?\\d+(\\.\\d+)?")) {
                result[i] = Double.valueOf(str);
            }
            // Try parsing as an Integer
            else if (str.matches("-?\\d+")) {
                result[i] = Integer.valueOf(str);
            }
            // Otherwise, treat it as a String
            else {
                result[i] = str;
            }
        }

        return result;
    }
}
