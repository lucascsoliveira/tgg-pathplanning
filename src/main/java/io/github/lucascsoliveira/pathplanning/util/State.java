package io.github.lucascsoliveira.pathplanning.util;

public class State {

	private IntegerOrderedPair discrete;
	private DoubleOrderedPair continuous;
	private Double theta;

	public State() {
		super();
	}

	public State(Double x, Double y, Double theta) {
		super();
		this.continuous = new DoubleOrderedPair(x, y);
		this.discrete = new IntegerOrderedPair(round(x), round(y));
		this.theta = theta;
	}

	public State(Integer x, Integer y, Double theta) {
		super();
		this.continuous = new DoubleOrderedPair(x.doubleValue(), y.doubleValue());
		this.discrete = new IntegerOrderedPair(x, y);
		this.theta = theta;
	}

	public State(IntegerOrderedPair discrete, DoubleOrderedPair continuous, Double theta) {
		super();
		this.discrete = discrete;
		this.continuous = continuous;
		this.theta = theta;
	}

	public IntegerOrderedPair getDiscrete() {
		return discrete;
	}

	public void setDiscrete(IntegerOrderedPair discrete) {
		this.discrete = discrete;
	}

	public DoubleOrderedPair getContinuous() {
		return continuous;
	}

	public void setContinuous(DoubleOrderedPair continuous) {
		this.continuous = continuous;
	}

	public Double getTheta() {
		return theta;
	}

	public void setTheta(Double theta) {
		this.theta = theta;
	}

	private Integer round(Double x) {
		return new Long(Math.round(x)).intValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continuous == null) ? 0 : continuous.hashCode());
		result = prime * result + ((discrete == null) ? 0 : discrete.hashCode());
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
		State other = (State) obj;
		if (continuous == null) {
			if (other.continuous != null)
				return false;
		} else if (!continuous.equals(other.continuous))
			return false;
		if (discrete == null) {
			if (other.discrete != null)
				return false;
		} else if (!discrete.equals(other.discrete))
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
		return "State [discrete=" + discrete + ", continuous=" + continuous + ", theta=" + theta + "]";
	}

}
