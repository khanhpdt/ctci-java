package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;

/**
 * Problem 4.3: Creates a BST with minimum height from increasingly sorted array.
 *
 * @author khanhpdt
 */
class MinimumHeightBST {

	static BinarySearchTree<UUID, Integer> buildFrom(int[] sortedIntegers) {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();
		buildFrom(sortedIntegers, 0, sortedIntegers.length - 1, bst);
		return bst;
	}

	private static void buildFrom(int[] sortedIntegers, int from, int to, BinarySearchTree<UUID, Integer> bst) {
		if (from > to) {
			return;
		}

		int middle = from + (int) Math.ceil((to - from) / 2);
		bst.insert(BinaryTreeNode.from(UUID.randomUUID(), sortedIntegers[middle]));
		buildFrom(sortedIntegers, from, middle - 1, bst);
		buildFrom(sortedIntegers, middle + 1, to, bst);
	}
}
