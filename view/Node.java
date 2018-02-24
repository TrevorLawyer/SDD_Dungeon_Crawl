package view;
import java.util.Set;

/**
 * A node in a graph, useful for pathfinding.
 * @param <T>
 *            Actual type of the node
 */
public interface Node<T> {
	/**
	 * The heuristic cost to move from the current node to the goal. In most
	 * cases this is the Manhattan distance.

	 */
	double getHeuristic(T goal);

	/**
	 * The cost of moving from the current node to the neighbor. In most cases
	 * this is just the distance between the nodes, but additional terrain cost
	 * can be added as well (for example climbing a mountain is more expensive
	 * than walking on a road).
	 */
	double getTraversalCost(T neighbour);

	/**
	 * Gets the set of neighboring nodes.
	 */
	Set<T> getNeighbours();
}