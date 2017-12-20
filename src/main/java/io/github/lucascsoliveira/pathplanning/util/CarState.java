package io.github.lucascsoliveira.pathplanning.util;

public class CarState {

	private DoubleOrderedPair position;
	private Double theta;

	public CarState() {
		super();
	}

	public CarState(Double x, Double y, Double theta) {
		super();
		this.position = new DoubleOrderedPair(x, y);
		this.theta = theta;
	}

	public DoubleOrderedPair getPosition() {
		return position;
	}

	public void setPosition(DoubleOrderedPair position) {
		this.position = position;
	}

	public Double getTheta() {
		return theta;
	}

	public void setTheta(Double theta) {
		this.theta = theta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((theta == null) ? 0 : theta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarState other = (CarState) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (theta == null) {
			if (other.theta != null)
				return false;
		} else if (!theta.equals(other.theta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [position=" + position + ", theta=" + theta + "]";
	}

}
