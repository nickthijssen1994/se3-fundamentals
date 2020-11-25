package chess;

public class ExampleSolutionMain {

	public static void main(String[] args) {
		for (int queens = 1; queens < 18; queens++) {
			ExampleSolution exampleSolution = new ExampleSolution(queens);
			exampleSolution.calculateSolution();
		}
	}
}
