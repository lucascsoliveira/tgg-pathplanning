package io.github.lucascsoliveira.pathplanning.algorithm;

import java.util.ArrayList;
import java.util.List;

import io.github.lucascsoliveira.pathplanning.util.IntegerOrderedPair;

public class Dijkstra extends AlgorithmImpl {

	public List<IntegerOrderedPair> run(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal) {

		if (costs == null || costs.length <= 0 || costs[0].length <= 0)
			return null; // TODO: FAILURE!

		final int LINES = costs.length;
		final int COLUMNS = costs[0].length;

		Double[][] distances = new Double[LINES][COLUMNS];
		IntegerOrderedPair[][] predecessor = new IntegerOrderedPair[LINES][COLUMNS];
		List<IntegerOrderedPair> unvisited = new ArrayList<IntegerOrderedPair>();

		for (int line = 0; line < LINES; line++) {
			for (int column = 0; column < COLUMNS; column++) {
				distances[line][column] = Double.POSITIVE_INFINITY;
				predecessor[line][column] = null;
				unvisited.add(new IntegerOrderedPair(line, column));
			}
		}

		distances[start.getX()][start.getY()] = 0.0;

		while (!unvisited.isEmpty()) {
			// u ← extrair-mín(Q) //Q ← Q - {u}
			IntegerOrderedPair u = getLowestCost(unvisited, distances);

			unvisited.remove(u);

			// para cada v adjacente a u
			for (IntegerOrderedPair v : getNeighborhood(u, costs)) {
				// se d[v] > d[u] + w(u, v) //relaxe (u, v)
				if (distances[v.getX()][v.getY()] > distances[u.getX()][u.getY()] + costs[u.getX()][u.getY()]) {
					// então d[v] ← d[u] + w(u, v)
					distances[v.getX()][v.getY()] = distances[u.getX()][u.getY()] + costs[u.getX()][u.getY()];
					// π[v] ← u
					predecessor[v.getX()][v.getY()] = u;
				}
			}
		}

		return reconstruct_path(predecessor, goal);
	}

}
