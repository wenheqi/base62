package net.wenheqi.util.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * <p>
 * This class consists of static methods for obtaining encoders and decoders for the Base62
 * encoding scheme.
 * </p>
 *
 * <div>
 *        <table style="text-align:center;" border="1"><tbody>
 *          <a name="alphabet"></a>
 *          <caption>Base62 Table</caption>
 *          <tr><th scope="col">Index</th><th scope="col">Binary</th><th scope="col">Char</th>
 *              <td rowspan="17" style="border: none;"></td>
 *              <th scope="col">Index</th><th scope="col">Binary</th><th scope="col">Char</th>
 *              <td rowspan="17" style="border: none;"></td>
 *              <th scope="col">Index</th><th scope="col">Binary</th><th scope="col">Char</th>
 *              <td rowspan="17" style="border: none;"></td>
 *              <th scope="col">Index</th><th scope="col">Binary</th><th scope="col">Char</th></tr>
 *          <tr><td>0</td><td>000000</td><td><code>A</code></td>
 *              <td>16</td><td>010000</td><td><code>Q</code></td>
 *              <td>32</td><td>100000</td><td><code>g</code></td>
 *              <td>48</td><td>110000</td><td><code>w</code></td></tr>
 *          <tr><td>1</td><td>000001</td><td><code>B</code></td>
 *              <td>17</td><td>010001</td><td><code>R</code></td>
 *              <td>33</td><td>100001</td><td><code>h</code></td>
 *              <td>49</td><td>110001</td><td><code>x</code></td></tr>
 *          <tr><td>2</td><td>000010</td><td><code>C</code></td>
 *              <td>18</td><td>010010</td><td><code>S</code></td>
 *              <td>34</td><td>100010</td><td><code>i</code></td>
 *              <td>50</td><td>110010</td><td><code>y</code></td></tr>
 *          <tr><td>3</td><td>000011</td><td><code>D</code></td>
 *              <td>19</td><td>010011</td><td><code>T</code></td>
 *              <td>35</td><td>100011</td><td><code>j</code></td>
 *              <td>51</td><td>110011</td><td><code>z</code></td></tr>
 *          <tr><td>4</td><td>000100</td><td><code>E</code></td>
 *              <td>20</td><td>010100</td><td><code>U</code></td>
 *              <td>36</td><td>100100</td><td><code>k</code></td>
 *              <td>52</td><td>110100</td><td><code>0</code></td></tr>
 *          <tr><td>5</td><td>000101</td><td><code>F</code></td>
 *              <td>21</td><td>010101</td><td><code>V</code></td>
 *              <td>37</td><td>100101</td><td><code>l</code></td>
 *              <td>53</td><td>110101</td><td><code>1</code></td></tr>
 *          <tr><td>6</td><td>000110</td><td><code>G</code></td>
 *              <td>22</td><td>010110</td><td><code>W</code></td>
 *              <td>38</td><td>100110</td><td><code>m</code></td>
 *              <td>54</td><td>110110</td><td><code>2</code></td></tr>
 *          <tr><td>7</td><td>000111</td><td><code>H</code></td>
 *              <td>23</td><td>010111</td><td><code>X</code></td>
 *              <td>39</td><td>100111</td><td><code>n</code></td>
 *              <td>55</td><td>110111</td><td><code>3</code></td></tr>
 *          <tr><td>8</td><td>001000</td><td><code>I</code></td>
 *              <td>24</td><td>011000</td><td><code>Y</code></td>
 *              <td>40</td><td>101000</td><td><code>o</code></td>
 *              <td>56</td><td>111000</td><td><code>4</code></td></tr>
 *          <tr><td>9</td><td>001001</td><td><code>J</code></td>
 *              <td>25</td><td>011001</td><td><code>Z</code></td>
 *              <td>41</td><td>101001</td><td><code>p</code></td>
 *              <td>57</td><td>111001</td><td><code>5</code></td></tr>
 *          <tr><td>10</td><td>001010</td><td><code>K</code></td>
 *              <td>26</td><td>011010</td><td><code>a</code></td>
 *              <td>42</td><td>101010</td><td><code>q</code></td>
 *              <td>58</td><td>111010</td><td><code>6</code></td></tr>
 *          <tr><td>11</td><td>001011</td><td><code>L</code></td>
 *              <td>27</td><td>011011</td><td><code>b</code></td>
 *              <td>43</td><td>101011</td><td><code>r</code></td>
 *              <td>59</td><td>111011</td><td><code>7</code></td></tr>
 *          <tr><td>12</td><td>001100</td><td><code>M</code></td>
 *              <td>28</td><td>011100</td><td><code>c</code></td>
 *              <td>44</td><td>101100</td><td><code>s</code></td>
 *              <td>60</td><td>111100</td><td><code>8</code></td></tr>
 *          <tr><td>13</td><td>001101</td><td><code>N</code></td>
 *              <td>29</td><td>011101</td><td><code>d</code></td>
 *              <td>45</td><td>101101</td><td><code>t</code></td>
 *              <td>61</td><td>111101</td><td><code>9</code></td></tr>
 *          <tr><td>14</td><td>001110</td><td><code>O</code></td>
 *              <td>30</td><td>011110</td><td><code>e</code></td>
 *              <td>46</td><td>101110</td><td><code>u</code></td>
 *              <td></td><td></td><td><code></code></td></tr>
 *          <tr><td>15</td><td>001111</td><td><code>P</code></td>
 *              <td>31</td><td>011111</td><td><code>f</code></td>
 *              <td>47</td><td>101111</td><td><code>v</code></td>
 *              <td></td><td></td><td><code> </code></td></tr>
 *         </tbody></table>
 *</div>
 *
 * <p>
 * Unless otherwise noted, passing a {@code null} argument to a method of this class will
 * cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 * </p>
 *
 * @author Wenhe Qi
 */
public class Base62 {

    private Base62() {}

    /**
     * Returns a {@link Encoder} that encodes using the alphabet in
     * <a href="#alphabet">Base62 Table</a>.
     *
     * @return A Base62 encoder.
     */
    public static Encoder getEncoder() {
        return Encoder.DFT_ENCODER;
    }

    /**
     * Returns a {@link Decoder} that decodes using the alphabet in
     * <a href="#alphabet">Base62 Table</a>.
     *
     * @return A Base62 decoder.
     */
    public static Decoder getDecoder() {
        return Decoder.DFT_DECODER;
    }

    /**
     * This class implements an encoder for encoding data using the
     * Base62 encoding scheme.
     *
     * Instances of {@link Encoder} class are save for use by multiple
     * concurrent threads.
     *
     * Unless otherwise noted, passing a {@code null} argument to a
     * method of this class will cause a
     * {@link java.lang.NullPointerException NullPointerException} to
     * be thrown.
     *
     * @see Decoder
     */
    public static class Encoder {

        private final char[] toBase62;
        private final int padToWidth;

        private static final byte BASE62 = 62;
        private static final char[] DFT_ALPHABET = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        static final Encoder DFT_ENCODER = new Encoder(DFT_ALPHABET, 0);

        private Encoder(char[] alphabet, int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            this.toBase62 = Arrays.copyOf(alphabet, alphabet.length);
            this.padToWidth = padToWidth;
        }

        private byte[] toBytes(int number) {
            byte[] res = new byte[Integer.BYTES];
            for (int i = res.length - 1; i >= 0; i--) {
                res[i] = (byte) (number & 0xff);
                number >>>= 8;
            }
            return res;
        }

        private byte[] toBytes(long number) {
            byte[] res = new byte[Long.BYTES];
            for (int i = res.length - 1; i >= 0; i--) {
                res[i] = (byte) (number & 0xff);
                number >>>= 8;
            }
            return res;
        }

        /**
         * Encodes all bytes from the specified byte array into a newly-allocated
         * byte array using the {@link Base62} encoding scheme.
         *
         * @param   data
         *          the byte array to encode
         * @return  A newly-allocated byte array containing the resulting
         *          encoded bytes.
         */
        public byte[] encode(byte[] data) {
            if (data.length == 0) {
                return new byte[0];
            }

            int len = data.length;
            byte[] rev = new byte[len]; // reversed b62 array
            int rLen = 0;
            int dividend = 0;
            data = Arrays.copyOf(data, len);

            while (len > 0) {
                int nLen = 0;
                for (int i = 0; i < len; i++) {
                    // convert data[i] to unsigned int and then compute
                    dividend = (dividend << 8) | ((int) data[i]) & 0xff;
                    if (dividend >= BASE62) {
                        data[nLen++] = (byte) (dividend / BASE62);
                        dividend %= BASE62;
                    }
                    else if (nLen > 0) {
                        data[nLen++] = 0;
                    }
                }

                if (rLen == rev.length) {
                    rev = Arrays.copyOf(rev, rev.length + (rev.length >> 1));
                }
                rev[rLen++] = (byte) dividend;
                dividend = 0;
                len = nLen;
            }

            byte[] res = new byte[Math.max(rLen, padToWidth)];
            for (int i = res.length-1, j = 0; j < rLen; i--) {
                res[i] = (byte) toBase62[rev[j++]];
            }
            for (int i = res.length - rLen - 1; i >=0; i--) {
                res[i] = (byte) toBase62[0];
            }
            return res;
        }

        /**
         * Converts the specified integer to a byte array and encodes all bytes
         * from the specified number into a newly-allocated byte array using
         * the {@link Base62} encoding scheme.
         *
         * @param   number
         *          the number to encode
         * @return  A newly-allocated byte array containing the resulting
         *          encoded bytes.
         */
        public byte[] encode(int number) {
            return encode(toBytes(number));
        }

        /**
         * Converts the specified long to a byte array and encodes all bytes
         * from the specified number into a newly-allocated byte array using
         * the {@link Base62} encoding scheme.
         *
         * @param   number
         *          the number to encode
         * @return  A newly-allocated byte array containing the resulting
         *          encoded bytes.
         */
        public byte[] encode(long number) {
            return encode(toBytes(number));
        }

        /**
         * Encodes this string into a sequence of bytes using
         * {@link java.nio.charset.StandardCharsets StandardCharsets.UTF_8}
         * and Encodes this byte array into a newly-allocated byte array using
         * the {@link Base62} encoding scheme.
         *
         * @param   str
         *          the string to encode
         * @return  A newly-allocated byte array containing the resulting
         *          encoded bytes.
         */
        public byte[] encode(String str) {
            return encode(str.getBytes(StandardCharsets.UTF_8));
        }

        /**
         * Encodes this string into a sequence of bytes using the given
         * {@link java.nio.charset.Charset charset} and Encodes this
         * byte array into a newly-allocated byte array using the
         * {@link Base62} encoding scheme.
         *
         * @param   str
         *          the string to encode
         * @param   charset
         *          The Charset to be used to encode the String
         * @return  A newly-allocated byte array containing the resulting
         *          encoded bytes.
         */
        public byte[] encode(String str, Charset charset) {
            return encode(str.getBytes(charset));
        }

        /**
         * Returns an encoder instance that encodes equivalently to this one,
         * but with a specified encoding alphabet.
         *
         * The padToWidth is unaffected by this invocation.
         *
         * @param   alphabet
         *          The alphabet to be used to encode the data
         *
         * @return an equivalent encoder that encodes with specified alphabet
         */
        public Encoder withAlphabet(char[] alphabet) {
            return new Encoder(alphabet, 0);
        }

        /**
         * Returns an encoder instance that encodes equivalently to this one,
         * but with a specified padToWidth value.
         *
         * The default encoder has padToWidth set to 0, i.e. no padding will
         * be added to the encoded result.
         *
         * If specified and the result byte array has a smaller length than
         * padToWidth, the result will be padded from the beginning of the
         * array with the character corresponding to index 0 to meet the
         * width requirement.
         *
         * The alphabet is unaffected by this invocation.
         *
         * @param   padToWidth
         *          The min length of result byte array after encoding
         *
         * @return an equivalent encoder that encodes with specified padToWidth
         *
         * @throws IllegalArgumentException if {@code padToWidth < 0}
         */
        public Encoder withPadToWidth(int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");
            return new Encoder(this.toBase62, padToWidth);
        }
    }

    /**
     * This class implements a decoder for decoding byte data using the Base62 encoding
     * scheme as specified in {@link Encoder}.
     *
     * Instances of {@link Decoder} class are safe for use by
     * multiple concurrent threads.
     *
     * <p> Unless otherwise noted, passing a {@code null} argument to a method of this
     * class will cause a {@link java.lang.NullPointerException NullPointerException}
     * to be thrown.
     *
     * @see Encoder
     */
    public static class Decoder {

        private static final int BASE256 = 256;

        private final int[] fromBase62;
        private final int padToWidth;

        static final Decoder DFT_DECODER = new Decoder(Encoder.DFT_ALPHABET, 0);

        private Decoder(int[] fromBase62, int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            this.fromBase62 = Arrays.copyOf(fromBase62, fromBase62.length);
            this.padToWidth = padToWidth;
        }

        private Decoder(char[] alphabet, int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");

            fromBase62 = new int[256];
            Arrays.fill(fromBase62, -1);

            for (int i = 0; i < alphabet.length; i++) {
                fromBase62[alphabet[i]] = i;
            }

            this.padToWidth = padToWidth;
        }

        /**
         * Decodes all bytes from the input byte array using the {@link Base62}
         * encoding scheme, writing the results into a newly-allocated output
         * byte array.
         *
         * @param   src
         *          the byte array to decode
         *
         * @return  A newly-allocated byte array containing the decoded bytes.
         *
         * @throws  IllegalArgumentException
         *          if {@code src} is not in valid Base64 scheme
         */
        public byte[] decode(byte[] src) {
            int len = src.length;
            byte[] rev = new byte[len]; // reversed b256 array
            int rLen = 0;
            int dividend = 0;
            src = Arrays.copyOf(src, len);

            while (len > 0) {
                int nLen = 0;
                for (int i = 0; i < len; i++) {
                    // convert data[i] to unsigned int and then compute
                    dividend = (dividend * 62) + (((int) src[i]) & 0xff);
                    if (dividend >= BASE256) {
                        src[nLen++] = (byte) (dividend / BASE256);
                        dividend %= BASE256;
                    }
                    else if (nLen > 0) {
                        src[nLen++] = 0;
                    }
                }

                if (rLen == rev.length) {
                    rev = Arrays.copyOf(rev, rev.length + (rev.length >> 1));
                }
                rev[rLen++] = (byte) dividend;
                dividend = 0;
                len = nLen;
            }

            byte[] res = new byte[Math.max(rLen, padToWidth)];
            for (int i = res.length-1, j = 0; j < rLen; i--) {
                res[i] = (byte) rev[j++];
            }
            for (int i = res.length - rLen - 1; i >=0; i--) {
                res[i] = 0;
            }
            return res;
        }

        /**
         * Converts each character in the str to a byte array of the corresponding
         * index in alphabet, and then decode all bytes using the {@link Base62}
         * encoding scheme, writing the results into a newly-allocated output
         * byte array.
         *
         * @param   str
         *          the string to decode
         *
         * @return  A newly-allocated byte array containing the decoded bytes.
         *
         * @throws  IllegalArgumentException
         *          if {@code str} is not in valid Base64 scheme
         */
        public byte[] decode(String str) {
            if (str.length() == 0) return new byte[0];

            byte[] data = new byte[str.length()];

            for (int i = 0, idx; i < data.length; i++) {
                idx = str.charAt(i);
                if (fromBase62[idx] == -1) {
                    throw new IllegalArgumentException("Unkown character: " + (char) idx);
                }
                data[i] = (byte) fromBase62[idx];
            }

            return decode(data);
        }

        /**
         * Returns an decoder instance that decodes equivalently to this one,
         * but with a specified decoding alphabet.
         *
         * The padToWidth is unaffected by this invocation.
         *
         * @param   alphabet
         *          The alphabet to be used to decode the data
         *
         * @return an equivalent decoder that decodes with specified alphabet
         */
        public Decoder withAlphabet(char[] alphabet) {
            return new Decoder(alphabet, this.padToWidth);
        }

        /**
         * Returns an decoder instance that decodes equivalently to this one,
         * but with a specified padToWidth value.
         *
         * The default decoder has padToWidth set to 0, i.e. no padding will
         * be added to the decoded result.
         *
         * If specified and the result byte array has a smaller length than
         * padToWidth, the result will be padded from the beginning of the
         * array with bytes of 0 value to meet the width requirement.
         *
         * The alphabet is unaffected by this invocation.
         *
         * @param   padToWidth
         *          The min length of result byte array after decoding
         *
         * @return an equivalent decoder that decodes with specified padToWidth
         *
         * @throws IllegalArgumentException if {@code padToWidth < 0}
         */
        public Decoder withPadToWidth(int padToWidth) {
            if (padToWidth < 0) throw new IllegalArgumentException("padToWidth is negative");
            return new Decoder(this.fromBase62, padToWidth);
        }
    }
}
