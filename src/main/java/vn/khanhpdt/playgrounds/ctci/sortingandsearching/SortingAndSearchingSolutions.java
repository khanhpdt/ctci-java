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
}
