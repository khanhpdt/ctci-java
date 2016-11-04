package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author khanhpdt
 */
class ArraysAndStringsSolutions {

	/**
	 * <p>Problem 1.1</p>
	 * <p>Solution 1: a straightforward solution</p>
	 * <p>Complexity: O(n^2), where n = length of the string</p>
	 */
	static boolean hasUniqueCharacters_1(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			char c = s.charAt(i);
			for (int j = i + 1; j < s.length(); j++) {
				// duplicated character found
				if (c == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * <p>Problem 1.1.</p>
	 * <p>Solution 2: use a hash table</p>
	 * <p>Complexity: O(n), where n = length of the string</p>
	 * <p>Extra O(n) memory</p>
	 */
	static boolean hasUniqueCharacters_2(String s) {
		// O(n) memory needed
		Set<Character> characters = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			boolean firstTimeInserted = characters.add(s.charAt(i));
			if (!firstTimeInserted) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Problem 1.1.</p>
	 * <p>Solution 3: use a bit vector</p>
	 * <p>Complexity: O(n), where n = length of the given string</p>
	 */
	static boolean hasUniqueCharacters_3(String s) {
		Pattern pattern = Pattern.compile("^[a-z]+$");
		Matcher matcher = pattern.matcher(s);
		if (!matcher.find()) {
			throw new IllegalArgumentException("This algorithm only supports string with characters in range [a-z].");
		}

		// this limits this algorithm to apply only on [a-z] characters, because an integer has only 32 bits
		int bitVector = 0;
		for (int i = 0; i < s.length(); i++) {
			int offset = s.charAt(i) - 'a';
			// the bit at the position offset is already on -> the character was found before
			if (((bitVector << offset) & (1 << offset)) == 1) {
				return false;
			}
			else {
				// turn on the bit at the position offset to mark the character
				bitVector |= (1 << offset);
			}
		}
		return true;
	}

	/**
	 * Problem 1.2.
	 *
	 * <p>Time: O(n), where n = length of the string</p>
	 * <p>Memory: O(n)</p>
	 */
	static char[] reverseString(char[] chars) {
		char[] reversed = new char[chars.length];
		int j = chars.length - 1;
		for (char ch : chars) {
			reversed[j] = ch;
			j--;
		}
		return reversed;
	}

	/**
	 * Problem 1.2.
	 *
	 * <p>In-place solution</p>
	 * <p>Time: O(n), where n = length of the string</p>
	 * <p>Memory: O(1)</p>
	 */
	static char[] reverseString_2(char[] chars) {
		int i = 0;
		int j = chars.length - 1;
		while (i < j) {
			char c = chars[i];
			chars[i] = chars[j];
			chars[j] = c;

			i++;
			j--;
		}
		return chars;
	}

	/**
	 * Problem 1.3.
	 *
	 * <p>Complexity: O(n), where n = length of the strings</p>
	 */
	static boolean checkPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		Map<Character, Integer> characterCounts = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			characterCounts.putIfAbsent(c, 0);
			characterCounts.put(c, characterCounts.get(c) + 1);
		}

		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			// s1 does not have a character in s2
			if (characterCounts.get(c) == null) {
				return false;
			}
			// s1 has fewer of this character than s2
			else if (characterCounts.get(c) == 0) {
				return false;
			}
			else {
				characterCounts.put(c, characterCounts.get(c) - 1);
			}
			// We don't need to check for the case when s1 has more of a character than s2, because this is equivalent
			// to the case when s1 has fewer of another character than s2 as they have the same length.
		}

		return true;
	}

	/**
	 * Problem 1.4.
	 *
	 * <p>Time: O(n^2) in the worst case, where n = length of the strings. The main computation is the shifting of the
	 * array elements.</p>
	 */
	static String replaceSpaces(String s) {
		char[] chars = s.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				// shift the array 2 positions to the right for the replacement. the shift starts
				// from (i + 1) to the end.
				// NOTE: we need to do the shifting in-place
				for (int j = chars.length - 1; j >= i + 3; j--) {
					chars[j] = chars[j - 2];
				}

				// replace space by"%20"
				chars[i] = '%';
				chars[i + 1] = '2';
				chars[i + 2] = '0';

				// no need to check the replacements
				i += 2;
			}
		}

		return String.valueOf(chars);
	}

	/**
	 * Problem 1.4.
	 *
	 * <p>Time: O(n) in the worst case, where n = length of the strings.</p>
	 */
	static char[] replaceSpaces_2(char[] chars, int size) {
		int j = chars.length - 1;
		for (int i = size - 1; i >= 0; i--) {
			if (chars[i] == ' ') {
				chars[j--] = '0';
				chars[j--] = '2';
				chars[j--] = '%';
			} else {
				chars[j--] = chars[i];
			}
		}
		return chars;
	}

	/**
	 * Problem 1.5.
	 *
	 * <p>Time: O(n), where n = length of the given string</p>
	 * <p>Memory: O(n)</p>
	 */
	static String compress(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int count = 1;
			// keep finding all the similar consecutive characters
			while (i < s.length() - 1 && ch == s.charAt(i + 1)) {
				count++;
				i++;
			}
			builder.append(ch).append(count);
		}

		String compressed = builder.toString();
		return compressed.length() < s.length() ? compressed : s;
	}

	/**
	 * Problem 1.6.
	 *
	 * <p>Time: O(m*n), where m and n are the number of rows and columns of the given matrix.</p>
	 * <p>Memory: O(m*n)</p>
	 */
	static Integer[][] rotateRight_1(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;

		// O(m*n) memory
		Integer[][] result = new Integer[nCols][nRows];

		// right rotation
		for (int origRow = 0; origRow < nRows; origRow++) {
			int col = (nRows - 1) - origRow;
			for (int origCol = 0; origCol < nCols; origCol++) {
				int row = origCol;
				result[row][col] = matrix[origRow][origCol];
			}
		}

		return result;
	}

	/**
	 * Problem 1.6.
	 *
	 * <p>In-place rotation.</p>
	 * <p>Time: O(m*n), where m and n are the number of rows and columns of the given matrix.</p>
	 */
	static void rotateRight_2(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		if (nRows != nCols) {
			throw new IllegalArgumentException("This in-place rotation only supports square matrix.");
		}

		// NOTE: We do the rotation in-place. The rotation is done layer by layer and in inward direction.
		int nLayers = (int) Math.ceil(nRows / 2);
		for (int layerIndex = 0; layerIndex < nLayers; layerIndex++) {
			int top = layerIndex, right = nCols - 1 - layerIndex, bottom = nRows - 1 - layerIndex, left = layerIndex;
			// Because we rotate from left to right, the element at position (nRows - layerIndex) is already the
			// correct element when we reach it. Thus, we don't rotate it.
			for (int i = layerIndex; i < (nRows - layerIndex) - 1; i++) { // nRows or nCols is fine because they are equal
				Integer tmp = matrix[top][i];

				// rotate the left-most column to become the top-most row
				matrix[top][i] = matrix[nRows - 1 - i][left];
				// rotate the bottom-most row to become the left-most column
				matrix[nRows - 1 - i][left] = matrix[bottom][nCols - 1 - i];
				// rotate the right-most column to become the bottom-most row
				matrix[bottom][nCols - 1 - i] = matrix[i][right];
				// rotate the top-most row to become the right-most column
				matrix[i][right] = tmp;
			}
		}
	}

	/**
	 * Problem 1.7.
	 *
	 * <p>Time: O(m*n*(m+n)), where m and n are the number of rows and columns of the given matrix.</p>
	 * <p>Memory: O(m*n)</p>
	 */
	static Integer[][] setZeros(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;

		Integer[][] result = new Integer[nRows][nCols];

		// clone the original matrix
		for (int i = 0; i < nRows; i++) {
			System.arraycopy(matrix[i], 0, result[i], 0, nCols);
		}

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if (matrix[i][j] == 0) {
					// set zeros for the whole row i
					for (int k = 0; k < nCols; k++) {
						result[i][k] = 0;
					}
					// set zeros for the whole column j
					for (int k = 0; k < nRows; k++) {
						result[k][j] = 0;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Problem 1.7.
	 *
	 * <p>Time: O(m*n*(m+n)), where m and n are the number of rows and columns of the given matrix.</p>
	 * <p>Memory: O(m + n)</p>
	 */
	static Integer[][] setZeros_2(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;

		boolean[] rowsToZero = new boolean[nRows];
		boolean[] colsToZero = new boolean[nCols];

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if (matrix[i][j] == 0) {
					rowsToZero[i] = true;
					colsToZero[i] = true;
				}
			}
		}

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if (rowsToZero[i] || colsToZero[j]) {
					matrix[i][j] = 0;
				}
			}
		}

		return matrix;
	}

	/**
	 * Problem 1.8.
	 * 
	 * <p>Complexity: O(n), where n = length of the two strings</p>
	 */
	static boolean checkRotation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		if (s1.equals(s2)) {
			return true;
		}

		// Assume that s1 = xy and the rotation happens at the point between x and y, then s2 is the rotation of s1 iff s2 = yx.
		// If s1.length = s2.length and s1 != s2, then s2 = yx iff s2 is a substring of s1 + s1 = xyxy

		return (s1 + s1).contains(s2);
	}

}
