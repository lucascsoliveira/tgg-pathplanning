package io.github.lucascsoliveira.pathplanning.util;

public class IntegerOrderedPair extends OrderedPair<Integer, Integer> {

	private Double theta;

	public IntegerOrderedPair() {
		super();
	}

	public IntegerOrderedPair(Integer x, Integer y) {
		super(x, y);
	}

	public IntegerOrderedPair(Integer x, Integer y, Double theta) {
		super(x, y);
		this.theta = theta;
	}

	public Double getTheta() {
		return theta;
	}

	public void setTheta(Double theta) {
		this.theta = theta;
	}

	@Override
	public String toString() {
		return "IntegerOrderedPair [x=" + super.getX() + ", y=" + super.getY() + ", theta=" + theta + "]";
	}

}
