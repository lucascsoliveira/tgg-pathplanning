package io.github.lucascsoliveira.pathplanning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import io.github.lucascsoliveira.pathplanning.algorithm.Astar;
import io.github.lucascsoliveira.pathplanning.algorithm.IAlgorithm;
import io.github.lucascsoliveira.pathplanning.util.FileParameter;
import io.github.lucascsoliveira.pathplanning.util.IntegerOrderedPair;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "file/matrix.txt";
		Double threshold = 0.5;

		FileParameter parameters = readFromFile(fileName);

		IntegerOrderedPair start = parameters.getStart();
		IntegerOrderedPair goal = parameters.getGoal();
		Double[][] matrix = parameters.getMatrix();

		IAlgorithm algorithm = new Astar();

		List<IntegerOrderedPair> output = algorithm.run(matrix, start, goal, threshold);

		printMatrix(matrix);
		printPath(matrix, output);
		// System.out.println(new Astar().getNeighborhood(start, matrix, 0.0));
	}

	private static FileParameter readFromFile(String fileName) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(fileName)).useLocale(Locale.US);

		int line = scanner.nextInt();
		int column = scanner.nextInt();

		IntegerOrderedPair start = new IntegerOrderedPair(scanner.nextInt(), scanner.nextInt());
		IntegerOrderedPair goal = new IntegerOrderedPair(scanner.nextInt(), scanner.nextInt());

		Double[][] matrix = new Double[line][column];

		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = scanner.nextDouble();
			}
		}

		scanner.close();

		return new FileParameter(start, goal, matrix);
	}

	public static void printPath(Double[][] matrix, List<IntegerOrderedPair> output) {
		for (int line = 0; line < matrix.length; line++) {
			for (int column = 0; column < matrix[0].length; column++) {
				if (output.contains(new IntegerOrderedPair(line, column)))
					System.out.printf("[ X  ]\t");
				else
					System.out.printf("[%.2f]\t", matrix[line][column]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printMatrix(Double[][] matrix) {
		for (Double[] line : matrix) {
			for (Double column : line) {
				System.out.printf("[%.2f]\t", column);
			}
			System.out.println();
		}
		System.out.println();
	}

}
