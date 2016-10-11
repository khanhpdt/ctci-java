package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class BalancedBinaryTreeCheckTest {

	private BinarySearchTree<UUID, Integer> defaultBST;

	@Before
	public void init() {
		defaultBST = getDefaultTree();
	}

	private BinarySearchTree<UUID, Integer> getDefaultTree() {
		List<BinaryTreeNode<UUID, Integer>> nodes =
				Stream.of(30, 20, 25, 35, 15, 40, 32)
						.map(value -> BinaryTreeNode.from(UUID.randomUUID(), value))
						.collect(Collectors.toList());

		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();
		nodes.forEach(bst::insert);
		return bst;
	}

	@Test
	public void testBalanced_completeTree() {
		assertThat(BalancedBinaryTreeCheck.isBalanced(defaultBST.getRoot()), is(true));
	}

	@Test
	public void testBalanced_incompleteTree() {
		defaultBST.remove(32);
		assertThat(BalancedBinaryTreeCheck.isBalanced(defaultBST.getRoot()), is(true));
	}

	@Test
	public void testUnbalanced() {
		defaultBST.insert(BinaryTreeNode.from(UUID.randomUUID(), 12));
		defaultBST.insert(BinaryTreeNode.from(UUID.randomUUID(), 10));
		assertThat(BalancedBinaryTreeCheck.isBalanced(defaultBST.getRoot()), is(false));
	}

}