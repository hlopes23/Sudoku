import java.util.Scanner;

public class Game {

    private Board board;
    private String name;
    private Scanner scanner;

    public Game() throws InterruptedException {

        printIntro();
        scanner = new Scanner(System.in);
        name = scanner.nextLine().trim();
        printMenu(name);
        Difficulty difficulty = Difficulty.valueOf((scanner.nextLine()).toUpperCase().trim());
        board = new Board(difficulty);
    }


    public void start() throws InterruptedException {

        while (true) {
                userInputs();
        }
    }


    private void printIntro(){
        System.out.println(
                '\n' + Colors.YELLOW + Colors.BOLD + "  *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    S U D O K U    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    " + Colors.BOLD + Colors.DEFAULT +
                        '\n' + '\n' + "Player name: ");
    }


    private void printMenu(String name){

        System.out.println('\n' + "Welcome to Sudoku " + Colors.BOLD + name + Colors.DEFAULT + "!" + " " + "💛"+
                '\n' +
                '\n' + "If this is your first time playing Sudoku, you must know that Sudoku is a numeric puzzle set in a 9X9 Grid, and the purpose of this" +
                '\n' + "game is to complete the puzzle without repeating the numbers (1 to 9) in every row, every column, or in every 3x3 box. Easy, right?" +
                '\n' +
                '\n' +
                Colors.BOLD + "MENU:" + Colors.DEFAULT + ":" +
                '\n' + "1. How to play." +
                '\n' + "2. Options" +
                '\n' +
                '\n' +
                Colors.BOLD + "OPTIONS:" + Colors.DEFAULT + ":" +
                '\n' + " solvecell + row number + col number -- gives you any cell you choose (ex: solvecell -> 3 9)." +
                '\n' + " solve -- gives you the Sudoku (ex: solve)." +
                '\n' + " restart -- restarts the puzzle you're trying to figure out. (ex: restart)" +
                '\n' + " switch + difficulty -- sets a new puzzle in whatever difficulty you want. (ex: switch hard)" +
                '\n' + " leave -- the game ends without the solution being revealed. (ex: leave)" +
                '\n' +
                '\n' +
                Colors.BOLD + "LEVELS" + Colors.DEFAULT + ":" +
                '\n' + " Easy -- 40 cells available" +
                '\n' + " Medium -- 34 cells available" +
                '\n' + " Hard -- 27 cells available" +
                '\n' + " Expert -- 20 cells available" +
                '\n' +
                '\n' +
                "Select your level: ");
    }


    private void userInputs() throws InterruptedException {

        String play = scanner.nextLine().trim();

        if (play.matches("\\d+ \\d+ \\d+")) {
            try {
                String[] par = play.split(" ");
                int row = Integer.parseInt(par[0]) - 1; // Conversion to index 0-8
                int col = Integer.parseInt(par[1]) - 1; // Conversion to index 0-8
                int num = Integer.parseInt(par[2]); // Value to be assigned

                board.updateCell(row, col, num);
                board.printEditable();
            } catch (UpdateCellException e) {
                System.out.println(e.getMessage());
                board.printEditable();
            }
            return;
        }


        Options option = Options.valueOf(play.toUpperCase());
        switch (option) {

            case SOLVE -> {
                board.printOriginal();
                System.out.println("Sudoku solved... But not by you:( " + '\n' + "Again? Y/N");
                String again = scanner.nextLine().trim();

                if (again.equalsIgnoreCase("Y")) {
                    System.out.println("Yes, let's go " + name + "! Choose your difficulty:");
                    String difficulty = scanner.nextLine().toUpperCase().trim();
                    board = new Board(Difficulty.valueOf(difficulty));
                    return;
                }
                System.out.println("Thank you " + name + "! See you next time :)");
                System.exit(1);
            }

            case SOLVECELL -> {
                System.out.println("Which cell would you like to be revealed?");
                String solveCell = scanner.nextLine().trim();

                try {
                    String[] parts = solveCell.split(" ");
                    int row = Integer.parseInt(parts[0]) - 1; // Conversion to index 0-8
                    int col = Integer.parseInt(parts[1]) - 1;
                    int num = board.revealCell(row, col);

                    board.updateCell(row, col, num);
                    board.markNotEditableCells();
                    board.printEditable();
                } catch (UpdateCellException e) {
                    System.out.println(e.getMessage());
                }

            }

            case LEAVE -> {
                System.out.println("Do you want to quit the game? Y/N");
                String inputExit = scanner.nextLine().trim();
                if (inputExit.equalsIgnoreCase("Y")) {
                    System.out.println("Thank you " + name + "! See you next time!");
                    System.exit(1);
                }
            }

            case RESTART -> {
                System.out.println("Restarting the board..." );
                Thread.sleep(1006);
                board.reset();
                board.printEditable();
            }

            case SWITCH -> {
                System.out.println("Choose new difficulty: ");
                String difficulty = scanner.nextLine().toUpperCase().trim();
                board = new Board(Difficulty.valueOf(difficulty));
            }

        }

    }
}