package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

/**
 * Problem 4.5: Checks if a binary tree is a binary search tree.
 *
 * @author khanhpdt
 */
class BSTCheck {

	static <K, V extends Comparable<V>> boolean isBST(BinaryTreeNode<K, V> root) {
		if (root.isNull()) {
			return true;
		}

		boolean largerThanOrEqualToLeft = true;
		if (root.getLeft().isNotNull()) {
			largerThanOrEqualToLeft = root.getValue().compareTo(root.getLeft().getValue()) >= 0;
		}
		boolean smallerThanOrEqualToRight = true;
		if (root.getRight().isNotNull()) {
			smallerThanOrEqualToRight = root.getValue().compareTo(root.getRight().getValue()) <= 0;
		}
		boolean rootSafe = largerThanOrEqualToLeft && smallerThanOrEqualToRight;

		return rootSafe && isBST(root.getLeft()) && isBST(root.getRight());
	}
}
