import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
  private Field[][] board;

  Sudoku(String filename) {
    this.board = readsudoku(filename);
  }

  @Override
  public String toString() {
    String output = "╔═══════╦═══════╦═══════╗\n";
		for(int i=0;i<9;i++){
      if(i == 3 || i == 6) {
		  	output += "╠═══════╬═══════╬═══════╣\n";
		  }
      output += "║ ";
		  for(int j=0;j<9;j++){
		   	if(j == 3 || j == 6) {
          output += "║ ";
		   	}
         output += board[i][j] + " ";
		  }

      output += "║\n";
	  }
    output += "╚═══════╩═══════╩═══════╝\n";
    return output;
  }

  /**
	 * Reads sudoku from file
	 * @param filename
	 * @return 2d int array of the sudoku
	 */
	public static Field[][] readsudoku(String filename) {
		assert filename != null && filename != "" : "Invalid filename";
		String line = "";
		Field[][] grid = new Field[9][9];
		try {
		FileInputStream inputStream = new FileInputStream(filename);
        Scanner scanner = new Scanner(inputStream);
        for(int i = 0; i < 9; i++) {
        	if(scanner.hasNext()) {
        		line = scanner.nextLine();
        		for(int j = 0; j < 9; j++) {
              int numValue = Character.getNumericValue(line.charAt(j));
              if(numValue == 0) {
                grid[i][j] = new Field();
              } else if (numValue != -1) {
                grid[i][j] = new Field(numValue);
        			}
        		}
        	}
        }
        scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("error opening file: "+filename);
		}
    addNeighbours(grid);
		return grid;
	}

  /**
   * Adds a list of neighbours to each field, i.e., arcs to be satisfied
   * @param grid
   */
  private static void addNeighbours(Field[][] grid) {
      for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
              List<Field> neighbours = new ArrayList<>();
              neighbours.addAll(rowNeighbours(grid, i, j));
              neighbours.addAll(columnNeighbours(grid, i, j));
              neighbours.addAll(squareNeighbours(grid, i, j));
              grid[i][j].setNeighbours(neighbours);
          }
      }
  }

  /**
   * Finds the neighbours of a field in the same row
   * @param grid
   * @param i index of row
   * @param j index of column
   * @return list of neighbours in the same row
   */
  private static List<Field> rowNeighbours(Field[][] grid, int i, int j) {
      List<Field> sameRow = new ArrayList<>();

      for (int x = 0; x < 9; x++) {
          if (x != j) { //given field doesn't get added as its neighbour
              sameRow.add(grid[i][x]);
          }
      }
      return sameRow;
  }

  /**
   * Finds the neighbours of a field in the same column
   * @param grid
   * @param i index of row
   * @param j index of column
   * @return list of neighbours in the same column
   */
  private static List<Field> columnNeighbours(Field[][] grid, int i, int j) {
      List<Field> sameColumn = new ArrayList<>();

      for (int x = 0; x < 9; x++) {
          if (x != i) { //given field doesn't get added as its neighbour
              sameColumn.add(grid[x][j]);
          }
      }
      return sameColumn;
  }

  /**
   * Finds the neighbours of a field in the same sub-grid
   * @param grid
   * @param i index of row
   * @param j index of column
   * @return list of neighbours in the same sub-grid
   */
  private static List<Field> squareNeighbours(Field[][] grid, int i, int j) {
      List<Field> sameSquare = new ArrayList<>();
      int startRow = (i / 3) * 3; // top index of the given fields 3x3 sub-grid
      int startCol = (j / 3) * 3; // left index of the given fields 3x3 sub-grid

      for (int row = startRow; row < startRow + 3; row++) {
          for (int col = startCol; col < startCol + 3; col++) {
              if (row != i && col != j) { //given field doesn't get added as its neighbour
                  sameSquare.add(grid[row][col]);
              }
          }
      }
      return sameSquare;
  }

  /**
	 * Generates fileformat output
	 */
	public String toFileString(){
    String output = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        output += board[i][j].getValue();
      }
      output += "\n";
    }
    return output;
	}

  public Field[][] getBoard(){
    return board;
  }
}
