import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        while (!stop) {
            String filePath = "";
            System.out.println("Select a sudoku file(1, 2, 3, 4, 5, anything else to stop):");
            System.out.println("""
                1- Sudoku1.txt
                2- Sudoku2.txt
                3- Sudoku3.txt
                4- Sudoku4.txt
                5- Sudoku5.txt""");
            String s = scanner.nextLine();
            switch (s) {
                case "1" -> filePath = "Sudoku1.txt";
                case "2" -> filePath = "Sudoku2.txt";
                case "3" -> filePath = "Sudoku3.txt";
                case "4" -> filePath = "Sudoku4.txt";
                case "5" -> filePath = "Sudoku5.txt";
                default -> stop = true;
            }
            if (!stop) {
                start(filePath);
            }
        }
    }

    /**
     * Start AC-3 using the sudoku from the given filepath, and reports whether the sudoku could be solved or not, and how many steps the algorithm performed
     * 
     * @param filePath
     */
    public static void start(String filePath){
        Game game1 = new Game(new Sudoku(filePath));
        game1.showSudoku();

        if (game1.solve() && game1.validSolution()){
            System.out.println("Solved!");
        }
        else{
            System.out.println("Could not solve this sudoku :(");
        }
        game1.showSudoku();
    }
}
