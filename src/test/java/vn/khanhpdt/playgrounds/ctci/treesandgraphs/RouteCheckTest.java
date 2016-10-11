package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class RouteCheckTest {

	private Graph<UUID, Integer> graph;

	@Before
	public void init() {
		initGraph();
	}

	private void initGraph() {
		graph = new Graph<>();
		IntStream.range(0, 6).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 3}, {0, 4}, {2, 1}, {2, 4}, {3, 5}, {4, 5}, {5, 2} });
	}

	@Test
	public void testExists_1() {
		assertThat(RouteCheck.checkExists(graph.getVertex(3), graph.getVertex(1)), is(true));
	}

	@Test
	public void testExists_2() {
		assertThat(RouteCheck.checkExists(graph.getVertex(0), graph.getVertex(2)), is(true));
	}

	@Test
	public void testExists_3() {
		assertThat(RouteCheck.checkExists(graph.getVertex(4), graph.getVertex(1)), is(true));
	}

	@Test
	public void testNotExists_1() {
		assertThat(RouteCheck.checkExists(graph.getVertex(1), graph.getVertex(5)), is(false));
	}

	@Test
	public void testNotExists_2() {
		assertThat(RouteCheck.checkExists(graph.getVertex(4), graph.getVertex(3)), is(false));
	}

	@Test
	public void testNotExists_3() {
		assertThat(RouteCheck.checkExists(graph.getVertex(5), graph.getVertex(0)), is(false));
	}
}
