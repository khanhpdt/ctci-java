package vn.khanhpdt.playgrounds.ctci.treesandgraphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * Problem 4.2
 *
 * @author khanhpdt
 */
public class RouteCheck {

	/**
	 * Checks if there is a route from source to dest in a directed graph.
	 *
	 */
	static <K, V> boolean checkExists(GraphVertex<K, V> source, GraphVertex<K, V> dest) {
		if (isAdjacent(source, dest)) {
			return true;
		}

		for (GraphVertex<K, V> adj : source.getAdjacents()) {
			if (adj.isNotDiscovered()) {
				adj.markDiscovered();
				if (checkExists(adj, dest)) {
					return true;
				}
			}
		}

		return false;
	}

	private static <K, V> boolean isAdjacent(GraphVertex<K, V> source, GraphVertex<K, V> dest) {
		return source != null && source.getAdjacents().stream().anyMatch(adj -> adj.equals(dest));
	}

}
