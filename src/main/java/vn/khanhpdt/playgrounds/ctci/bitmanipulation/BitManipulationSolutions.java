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
        // note: ((1 << i) - 1) produces a sequence of bits where the i lower-order bits are 1,
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

    // this solution mainly uses array manipulation
    static int getNextLargerNumberSameOneBitCount_2(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 0 whose right bit is 1
        int indexOfZeroWhoseRightIsOne = -1;
        int nZeros = 0;
        boolean oneFound = false;
        // go from right to left
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] == '0' && oneFound) {
                indexOfZeroWhoseRightIsOne = i;
                break;
            }
            if (bits[i] == '1' && !oneFound) {
                oneFound = true;
            }
            if (bits[i] == '0') nZeros++;
        }

        char[] result;
        int flippedZeroIndex;
        if (indexOfZeroWhoseRightIsOne == -1) {
            // no 0 bit whose right bit is 1 is found. we add such 0 bit to the head of the array.
            result = new char[bits.length + 1];
            flippedZeroIndex = 0;
        } else {
            result = new char[bits.length];
            System.arraycopy(bits, 0, result, 0, indexOfZeroWhoseRightIsOne);
            flippedZeroIndex = indexOfZeroWhoseRightIsOne;
        }

        // this flip gives us a larger number than the given number
        result[flippedZeroIndex] = '1';
        result[flippedZeroIndex + 1] = '0';

        // makes the number smallest possible by moving the zeroes to before the ones
        for (int i = flippedZeroIndex + 2, nZero = 0; i < result.length; i++) {
            if (nZero < nZeros) {
                result[i] = '0';
                nZero++;
            } else {
                result[i] = '1';
            }
        }

        return Integer.parseInt("+" + String.valueOf(result), 2);
    }

    // this solution uses bit manipulation
    static int getNextLargerNumberSameOneBitCount(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 0 whose right bit is 1
        int indexOfZeroWhoseRightIsOne = -1;
        for (int i = 0; i < bits.length - 1; i++) {
            if (bits[i] == '0' && bits[i + 1] == '1') {
                indexOfZeroWhoseRightIsOne = i;
                break;
            }
        }

        // count the number of ones and zeros before the zero whose right is 1
        int nOnes = 0, nZeros = 0;
        for (int i = indexOfZeroWhoseRightIsOne + 1; i < bits.length; i++) {
            if (bits[i] == '0') {
                nZeros++;
            }
            else {
                nOnes++;
            }
        }

        int result = n;
        // flip the bit 0 to 1 to get a number larger than the given number.
        result |= 1 << ((bits.length - 1) - indexOfZeroWhoseRightIsOne);

        // we want to make it as small as possible while keeping the same numbers of zeros and ones.
        // set the trailing bits to 1. since we already added 1, there are (nOnes - 1) ones left
        result |= (1 << (nOnes - 1)) - 1;
        // set the in-between bits to 0
        result &= ~ (((1 << (nZeros + 1)) - 1) << (nOnes - 1));

        return result;
    }

    // this solution uses bit manipulation
    static int getNextSmallerNumberSameOneBitCount(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();

        // find the first bit 1 whose right bit is 0
        int indexOfOneWhoseRightIsZero = -1;
        for (int i = 0; i < bits.length - 1; i++) {
            if (bits[i] == '1' && bits[i + 1] == '0') {
                indexOfOneWhoseRightIsZero = i;
                break;
            }
        }

        if (indexOfOneWhoseRightIsZero == -1) {
            throw new IllegalArgumentException("Smaller number than " + n + " with same one bit count does not exist!");
        }

        // count the number of ones and zeros before the 1 whose right is 0
        int nOnes = 0, nZeros = 0;
        for (int i = indexOfOneWhoseRightIsZero + 1; i < bits.length; i++) {
            if (bits[i] == '0') {
                nZeros++;
            }
            else {
                nOnes++;
            }
        }

        int result = n;
        // flip the bit 1 to 0 to get a number smaller than the given number
        result &= ~(1 << ((bits.length - 1) - indexOfOneWhoseRightIsZero));

        // we want to make it as large as possible while keeping the same numbers of zeros and ones.
        // set the trailing bits to 0. since we already added 0, there are (nZeros - 1) zeros left
        result &= ~((1 << (nZeros - 1)) - 1);
        // set the in-between bits to 1
        result |= ((1 << (nOnes + 1)) - 1) << (nZeros - 1);

        return result;
    }
}
