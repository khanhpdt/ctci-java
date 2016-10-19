package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import org.junit.Test;

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
}