package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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

	@Test
	public void testGroupAnagrams() {
		List<String> s = Arrays.asList("1357", "2464", "123", "7531", "873193", "231", "6424");

		List<String> result = SortingAndSearchingSolutions.clusterAnagrams(s);

		assertTrue(nextToEachOther(result, "1357", "7531"));
		assertTrue(nextToEachOther(result, "2464", "6424"));
		assertTrue(nextToEachOther(result, "123", "231"));
	}

	private boolean nextToEachOther(List<String> strings, String s1, String s2) {
		return Math.abs(strings.indexOf(s1) - strings.indexOf(s2)) == 1;
	}

	@Test
	public void testFindInRotatedArray() {
		int[] ints = new int[]{15, 16, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		int index = SortingAndSearchingSolutions.findInRotatedArray(ints, 5);
		assertThat(index, is(7));
	}

}
