package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

/**
 * @author khanhpdt
 */
class BitManipulationSolutions {

    /**
     * Problem 5.1: Inject M into the space between ith and jth bits (inclusive) in N
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
}
