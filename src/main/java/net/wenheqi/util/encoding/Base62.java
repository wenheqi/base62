package net.wenheqi.util.encoding;

import java.util.Arrays;

public class Base62 {

    private Base62() {}

    public static Encoder getEnCoder() { return Encoder.DFT_ENCODER; }

    public static Decoder getDecoder() {
        return Decoder.DFT_DECODER;
    }

    public static class Encoder {

        /**
         * This array is the default lookup table that translates 6-bit positive
         * integer index values into their "Base62 Alphabet". The order and
         * values of the characters in this array comes from "Table 1: The
         * Base64 Alphabet" of RFC 2045 (and RFC 4648), with '+' and '/' removed.
         */
        private static final char[] DFT_ALPHABET = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        private final char[] toBase62;
        private final int padToWidth;

        private Encoder(char[] alphabet, int padToWidth) {
            if (alphabet == null) throw new NullPointerException("Alphabet is null");
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            this.toBase62 = Arrays.copyOf(alphabet, alphabet.length);
            this.padToWidth = padToWidth;
        }

        static final Encoder DFT_ENCODER = new Encoder(DFT_ALPHABET, 0);

        public Encoder withAlphabet(char[] alphabet) {
            if (alphabet == null) throw new NullPointerException("Alphabet is null");
            return new Encoder(alphabet, 0);
        }

        public Encoder withPadToWidth(int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");
            return new Encoder(this.toBase62, padToWidth);
        }

        public String encode(int number) {
            return encode((long) number);
        }

        public String encode(long number) {
            StringBuilder sb = new StringBuilder();

            if (number == 0) sb.append(toBase62[0]);

            while (number != 0) {
                sb.append(toBase62[(byte)(number % 62)]);
                number /= 62;
            }

            while (sb.length() < padToWidth) sb.append(toBase62[0]);
            return sb.reverse().toString();
        }

    }

    public static class Decoder {

        private static final int[] DFT_ALPHABET = new int[256];

        static {
            Arrays.fill(DFT_ALPHABET, -1);
            for (int i = 0; i < Encoder.DFT_ALPHABET.length; i++) {
                DFT_ALPHABET[Encoder.DFT_ALPHABET[i]] = i;
            }
        }

        private final int[] fromBase62;
        private final int padToWidth;

        private Decoder() {
            this.fromBase62 = DFT_ALPHABET;
            padToWidth = 0;
        }

        private Decoder(int[] fromBase62, int padToWidth) {
            if (fromBase62 == null) throw new NullPointerException("Alphabet is null");
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            this.fromBase62 = Arrays.copyOf(fromBase62, fromBase62.length);
            this.padToWidth = padToWidth;
        }

        private Decoder(char[] alphabet, int padToWidth) {
            if (alphabet == null) throw new NullPointerException("Alphabet is null");
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            fromBase62 = new int[256];
            Arrays.fill(fromBase62, -1);

            for (int i = 0; i < alphabet.length; i++) {
                fromBase62[alphabet[i]] = i;
            }

            this.padToWidth = padToWidth;
        }


        static final Decoder DFT_DECODER = new Decoder();

        public Decoder withAlphabet(char[] alphabet) {
            return new Decoder(alphabet, this.padToWidth);
        }

        public Decoder withPadToWidth(int padToWidth) {
            return new Decoder(this.fromBase62, padToWidth);
        }

        public String decode(String text) {
            long number = 0, add;
            for (int i = 0, len = text.length(); i < len; i++) {
                add = fromBase62[text.charAt(i)];
                if (add < 0) throw new IllegalArgumentException("Illegal character: " + text.charAt(i));
                number = number * 62 + add;
            }
            return String.valueOf(number);
        }
    }

}
