package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

/**
 * Problem 4.1
 *
 * @author khanhpdt
 */
class BalancedBinaryTreeCheck {

	static <K, V extends Comparable<V>> boolean isBalanced(BinaryTreeNode<K, V> root) {
		if (root.isNull()) {
			return true;
		}

		boolean balanced = Math.abs(getHeight(root.getLeft()) - getHeight(root.getRight())) <= 1;
		return balanced && isBalanced(root.getLeft()) && isBalanced(root.getRight());
	}

	private static <K, V extends Comparable<V>> int getHeight(BinaryTreeNode<K, V> root) {
		if (root.isNull()) {
			return 0;
		}
		return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
	}

}
