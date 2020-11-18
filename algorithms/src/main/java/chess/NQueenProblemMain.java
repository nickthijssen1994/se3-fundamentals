package chess;

public class NQueenProblemMain {

	public static void main(String[] args) {
		for (int queens = 4; queens < 14; queens++) {
			QueenProblem queenProblem = new QueenProblem(queens);
			queenProblem.calculateSolution();
		}
	}
}
