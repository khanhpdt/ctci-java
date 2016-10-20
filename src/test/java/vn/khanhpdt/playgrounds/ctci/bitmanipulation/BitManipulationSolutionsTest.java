package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author khanhpdt
 */
public class BitManipulationSolutionsTest {

    @Test
    public void testInjectBits() {
        assertThat(BitManipulationSolutions.injectBits(0b11_10110_1100, 0b11111, 4, 8), is(0b11_11111_1100));
        assertThat(BitManipulationSolutions.injectBits(0b100_00000_00, 0b10011, 2, 6), is(0b100_10011_00));
        assertThat(BitManipulationSolutions.injectBits(0b10000, 0b11111, 0, 4), is(0b11111));
    }

    @Test
    public void testFractionInBinary() throws Exception {
        assertThat(BitManipulationSolutions.fractionInBinary(0.0625), is("0.0001"));
        assertThat(BitManipulationSolutions.fractionInBinary(0.625), is("0.101"));
        assertThat(BitManipulationSolutions.fractionInBinary(0.72), is("ERROR"));
    }

    @Test
    public void testGetNextLargerNumberSameOneBitCount() {
        assertThat(BitManipulationSolutions.getNextLargerNumberSameOneBitCount(0b101), is(0b110));
        assertThat(BitManipulationSolutions.getNextLargerNumberSameOneBitCount(0b1110), is(0b10011));
    }

    @Test
    public void testGetNextLargerNumberSameOneBitCount2() {
        assertThat(BitManipulationSolutions.getNextLargerNumberSameOneBitCount_2(0b101), is(0b110));
        assertThat(BitManipulationSolutions.getNextLargerNumberSameOneBitCount_2(0b1110), is(0b10011));
    }

    @Test
    public void testGetNextSmallerNumberSameOneBitCount() {
        assertThat(BitManipulationSolutions.getNextSmallerNumberSameOneBitCount(0b110), is(0b101));
        assertThat(BitManipulationSolutions.getNextSmallerNumberSameOneBitCount(0b111100001), is(0b111011000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNextSmallerNumberSameOneBitCount_notExist() {
        BitManipulationSolutions.getNextSmallerNumberSameOneBitCount(0b111111);
    }

    @Test
    public void testProblem54() throws Exception {
        assertThat(BitManipulationSolutions.problem54(0b111011_1), is(0b111011_0));
        assertThat(BitManipulationSolutions.problem54(0b11101_100), is(0b11101_000));
        assertThat(BitManipulationSolutions.problem54(0b1_1000000), is(0b1_0000000));
        assertThat(BitManipulationSolutions.problem54(0b1_000000), is(0));
    }

    @Test
    public void testNumberOfBitsToConvert() throws Exception {
        assertThat(BitManipulationSolutions.countNumberOfBitsToConvert(0b11111, 0b1110), is(2));
        assertThat(BitManipulationSolutions.countNumberOfBitsToConvert(0b11_010_111, 0b11_101_111), is(3));
        assertThat(BitManipulationSolutions.countNumberOfBitsToConvert(0b101010101_11, 0b11), is(5));
    }

    @Test
    public void testSwapOddAndEvenBits() throws Exception {
        assertThat(BitManipulationSolutions.swapOddAndEvenBits(0b11_10_01_00), is(0b11_01_10_00));
        assertThat(BitManipulationSolutions.swapOddAndEvenBits(0b1_10_01_10), is(0b10_01_10_01));
    }

    @Test
    public void testSwapOddAndEvenBits2() throws Exception {
        assertThat(BitManipulationSolutions.swapOddAndEvenBits_2(0b11_10_01_00), is(0b11_01_10_00));
        assertThat(BitManipulationSolutions.swapOddAndEvenBits_2(0b1_10_01_10), is(0b10_01_10_01));
    }

    @Test
    public void testFindMissingNumber() throws Exception {
        assertThat(BitManipulationSolutions.findMissingNumber(20, IntStream.range(0, 21).filter(i -> i != 12).toArray()), is(12));
        assertThat(BitManipulationSolutions.findMissingNumber(100, IntStream.range(0, 101).filter(i -> i != 81).toArray()), is(81));
    }

    @Test
    public void testDrawHorizontalLine_widthEight() {
        // 8x10 screen
        int width = 8;
        byte[] screen = new byte[20];
        Arrays.fill(screen, (byte)0);

        BitManipulationSolutions.drawHorizontalLine(screen, width, 0, 7, 2);

        assertThat(screen[2], is((byte)0b11111111));
    }

    @Test
    public void testDrawHorizontalLine_widthMultipleEight() {
        // 24x10 screen
        int width = 24;
        byte[] screen = new byte[30];
        Arrays.fill(screen, (byte)0);

        BitManipulationSolutions.drawHorizontalLine(screen, width, 2, 20, 3);

        assertThat(screen[9], is((byte)0b00111111));
        assertThat(screen[10], is((byte)0b11111111));
        assertThat(screen[11], is((byte)0b11111000));
    }
}