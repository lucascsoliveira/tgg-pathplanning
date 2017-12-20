package io.github.lucascsoliveira.pathplanning.algorithm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import io.github.lucascsoliveira.pathplanning.util.IntegerOrderedPair;

public class Astar extends AlgorithmImpl {

	public List<IntegerOrderedPair> run(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal) {

		if (costs == null || costs.length <= 0 || costs[0].length <= 0)
			return null; // TODO: FAILURE!

		final int LINES = costs.length;
		final int COLUMNS = costs[0].length;

		// The set of nodes already evaluated
		List<IntegerOrderedPair> closedSet = new ArrayList<IntegerOrderedPair>();

		// The set of currently discovered nodes that are not evaluated yet.
		// Initially, only the start node is known.
		List<IntegerOrderedPair> openSet = new ArrayList<IntegerOrderedPair>();
		openSet.add(start);

		// For each node, which node it can most efficiently be reached from.
		// If a node can be reached from many nodes, cameFrom will eventually
		// contain the most efficient previous step.
		IntegerOrderedPair[][] cameFrom = new IntegerOrderedPair[LINES][COLUMNS];

		// For each node, the cost of getting from the start node to that node.
		Double[][] gScore = new Double[LINES][COLUMNS];

		// For each node, the total cost of getting from the start node to the
		// goal by passing by that node. That value is partly known, partly
		// heuristic.
		Double[][] fScore = new Double[LINES][COLUMNS];

		for (int line = 0; line < LINES; line++) {
			for (int column = 0; column < COLUMNS; column++) {
				gScore[line][column] = Double.POSITIVE_INFINITY;
				fScore[line][column] = Double.POSITIVE_INFINITY;
			}
		}

		// The cost of going from start to start is zero.
		gScore[start.getX()][start.getY()] = 0.0;

		// For the first node, that value is completely heuristic.
		fScore[start.getX()][start.getY()] = heuristic_cost_estimate(costs, start, goal);

		while (!openSet.isEmpty()) {
			// the node in openSet having the lowest fScore[] value
			IntegerOrderedPair current = getLowestCost(openSet, fScore);

			if (current.equals(goal)) {
				return reconstruct_path(cameFrom, current);
			}

			openSet.remove(current);
			closedSet.add(current);

			List<IntegerOrderedPair> neighborhood = getNeighborhood(current, costs);

			for (IntegerOrderedPair neighbor : neighborhood) {
				if (closedSet.contains(neighbor)) {
					continue; // Ignore the neighbor which is already evaluated.
				}

				if (!openSet.contains(neighbor)) { // Discover a new node
					openSet.add(neighbor);
				}

				// The distance from start to a neighbor the "dist_between"
				// function may vary as per the solution requirements.
				Double tentative_gScore = gScore[current.getX()][current.getY()]
						+ dist_between(costs, current, neighbor);
				if (tentative_gScore.compareTo(gScore[neighbor.getX()][neighbor.getY()]) >= 0)
					continue; // This is not a better path.

				// This path is the best until now. Record it!
				cameFrom[neighbor.getX()][neighbor.getY()] = current;
				gScore[neighbor.getX()][neighbor.getY()] = tentative_gScore;
				fScore[neighbor.getX()][neighbor.getY()] = gScore[neighbor.getX()][neighbor.getY()]
						+ heuristic_cost_estimate(costs, neighbor, goal);
			}
		}

		// return failure
		return null; // TODO: FAILURE!
	}

	private Double dist_between(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal) {
		return costs[goal.getX()][goal.getY()];
	}

	private Double heuristic_cost_estimate(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal) {
		return Math.sqrt(Math.pow(goal.getX() - start.getX(), 2.0) + Math.pow(goal.getY() - start.getY(), 2.0));
	}

}
