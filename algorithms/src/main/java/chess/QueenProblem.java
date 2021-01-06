package chess;

public class QueenProblem {

	private int totalCalculations = 0;
	private final int NUMBER_OF_QUEENS;
	private final int[][] cells;

	public QueenProblem(int N) {
		NUMBER_OF_QUEENS = N;
		cells = new int[N][N];
		emptyBoard();
	}

	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	private void emptyBoard() {
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
			for (int column = 0; column < NUMBER_OF_QUEENS; column++) {
				cells[row][column] = 0;
			}
		}
	}

	private void printBoard() {
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
	}

	// Check if a Queen can be placed on a given cell
	private boolean canBePlaced(int row, int column) {

		// Check if a Queen is already placed here
		if (cells[row][column] == 1) {
			return false;
		}

		// Check if a Queen is on the same column
		for (int rowCell = 0; rowCell < NUMBER_OF_QUEENS; rowCell++) {
			if (cells[rowCell][column] == 1) {
				return false;
			}
		}

		// Check if a Queen is on the same row
		for (int columnCell = 0; columnCell < NUMBER_OF_QUEENS; columnCell++) {
			if (cells[row][columnCell] == 1) {
				return false;
			}
		}

		// Check if a Queen is on upper left diagonal
		for (int rowCell = row, columnCell = column; rowCell >= 0 && columnCell >= 0; rowCell--, columnCell--) {
			if (cells[rowCell][columnCell] == 1) {
				return false;
			}
		}

		// Check if a Queen is on lower right diagonal
		for (int rowCell = row, columnCell = column; rowCell < NUMBER_OF_QUEENS && columnCell < NUMBER_OF_QUEENS; rowCell++, columnCell++) {
			if (cells[rowCell][columnCell] == 1) {
				return false;
			}
		}

		// Check if a Queen is on upper right diagonal
		for (int rowCell = row, columnCell = column; rowCell >= 0 && columnCell < NUMBER_OF_QUEENS; rowCell--,
				columnCell++) {
			if (cells[rowCell][columnCell] == 1) {
				return false;
			}
		}

		// Check if a Queen is on lower left diagonal
		for (int rowCell = row, columnCell = column; rowCell < NUMBER_OF_QUEENS && columnCell >= 0; rowCell++,
				columnCell--) {
			if (cells[rowCell][columnCell] == 1) {
				return false;
			}
		}

		return true;
	}

	/* A recursive utility function to solve N
       Queen problem */
	private boolean solveProblem(int column) {
        /* base case: If all queens are placed
           then return true */
		if (column >= NUMBER_OF_QUEENS) {
			return true;
		}

        /* Consider this column and try placing
           this queen in all rows one by one */
		for (int row = 0; row < NUMBER_OF_QUEENS; row++) {
            /* Check if the queen can be placed on
               board[row][column] */
			totalCalculations++;
			if (canBePlaced(row, column)) {
				/* Place this queen in board[row][column] */
				cells[row][column] = 1;

				/* recur to place rest of the queens */
				if (solveProblem(column + 1) == true) {
					return true;
				}

                /* If placing queen in board[row][column]
                   doesn't lead to a solution then
                   remove queen from board[row][column] */
				cells[row][column] = 0; // BACKTRACK
			}
		}

        /* If the queen can not be placed in any row in
           this colum column, then return false */
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
	public boolean calculateSolution() {

		System.out.println();

		long startTime = System.nanoTime();

		if (solveProblem(0) == false) {
			System.out.println("Solution does not exist for " + NUMBER_OF_QUEENS + " number of Queens.");
			return false;
		}
		Runtime runtime = Runtime.getRuntime();
		// Run the garbage collector
		runtime.gc();
		// Calculate the used memory
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));

		long endTime = System.nanoTime();
		long duration = endTime - startTime;

		System.out.println("Calculation for " + NUMBER_OF_QUEENS + " Queens took " + duration + " nanoseconds.");
		System.out.println("Calculation for " + NUMBER_OF_QUEENS + " Queens took " + totalCalculations + " " +
				"calculations" +
				".");
		printBoard();
		return true;
	}
}
