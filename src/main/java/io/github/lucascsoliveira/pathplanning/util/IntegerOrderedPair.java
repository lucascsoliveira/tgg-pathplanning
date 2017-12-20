package io.github.lucascsoliveira.pathplanning.util;

public class IntegerOrderedPair extends OrderedPair<Integer> {

	private CarState carState;

	public IntegerOrderedPair() {
		super();
	}

	public IntegerOrderedPair(Integer x, Integer y) {
		super(x, y);
	}

	public IntegerOrderedPair(Integer x, Integer y, CarState carState) {
		super(x, y);
		this.carState = carState;
	}

	public CarState getCarState() {
		return carState;
	}

	public void setCarState(CarState carState) {
		this.carState = carState;
	}
	
	

}
