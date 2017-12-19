package io.github.lucascsoliveira.pathplanning.util;

public class FileParameter {
	private IntegerOrderedPair start;
	private IntegerOrderedPair goal;
	private Double[][] matrix;

	public FileParameter() {
		super();
	}

	public FileParameter(IntegerOrderedPair start, IntegerOrderedPair goal, Double[][] matrix) {
		super();
		this.start = start;
		this.goal = goal;
		this.matrix = matrix;
	}

	public IntegerOrderedPair getStart() {
		return start;
	}

	public void setStart(IntegerOrderedPair start) {
		this.start = start;
	}

	public IntegerOrderedPair getGoal() {
		return goal;
	}

	public void setGoal(IntegerOrderedPair goal) {
		this.goal = goal;
	}

	public Double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Double[][] matrix) {
		this.matrix = matrix;
	}

}
