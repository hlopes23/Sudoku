import java.util.Scanner;

public class Game {

    private Board board;
    private String name;

    public Game() {

        printIntro();
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        printMenu(name);
        Difficulty difficulty = Difficulty.valueOf((scanner.next()).toUpperCase());
        board = new Board(difficulty);
    }


    public void start (){

        Scanner input = new Scanner(System.in);

        while (true) {

            String play = input.nextLine();

           userOptions(play, input);
           userPlays(play);
        }
    }


    private void printIntro(){
        System.out.println(
                '\n' + "\u001B[33m" + "  *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    S U D O K U    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    " + "\u001B[0m" +
                        '\n' + '\n' + "Please insert player's name: "+"\u001B[0m");
    }


    private void printMenu(String name){

        System.out.println('\n' + "Welcome to Sudoku " + name + "!" +
            '\n' +
            '\n' + "If this is your first time playing Sudoku, you must know that Sudoku is a numeric puzzle set in a 9X9 Grid, and the purpose of this" +
            '\n' + "game is to complete the puzzle without repeating the numbers (1 to 9) in every row, every column, or in every 3x3 box. Easy, right?" +
            '\n' +
            '\n' +
            "OPTIONS: " +
            '\n' + " solvecell + row number + col number -- gives you any cell you choose (ex: solvecell -> 3 9)." +
            '\n' + " solve -- gives you the Sudoku (ex: solve)." +
            '\n' + " restart -- restarts the puzzle you're trying to figure out. (ex: restart)" +
            '\n' + " switch + difficulty -- sets a new puzzle in whatever difficulty you want. (ex: switch hard)" +
            '\n' + " leave -- the game ends without the solution being revealed. (ex: leave)" +
            '\n' +
            '\n' +
            "LEVELS: " +
            '\n' + " Easy -- 40 cells available" +
            '\n' + " Medium -- 34 cells available" +
            '\n' + " Hard -- 27 cells available" +
            '\n' + " Expert -- 20 cells available" +
            '\n' +
            '\n' +
            "Select your level: ");
    }


    private void userOptions(String play, Scanner input){

        if (play.equalsIgnoreCase(Options.SOLVE.toString().trim())) {
            System.out.println("\u001B[30m" + "Sudoku solved... But not by you:( " + '\n' + "Let's go again? Press <enter>!" + '\n');
            board.printOriginal();
            System.out.println(" ");
            return;
        }

        if (play.equalsIgnoreCase(Options.SOLVECELL.toString().trim())) {
            System.out.println("\u001B[30m" + "Which cell would you like to be revealed?" + '\n');

            String solveCell = input.nextLine();

            try{
                String[] parts = solveCell.split(" ");
                int row = Integer.parseInt(parts[0]) - 1; // Conversion to index 0-8
                int col = Integer.parseInt(parts[1]) - 1;
                int num = board.revealCell(row, col);

                board.updateCell(row, col, num);
            } catch (UpdateCellException e) {
                System.out.println(e.getMessage());
            }
            board.printEditable();
            System.out.println(" ");
            return;
        }

        if (play.equalsIgnoreCase(Options.LEAVE.toString().trim())) {
            System.out.println("\u001B[30m" + "Do you want to quit game? Y/N");
            String inputExit = input.nextLine();
            if (inputExit.equalsIgnoreCase("Y")) {
                System.out.println("\u001B[30m" + "See you next time!");
                System.exit(1);
            }
        }
    }


    private void userPlays(String play){
        String[] par = play.split(" ");
        int row = Integer.parseInt(par[0]) - 1; // Conversion to index 0-8
        int col = Integer.parseInt(par[1]) - 1; // Conversion to index 0-8
        int num = Integer.parseInt(par[2]); // Value to be assigned

        try {
            board.updateCell(row, col, num);
        } catch (UpdateCellException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" ");
        board.printEditable();
        System.out.println(" ");
    }

}