package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

/**
 * @author khanhpdt
 */
class SortingAndSearchingSolutions {

	/**
	 * Problem 11.1
	 * @param a sorted array, has enough empty spaces at the end to store b
	 * @param b sorted array
	 */
	static void mergeSortedArrays(int[] a, int[] b) {
		// shift all elements in a to the right
		int nElementsA = a.length - b.length;
		System.arraycopy(a, 0, a, b.length, nElementsA);

		// merge in-place.
		int i = b.length; // since a's elements were shifted to the right.
		int j = 0;
		for (int k = 0; k < a.length; k++) {
			// out of a's items
			if (i == a.length) {
				a[k] = b[j];
				j++;
			}
			// out of b's items
			else if (j == b.length) {
				a[k] = a[i];
				i++;
			}
			else {
				// smaller first
				if (a[i] < b[j]) {
					a[k] = a[i];
					i++;
				} else {
					a[k] = b[j];
					j++;
				}
			}
		}
	}

}
