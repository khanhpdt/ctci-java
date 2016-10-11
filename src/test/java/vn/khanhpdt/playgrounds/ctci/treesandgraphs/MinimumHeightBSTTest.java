package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class MinimumHeightBSTTest {

	@Test
	public void test_1() {
		int[] sortedIntegers = IntStream.range(0, 7).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.buildFrom(sortedIntegers);

		assertThat(bst.getHeight(), is(2));
	}

	@Test
	public void test_2() {
		int[] sortedIntegers = IntStream.range(0, 8).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.buildFrom(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

	@Test
	public void test_3() {
		int[] sortedIntegers = IntStream.range(0, 12).toArray();

		BinarySearchTree<UUID, Integer> bst = MinimumHeightBST.buildFrom(sortedIntegers);

		assertThat(bst.getHeight(), is(3));
	}

}