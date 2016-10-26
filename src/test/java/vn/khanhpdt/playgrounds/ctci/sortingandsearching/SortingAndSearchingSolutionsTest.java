package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * @author khanhpdt
 */
public class SortingAndSearchingSolutionsTest {

	@Test
	public void testMergeSortedArrays() {
		int[] a = new int[20];
		for (int i = 0; i < 13; i++) {
			a[i] = i;
		}

		int[] b = new int[]{3, 4, 7, 21, 25, 34, 100};

		SortingAndSearchingSolutions.mergeSortedArrays(a, b);
		assertTrue(Arrays.equals(a, new int[]{0, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 21, 25, 34, 100}));
	}

}
