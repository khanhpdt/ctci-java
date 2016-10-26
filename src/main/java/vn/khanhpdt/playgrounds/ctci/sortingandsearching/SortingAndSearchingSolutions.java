package vn.khanhpdt.playgrounds.ctci.sortingandsearching;

import java.util.*;

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

	/**
	 * Problem 11.2
	 */
	static List<String> clusterAnagrams(List<String> strings) {
		Map<Integer, List<String>> stringBySumCharacters = new HashMap<>();
		strings.forEach(s -> {
			int sum = calculateSumCharacters(s);
			stringBySumCharacters.putIfAbsent(sum, new ArrayList<>());
			stringBySumCharacters.get(sum).add(s);
		});

		List<String> result = new ArrayList<>();
		for (List<String> stringList : stringBySumCharacters.values()) {
			result.addAll(groupAnagrams(stringList));
		}

		return result;
	}

	private static List<String> groupAnagrams(List<String> strings) {
		final int nStrings = strings.size();

		List<String> result = new ArrayList<>();
		result.add(strings.get(0));
		strings.set(0, null);

		while (result.size() < nStrings) {
			String current = result.get(result.size() - 1);

			// move all anagrams
			for (int i = 0; i < strings.size(); i++) {
				if (strings.get(i) != null && isAnagram(current, strings.get(i))) {
					result.add(strings.get(i));
					strings.set(i, null);
				}
			}

			// continue with another string
			for (int i = 0; i < strings.size(); i++) {
				if (strings.get(i) != null) {
					result.add(strings.get(i));
					strings.set(i, null);
				}
			}
		}

		return result;
	}

	private static boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		Set<Character> chars = new HashSet<>();
		for (char c : s1.toCharArray()) {
			chars.add(c);
		}

		for (char c : s2.toCharArray()) {
			if (!chars.contains(c)) {
				return false;
			}
		}

		return true;
	}

	private static int calculateSumCharacters(String s) {
		int result = 0;
		for (char c : s.toCharArray()) {
			result += c;
		}
		return result;
	}

	/**
	 * Problem 11.3
	 *
	 * @param numbers a rotated of a sorted array
	 */
	static int findInRotatedArray(int[] numbers, int n) {
		int indexSmallest = -1;
		int i = 0;
		while (indexSmallest == -1 && i < numbers.length - 1) {
			// micro optimization: exploit the search for the smallest number to find the given number
			if (numbers[i] == n) {
				return i;
			}

			if (numbers[i] > numbers[i + 1]) {
				indexSmallest = i + 1;
			}

			i++;
		}

		// because indexSmallest == -1 here means no rotation, meaning that the given number must have been found
		// in the loop if it is in the array.
		if (indexSmallest == -1) {
			throw new IllegalArgumentException(n + " not found.");
		}

		if (n > numbers[0]) {
			return binarySearch(n, numbers, 0, indexSmallest - 1);
		} else {
			return binarySearch(n, numbers, indexSmallest, numbers.length - 1);
		}
	}

	private static int binarySearch(int n, int[] numbers, int from, int to) {
		while (from <= to) {
			int middle = (from + to) / 2;
			if (n == numbers[middle]) {
				return middle;
			}
			if (n > numbers[middle]) {
				from = middle + 1;
			} else {
				to = middle - 1;
			}
		}

		throw new IllegalArgumentException(n + " not found.");
	}

	/**
	 * Problem 11.5
	 *
	 * @param strings a sorted string array interspersed with empty strings
	 */
	static int findInInterspersedArray(String[] strings, String s) {
		int from = 0;
		int to = strings.length - 1;
		while (from <= to) {
			final int middle = (from + to) / 2;

			// move up until a non-empty string found
			int nonEmptyStringMiddle = middle;
			while ("".equals(strings[nonEmptyStringMiddle]) && nonEmptyStringMiddle <= to) {
				nonEmptyStringMiddle++;
			}

			// no non-empty string found upward
			if (nonEmptyStringMiddle > to) {
				// move down until a non-empty string found
				nonEmptyStringMiddle = middle - 1;
				while ("".equals(strings[nonEmptyStringMiddle]) && nonEmptyStringMiddle >= from) {
					nonEmptyStringMiddle--;
				}
			}

			// no non-empty string found downward
			if (nonEmptyStringMiddle < from) {
				throw new IllegalArgumentException("All strings empty.");
			}

			// binary search
			if (s.equals(strings[nonEmptyStringMiddle])) {
				return nonEmptyStringMiddle;
			}
			if (s.compareTo(strings[nonEmptyStringMiddle]) > 0) {
				from = nonEmptyStringMiddle + 1;
			} else {
				to = nonEmptyStringMiddle - 1;
			}
		}

		throw new IllegalArgumentException(s + " not found.");
	}

	static int[] findInSortedMatrix(int[][] matrix, int n) {
		return findInSortedMatrix(n, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
	}

	private static int[] findInSortedMatrix(int n, int[][] matrix, int rowFrom, int rowTo, int colFrom, int colTo) {
		if (rowFrom > rowTo || colFrom > colTo) {
			// not found
			return null;
		}

		int middleRow = (rowFrom + rowTo) / 2;
		int middleCol = (colFrom + colTo) / 2;

		if (n == matrix[middleRow][middleCol]) {
			return new int[]{middleRow, middleCol};
		}

		int[] indexes;
		if (n > matrix[middleRow][middleCol]) {
			// right
			indexes = findInSortedMatrix(n, matrix, middleRow, middleRow, middleCol + 1, colTo);
			if (indexes == null) {
				// lower right
				indexes = findInSortedMatrix(n, matrix, middleRow + 1, rowTo, middleCol, colTo);
			}
		} else {
			// left
			indexes = findInSortedMatrix(n, matrix, middleRow, middleRow, colFrom, middleCol - 1);
			if (indexes == null) {
				// upper left
				indexes = findInSortedMatrix(n, matrix, rowFrom, middleRow - 1, colFrom, middleCol);
			}
		}
		// search in these areas in both cases (less and greater than), because we cannot know know their relationships
		// with matrix[middleRow][middleCol]
		if (indexes == null) {
			// upper right
			indexes = findInSortedMatrix(n, matrix, rowFrom, middleRow - 1, middleCol + 1, colTo);
			if (indexes == null) {
				// lower left
				indexes = findInSortedMatrix(n, matrix, middleRow + 1, rowTo, colFrom, middleCol - 1);
			}
		}

		return indexes;
	}
}
