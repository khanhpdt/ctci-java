package vn.khanhpdt.playgrounds.ctci.bitmanipulation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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

}