package chess;

public class QueenProblemMain {

	public static void main(String[] args) {
		for (int queens = 1; queens < 30; queens++) {
	QueenProblem queenProblem = new QueenProblem(queens);
			queenProblem.calculateSolution();
		}
	}
}
