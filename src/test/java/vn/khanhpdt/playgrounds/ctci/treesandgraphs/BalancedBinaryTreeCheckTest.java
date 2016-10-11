package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.trees.BinarySearchTree;

import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class BalancedBinaryTreeCheckTest {

	private BinarySearchTree<UUID, Integer> bst;

	@Before
	public void init() {
		initBST();
	}

	private void initBST() {
		bst = new BinarySearchTree<>();
		Stream.of(30, 20, 25, 35, 15, 40, 32)
				.map(value -> BinaryTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);
	}

	@Test
	public void testBalanced_completeTree() {
		assertThat(BalancedBinaryTreeCheck.isBalanced(bst.getRoot()), is(true));
	}

	@Test
	public void testBalanced_incompleteTree() {
		bst.remove(32);
		assertThat(BalancedBinaryTreeCheck.isBalanced(bst.getRoot()), is(true));
	}

	@Test
	public void testUnbalanced() {
		bst.insert(BinaryTreeNode.from(UUID.randomUUID(), 12));
		bst.insert(BinaryTreeNode.from(UUID.randomUUID(), 10));
		assertThat(BalancedBinaryTreeCheck.isBalanced(bst.getRoot()), is(false));
	}

}