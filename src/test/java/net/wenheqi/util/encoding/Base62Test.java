package net.wenheqi.util.encoding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62Test {

    private static final Base62.Encoder b62e = Base62.getEncoder();
    private static final Base62.Decoder b62d = Base62.getDecoder();

    @Test
    @DisplayName("Encoder: integer")
    void encodeIntegers() {
        int[] numbers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                -2_147_483_648, // Integer.MIN_VALUE = 0x80000000
                -1,
                65315,
                2_147_483_647, // Integer.MAX_VALUE = 0x7fffffff
        };

        String[] answers = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "CVUmlC",
                "EqpPMD",
                "Q9d",
                "CVUmlB",
        };

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], new String(b62e.encode(numbers[i])));
        }
    }

    @Test
    @DisplayName("Encoder: long")
    void encodeLongs() {
        long[] numbers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
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
                "K9VIxAiFIwH",
        };

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], new String(b62e.encode(numbers[i])));
        }
    }

    @Test
    @DisplayName("Encoder: string")
    void encodeStrings() {
        String[] data = {
                "\uD83E\uDD70", // 🥰 U+1F970
                "\uD83D\uDE18", // 😘 U+1F618
        };
        String[] answers = {
                "EZMyqM",
                "EZMxyI",
        };

        for (int i = 0; i < data.length; i++) {
            assertEquals(answers[i], new String(b62e.encode(data[i])));
        }
    }

    @Test
    @DisplayName("Decoder: string")
    void decodeStrings() {
        String[] data = {
                "",
                "EZMyqM",
                "EZMxyI",
                "K9VIxAiFIwH", // Long.MAX_VALUE = 0x7fffffffffffffff
        };

        String[] answers = {
            "",
            "\uD83E\uDD70", // 🥰 U+1F970
            "\uD83D\uDE18", // 😘 U+1F618
            new String(new byte[]{127,-1,-1,-1,-1,-1,-1,-1}),
        };

        for (int i = 0; i < data.length; i++) {
            assertEquals(answers[i], new String(b62d.decode(data[i])));
        }
    }

    @Test
    @DisplayName("Decoder: integer")
    void decodeIntegers() {
        String[] data = {
                "CVUmlC",
                "EqpPMD",
                "Q9d",
                "CVUmlB",
        };
        int[] answers = {
                -2_147_483_648, // Integer.MIN_VALUE = 0x80000000
                -1,
                65315,
                2_147_483_647, // Integer.MAX_VALUE = 0x7fffffff
        };

        for (int i = 0; i < data.length; i++) {
            byte[] bytes = b62d.decode(data[i]);

            int x = 0;
            for (int j = 0; j < bytes.length; j++) {
                x = (x << 8) | (((int) bytes[j]) & 0xff);
            }
            assertEquals(answers[i], x);
        }
    }

    @Test
    @DisplayName("Decoder: long")
    void decodeLongs() {
        String[] data = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "K9VIxAiFIwH", // Long.MAX_VALUE = 0x7fffffffffffffff
        };
        long[] answers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                9_223_372_036_854_775_807L, // Long.MAX_VALUE = 0x7fffffffffffffff
        };

        for (int i = 0; i < data.length; i++) {
            byte[] bytes = b62d.decode(data[i]);
            long x = 0;
            for (int j = 0; j < bytes.length; j++) {
                x = (x << 8) | (((int) bytes[j]) & 0xff);
            }
            assertEquals(answers[i], x);
        }
    }

    @Test
    @DisplayName("Encoder: with custom alphabet")
    void encodeWithAlphabet() {
        long[] numbers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                9_223_372_036_854_775_807L, // Long.MAX_VALUE = 0x7fffffffffffffff
        };

        String[] answers = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "k9viXaIfiWh",
        };

        char[] alphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        Base62.Encoder cusEncoder = Base62.getEncoder().withAlphabet(alphabet);

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], new String(cusEncoder.encode(numbers[i])));
        }
    }

    @Test
    @DisplayName("Encoder: with custom padToWidth")
    void encodeWithPadToWidth() {
        int[] numbers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                -2_147_483_648, // Integer.MIN_VALUE = 0x80000000
                -1,
                65315,
                2_147_483_647, // Integer.MAX_VALUE = 0x7fffffff
        };

        String[] answers = {
                "AAAAA", "AAAAB", "AAAAC", "AAAAD", "AAAAE", "AAAAF", "AAAAG", "AAAAH", "AAAAI", "AAAAJ",
                "AAAAK", "AAAAL", "AAAAM", "AAAAN", "AAAAO", "AAAAP", "AAAAQ", "AAAAR", "AAAAS", "AAAAT",
                "AAAAU", "AAAAV", "AAAAW", "AAAAX", "AAAAY", "AAAAZ",
                "AAAAa", "AAAAb", "AAAAc", "AAAAd", "AAAAe", "AAAAf", "AAAAg", "AAAAh", "AAAAi", "AAAAj",
                "AAAAk", "AAAAl", "AAAAm", "AAAAn", "AAAAo", "AAAAp", "AAAAq", "AAAAr", "AAAAs", "AAAAt",
                "AAAAu", "AAAAv", "AAAAw", "AAAAx", "AAAAy", "AAAAz",
                "AAAA0", "AAAA1", "AAAA2", "AAAA3", "AAAA4", "AAAA5", "AAAA6", "AAAA7", "AAAA8", "AAAA9",
                "CVUmlC",
                "EqpPMD",
                "AAQ9d",
                "CVUmlB",
        };

        Base62.Encoder cusEncoder = Base62.getEncoder().withPadToWidth(5);

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], new String(cusEncoder.encode(numbers[i])));
        }
    }

    @Test
    @DisplayName("Decoder: with custom alphabet")
    void decodeWithAlphabet() {
        String[] data = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "k9viXaIfiWh",
        };

        long[] answers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                9_223_372_036_854_775_807L, // Long.MAX_VALUE = 0x7fffffffffffffff
        };

        char[] alphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        Base62.Decoder cusDecoder = Base62.getDecoder().withAlphabet(alphabet);

        for (int i = 0; i < data.length; i++) {
            byte[] bytes = cusDecoder.decode(data[i]);
            long x = 0;
            for (int j = 0; j < bytes.length; j++) {
                x = (x << 8) | (((int) bytes[j]) & 0xff);
            }
            assertEquals(answers[i], x);
        }
    }

    @Test
    @DisplayName("Decoder: with custom padToWidth")
    void decodeWithPadToWidth() {
        String[] data = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "CVUmlC",
                "EqpPMD",
                "Q9d",
                "CVUmlB",
        };

        int[] answers = {
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10,11,12,13,14,15,16,17,18,19,
                20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,
                40,41,42,43,44,45,46,47,48,49,
                50,51,52,53,54,55,56,57,58,59,
                60,61,
                -2_147_483_648, // Integer.MIN_VALUE = 0x80000000
                -1,
                65315,
                2_147_483_647, // Integer.MAX_VALUE = 0x7fffffff
        };

        final int width = 8;

        Base62.Decoder cusDecoder = Base62.getDecoder().withPadToWidth(width);

        for (int i = 0; i < data.length; i++) {
            byte[] bytes = cusDecoder.decode(data[i]);
            assertEquals(width, bytes.length);
            int x = 0;
            for (int j = 0; j < bytes.length; j++) {
                x = (x << 8) | (((int) bytes[j]) & 0xff);
            }
            assertEquals(answers[i], x);
        }
    }
}
