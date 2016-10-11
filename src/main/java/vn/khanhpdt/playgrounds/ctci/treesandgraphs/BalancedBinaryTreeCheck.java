package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

/**
 * Problem 4.1
 *
 * @author khanhpdt
 */
class BalancedBinaryTreeCheck {

	static <K, V extends Comparable<V>> boolean isBalanced(BinarySearchTree<K, V> bst) {
		if (bst == null) {
			throw new IllegalArgumentException("Null tree!");
		}
		return isBalanced(bst.getRoot());
	}

	private static <K, V extends Comparable<V>> boolean isBalanced(BinaryTreeNode<K, V> root) {
		// empty tree is balanced
		if (root.isNull()) {
			return true;
		}

		boolean balanced = Math.abs(root.getLeft().getHeight() - root.getRight().getHeight()) <= 1;
		return balanced && isBalanced(root.getLeft()) && isBalanced(root.getRight());
	}

}
