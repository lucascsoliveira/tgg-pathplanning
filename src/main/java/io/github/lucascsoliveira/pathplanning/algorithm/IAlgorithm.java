/**
 * 
 */
package io.github.lucascsoliveira.pathplanning.algorithm;

import java.util.List;

import io.github.lucascsoliveira.pathplanning.util.IntegerOrderedPair;

/**
 * @author lucas
 *
 */
public interface IAlgorithm {

	public List<IntegerOrderedPair> run(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal);
	
	public List<IntegerOrderedPair> run(Double[][] costs, IntegerOrderedPair start, IntegerOrderedPair goal, Double threshold);

}
