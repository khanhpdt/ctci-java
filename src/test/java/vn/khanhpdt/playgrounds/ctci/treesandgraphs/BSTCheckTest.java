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
public class BSTCheckTest {

	private BinarySearchTree<UUID, Integer> bst;

	@Before
	public void init() {
		initBST();
	}

	private void initBST() {
		bst = new BinarySearchTree<>();
		Stream.of(30, 20, 25, 35, 15, 32, 28, 34)
				.map(value -> BinaryTreeNode.from(UUID.randomUUID(), value))
				.forEach(bst::insert);
	}

	@Test
	public void testBST() {
		assertThat(BSTCheck.isBST(bst.getRoot()), is(true));
	}

	@Test
	public void testNonBST() {
		// make a node smaller than its left node to break the bst properties
		BinaryTreeNode<UUID, Integer> node = bst.getRoot().getLeft().getRight();
		node.setLeft(BinaryTreeNode.from(UUID.randomUUID(), node.getValue() + 1));

		assertThat(BSTCheck.isBST(bst.getRoot()), is(false));
	}

}