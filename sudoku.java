import java.io.*;
import java.util.*;

public class sudoku {
	public static void main(String [] args) 
	{
		try {
			if (args.length != 1) {
				System.out.println("Enter filename");
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
			System.out.println("File doesn't exist");
			System.exit(1);
		}
	}

	public sudoku(Scanner fileReader)
	{
		int [][] grid = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid [i][j] = fileReader.nextInt();
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
}