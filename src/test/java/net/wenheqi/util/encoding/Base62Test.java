package net.wenheqi.util.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62Test {

    @Test
    void encodeBasic() {
        long[] numbers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                2_147_483_647, // Integer.MAX_VALUE = 0x7fffffff
                9_223_372_036_854_775_807L, // Long.MAX_VALUE = 0x7fffffffffffffff

        };

        String[] answers = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "CVUmlB",
                "K9VIxAiFIwH",
        };

        Base62.Encoder encoder = Base62.getEnCoder();

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], encoder.encode(numbers[i]));
        }
    }
}
