package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import java.math.BigDecimal;

/**
 * @author khanhpdt
 */
class BitManipulationSolutions {

    /**
     * Problem 5.1: Injects M into the space between ith and jth bits (inclusive) in N
     */
    static int injectBits(int N, int M, int i, int j) {
        // note: (1 << (i + 1) - 1) produces a sequence of bits where the lower-order i bits are 1,
        // and the higher-order bits are 0

        // clear all bits from j to 0
        int initN = N & (~ ((1 << (j + 1)) - 1));
        // clear all bits from MSB to i
        int tailN = N & ((1 << (i + 1)) - 1);
        // shift M to the left i bits
        int shiftedM = M << i;
        return initN | shiftedM | tailN;
    }

    /**
     * Problem 5.2: Prints the binary representation of the given double number.
     * @see <a href="http://cs.furman.edu/digitaldomain/more/ch6/dec_frac_to_bin.htm">dec_frac_to_bin</a>
     */
    static String fractionInBinary(double d) {
        if (d == 0.0) {
            return "0";
        }

        final int lengthLimit = 32;

        StringBuilder sb = new StringBuilder("0.");
        while (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(d)) != 0 && sb.length() <= lengthLimit) {
            double doubleD = d * 2;

            long integerPart = (long) Math.floor(doubleD);
            sb.append(integerPart);

            d = doubleD - integerPart;
        }

        if (sb.length() > lengthLimit) {
            return "ERROR";
        }

        return sb.toString();
    }
}
