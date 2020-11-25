package chess;

public class ExampleSolution {

	private final int NUMBER_OF_QUEENS;
	private final int[][] board;

	/* ld is an array where its indices indicate row-col+N-1
	(N-1) is for shifting the difference to store negative
	indices */
	private final int[] ld = new int[30];

	/* rd is an array where its indices indicate row+col
	and used to check whether a queen can be placed on
	right diagonal or not*/
	private final int[] rd = new int[30];

	/*column array where its indices indicates column and
	used to check whether a queen can be placed in that
		row or not*/
	private final int[] cl = new int[30];

	public ExampleSolution(int N) {
		NUMBER_OF_QUEENS = N;
		board = new int[NUMBER_OF_QUEENS][NUMBER_OF_QUEENS];
		emptyBoard();
	}

	private void emptyBoard() {
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
			for (int column = 0; column < NUMBER_OF_QUEENS; column++) {
				board[row][column] = 0;
			}
		}
	}

	/* A utility function to print solution */
	public void printBoard() {
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
			for (int column = 0; column < NUMBER_OF_QUEENS; column++) {
				if (board[row][column] == 0) {
					System.out.print("| ");
				}
				else {
					System.out.print("|*");
				}
			}
			System.out.print("|");
			System.out.println();
		}
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
        /* A check if a queen can be placed on
        board[row][col].We just need to check
        ld[row-col+n-1] and rd[row+coln] where
        ld and rd are for left and right
        diagonal respectively*/
			if (i + col <= 30 && (ld[i - col + NUMBER_OF_QUEENS - 1] != 1 && rd[i + col] != 1) && cl[i] != 1) {
				/* Place this queen in board[i][col] */
				board[i][col] = 1;
				ld[i - col + NUMBER_OF_QUEENS - 1] = rd[i + col] = cl[i] = 1;

				/* recur to place rest of the queens */
				if (solveNQUtil(col + 1)) {
					return true;
				}

            /* If placing queen in board[i][col]
            doesn't lead to a solution, then
            remove queen from board[i][col] */
				board[i][col] = 0; // BACKTRACK
				ld[i - col + NUMBER_OF_QUEENS - 1] = rd[i + col] = cl[i] = 0;
			}
		}

    /* If the queen cannot be placed in any row in
        this colum col then return false */
		return false;
	}

	/* This function solves the N Queen problem using
	Backtracking. It mainly uses solveNQUtil() to
	solve the problem. It returns false if queens
	cannot be placed, otherwise, return true and
	prints placement of queens in the form of 1s.
	Please note that there may be more than one
	solutions, this function prints one of the
	feasible solutions.*/
	public boolean calculateSolution() {
		System.out.println();

		long startTime = System.nanoTime();

		if (solveNQUtil(0) == false) {
			System.out.println("Solution does not exist for " + NUMBER_OF_QUEENS + " number of Queens.");
			return false;
		}

		long endTime = System.nanoTime();
		long duration = endTime - startTime;

		System.out.println("Calculation for " + NUMBER_OF_QUEENS + " Queens took " + duration + " nanoseconds.");
		printBoard();
		return true;
	}
}
