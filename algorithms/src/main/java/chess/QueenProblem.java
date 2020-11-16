package chess;

public class QueenProblem {

	private final int NUMBER_OF_QUEENS;
	private final int[][] cells;

	public QueenProblem(int N) {
		NUMBER_OF_QUEENS = N;
		cells = new int[N][N];
		emptyBoard();
	}

	private void emptyBoard() {
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
			for (int column = 0; column < NUMBER_OF_QUEENS; column++) {
				cells[row][column] = 0;
			}
		}
	}

	public void printBoard() {
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
			for (int column = 0; column < NUMBER_OF_QUEENS; column++) {
				if (cells[row][column] == 0) {
					System.out.print("| ");
				}
				else {
					System.out.print("|*");
				}
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println();
	}

	public boolean isSafeWhenPlaced(int row, int column) {
		int i, j;

		/* Check this row on left side */
		for (i = 0; i < column; i++) {
			if (cells[row][i] == 1) {
				return false;
			}
		}

		/* Check upper diagonal on left side */
		for (i = row, j = column; i >= 0 && j >= 0; i--, j--) {
			if (cells[i][j] == 1) {
				return false;
			}
		}

		/* Check lower diagonal on left side */
		for (i = row, j = column; j >= 0 && i < NUMBER_OF_QUEENS; i++, j--) {
			if (cells[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	/* A recursive utility function to solve N
       Queen problem */
	private boolean solveNQUtil(int col) {
        /* base case: If all queens are placed
           then return true */
		if (col >= NUMBER_OF_QUEENS) {
			return true;
		}

        /* Consider this column and try placing
           this queen in all rows one by one */
		for (int i = 0; i < NUMBER_OF_QUEENS; i++) {
            /* Check if the queen can be placed on
               board[i][col] */
			if (isSafeWhenPlaced(i, col)) {
				/* Place this queen in board[i][col] */
				cells[i][col] = 1;

				/* recur to place rest of the queens */
				if (solveNQUtil(col + 1) == true) {
					return true;
				}

                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
				cells[i][col] = 0; // BACKTRACK
			}
		}

        /* If the queen can not be placed in any row in
           this colum col, then return false */
		return false;
	}

	/* This function solves the N Queen problem using
	   Backtracking.  It mainly uses solveNQUtil () to
	   solve the problem. It returns false if queens
	   cannot be placed, otherwise, return true and
	   prints placement of queens in the form of 1s.
	   Please note that there may be more than one
	   solutions, this function prints one of the
	   feasible solutions.*/
	public boolean solveNQ() {

		if (solveNQUtil(0) == false) {
			System.out.print("Solution does not exist");
			return false;
		}

		printBoard();
		return true;
	}
}
