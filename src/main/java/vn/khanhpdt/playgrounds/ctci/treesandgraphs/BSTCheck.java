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

		boolean rootSafe = isLargerThanOrEqualTo(root, root.getLeft()) && isSmallerThanOrEqualTo(root, root.getRight());
		return rootSafe && isBST(root.getLeft()) && isBST(root.getRight());
	}

	private static <K, V extends Comparable<V>> boolean isLargerThanOrEqualTo(BinaryTreeNode<K, V> root,
	                                                                          BinaryTreeNode<K, V> subtreeRoot) {
		if (subtreeRoot.isNull()) {
			return true;
		}
		return root.compareTo(subtreeRoot) >= 0
				&& isLargerThanOrEqualTo(root, subtreeRoot.getLeft())
				&& isLargerThanOrEqualTo(root, subtreeRoot.getRight());
	}

	private static <K, V extends Comparable<V>> boolean isSmallerThanOrEqualTo(BinaryTreeNode<K, V> root,
	                                                                           BinaryTreeNode<K, V> subtreeRoot) {
		if (subtreeRoot.isNull()) {
			return true;
		}
		return root.compareTo(subtreeRoot) <= 0
				&& isSmallerThanOrEqualTo(root, subtreeRoot.getLeft())
				&& isSmallerThanOrEqualTo(root, subtreeRoot.getRight());
	}
}
