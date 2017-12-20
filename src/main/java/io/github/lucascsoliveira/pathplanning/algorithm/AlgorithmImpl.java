package io.github.lucascsoliveira.pathplanning.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.github.lucascsoliveira.pathplanning.util.CarState;
import io.github.lucascsoliveira.pathplanning.util.IntegerOrderedPair;

public abstract class AlgorithmImpl implements IAlgorithm {

	protected final double DELTA_TIME = 1.0; // Seconds
	protected final double VELOCITY = 1.0; // Meters
	protected final double L = 2.625;// Meters
	protected final double MAX_STEERING_ANGLE = 0.5337; // Rad

	protected final double ALL_RIGHT_STEERING_ANGLE = MAX_STEERING_ANGLE;
	protected final double ALL_LEFT_STEERING_ANGLE = (-1.0) * MAX_STEERING_ANGLE;
	protected final double STRAIGHT_STEERING_ANGLE = 0.0;

	public boolean inBounds(Double[][] matrix, int line, int column) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0)
			return false;

		return ((line >= 0 && line < matrix.length) && (column >= 0 && column < matrix[0].length));
	}

	public List<IntegerOrderedPair> getNeighborhood(IntegerOrderedPair current, Double[][] costs) {
		ArrayList<IntegerOrderedPair> neighborhood = new ArrayList<IntegerOrderedPair>();
		IntegerOrderedPair tmp;

		// LEFT
		tmp = calculateNeighbor(current, ALL_LEFT_STEERING_ANGLE, (Math.PI / 2));
		if (inBounds(costs, tmp.getX(), tmp.getY()))
			neighborhood.add(tmp);

		// STRAIGHT
		tmp = calculateNeighbor(current, STRAIGHT_STEERING_ANGLE, 0.0);
		if (inBounds(costs, tmp.getX(), tmp.getY()))
			neighborhood.add(tmp);

		// RIGHT
		tmp = calculateNeighbor(current, ALL_RIGHT_STEERING_ANGLE, (3 * Math.PI / 2));
		if (inBounds(costs, tmp.getX(), tmp.getY()))
			neighborhood.add(tmp);

		System.out.println(current);
		System.out.println(neighborhood);
		
		return neighborhood;
	}

	public List<IntegerOrderedPair> getNeighborhood1(IntegerOrderedPair current, Double[][] costs) {
		ArrayList<IntegerOrderedPair> neighborhood = new ArrayList<IntegerOrderedPair>();
		int line = current.getX(), column = current.getY();

		if (inBounds(costs, line, column)) {
			if (inBounds(costs, line - 1, column - 1))
				neighborhood.add(new IntegerOrderedPair(line - 1, column - 1));
			if (inBounds(costs, line - 1, column))
				neighborhood.add(new IntegerOrderedPair(line - 1, column));
			if (inBounds(costs, line - 1, column + 1))
				neighborhood.add(new IntegerOrderedPair(line - 1, column + 1));
			if (inBounds(costs, line, column - 1))
				neighborhood.add(new IntegerOrderedPair(line, column - 1));
			if (inBounds(costs, line, column + 1))
				neighborhood.add(new IntegerOrderedPair(line, column + 1));
			if (inBounds(costs, line + 1, column - 1))
				neighborhood.add(new IntegerOrderedPair(line + 1, column - 1));
			if (inBounds(costs, line + 1, column))
				neighborhood.add(new IntegerOrderedPair(line + 1, column));
			if (inBounds(costs, line + 1, column + 1))
				neighborhood.add(new IntegerOrderedPair(line + 1, column + 1));
		}

		return neighborhood;
	}

	public IntegerOrderedPair getLowestCost(List<IntegerOrderedPair> nodes, Double[][] costs) {
		Double lowestCost = Double.POSITIVE_INFINITY;
		IntegerOrderedPair lowestCostNode = null;

		for (IntegerOrderedPair node : nodes) {
			Double currentScore = costs[node.getX()][node.getY()];
			if (lowestCost.compareTo(currentScore) >= 0) {
				lowestCost = currentScore;
				lowestCostNode = node;
			}
		}

		return lowestCostNode;
	}

	public List<IntegerOrderedPair> reconstruct_path(IntegerOrderedPair[][] cameFrom, IntegerOrderedPair current) {
		LinkedList<IntegerOrderedPair> path = new LinkedList<IntegerOrderedPair>();

		while (current != null) {
			path.addFirst(current);
			current = cameFrom[current.getX()][current.getY()];
		}

		return path;
	}

	public List<IntegerOrderedPair> run(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal,
			Double threshold) {

		if (costs == null || costs.length <= 0 || costs[0].length <= 0)
			return null; // TODO: FAILURE!

		Double[][] newCosts = new Double[costs.length][costs[0].length];

		if (threshold != null) {
			for (int line = 0; line < newCosts.length; line++) {
				for (int column = 0; column < newCosts[0].length; column++) {
					if (threshold.compareTo(costs[line][column]) <= 0) {
						newCosts[line][column] = Double.POSITIVE_INFINITY;
					} else {
						newCosts[line][column] = costs[line][column];
					}
				}
			}
		}

		return run(newCosts, start, goal);
	}

	public Double calculateX(Double x, Double theta) {
		return x + DELTA_TIME * VELOCITY * Math.cos(theta);
	}

	public Double calculateY(Double y, Double theta) {
		return y + DELTA_TIME * VELOCITY * Math.sin(theta);
	}

	public Double calculateTheta(Double theta, Double steeringAngle) {
		return theta + DELTA_TIME * VELOCITY * Math.tan(steeringAngle) / L;
	}

	public IntegerOrderedPair calculateNeighbor(IntegerOrderedPair current, Double phi, Double defaultTheta) {
		Double theta, x, y;

		if (current.getCarState() != null) {
			theta = current.getCarState().getTheta();
			x = current.getCarState().getPosition().getX();
			y = current.getCarState().getPosition().getY();
		} else {
			theta = defaultTheta;
			x = current.getX().doubleValue();
			y = current.getY().doubleValue();
		}

		Double new_theta = calculateTheta(theta, phi);
		Double new_x = calculateX(x, new_theta);
		Double new_y = calculateY(y, new_theta);

		return new IntegerOrderedPair(new Long(Math.round(new_x)).intValue(), new Long(Math.round(new_y)).intValue(),
				new CarState(new_x, new_y, new_theta));
	}

}
