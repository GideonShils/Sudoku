import java.io.*;
import java.util.*;

public class sudoku {

	// Array to store grid
	int [][] grid = new int[9][9];

	public static void main(String [] args) 
	{
		try {

			// Make sure filename was entered
			if (args.length != 1) {
				System.out.println("Proper usage: java sudoku fileName.txt");
				System.out.println("Now quiting...");
				System.exit(1);
			}
			else {
				File fileName = new File(args[0]);

				if (fileName.exists()) {
					Scanner fileReader = new Scanner(fileName);
					new sudoku(fileReader);
				}
				else {
					throw new FileNotFoundException();
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File doesn't exist.");
			System.out.println("Now quiting...");
			System.exit(1);
		}
	}

	public sudoku(Scanner fileReader)
	{
		System.out.println();

		// Read file into grid and display grid
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid [i][j] = fileReader.nextInt();
				System.out.print(grid[i][j] + " ");
				if ((j+1) % 3 == 0 && j < 8) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if ((i+1) % 3 == 0 && i < 8) {
				System.out.println("- - -   - - -   - - -");
			}
		}
		System.out.println("\n-------------------------\n");
		System.out.println("Searching for solution...");

		double startTime = System.nanoTime();
		double endTime;
		double totalTime;

		// Call solve method starting in top left corner
		if (solve(0, 0)) {
			endTime = System.nanoTime();
			System.out.println("Here is the solution: ");

			// Print the solution
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(grid[i][j] + " ");
					if ((j+1) % 3 == 0 && j < 8) {
						System.out.print("| ");
					}
				}
				System.out.println();
				if ((i+1) % 3 == 0 && i < 8) {
					System.out.println("- - -   - - -   - - -");
				}
			}

			totalTime = (endTime - startTime) / 1000000000;
			System.out.printf("Solve time: %.2f seconds\n", totalTime);
		}

		else {
			System.out.println("No solution was found.");
		}
	}

	// Recursive method for solving
	public boolean solve(int row, int col) 
	{	
		// Move to next column when end of row is reached
		if (row == 9) {
			row = 0;
			if (++col == 9) {

				// Return true if every cell has been checked
				return true;
			}
		}

		// If cell already has a value, move onto next cell
		if (grid[row][col] != 0) {
			return solve(row+1, col);
		}

		else {
			// Try each possible value
			for (int i = 1; i < 10; i++) {

				// Check if value is valid
				if (safe(row, col, i)) {
					grid [row][col] = i;

					// If valid, move to next cell
					if (solve(row+1, col)) {
						return true;
					}
				}
			}
		}
		grid[row][col] = 0; // If value didn't lead to solution, reset to 0 and backtrack
		return false;
	}

	public boolean safe(int row, int col, int val) {
		// Check row
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == val) {
				return false;
			}
		}

		// Check column
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == val) {
				return false;
			}
		}

		// Check box
		int boxRow = (row / 3) * 3;
		int boxCol = (col / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[boxRow + i][boxCol + j] == val) {
					return false;
				}
			}
		}

		return true;

	}
}