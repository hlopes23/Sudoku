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
    }

    public void start() throws InterruptedException {

        Difficulty difficulty = Difficulty.valueOf((scanner.nextLine()).toUpperCase().trim());
        board = new Board(difficulty);

        while (true) {
                userInputs();
        }
    }


    private void printIntro(){
        System.out.println(
                '\n' + Colors.YELLOW + Colors.BOLD + "   *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    S U D O K U    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    " + Colors.BOLD + Colors.DEFAULT +
                        '\n' + '\n' + "Player name: ");
    }


    private void printMenu(String name){

        System.out.println('\n' + "Welcome to Sudoku " + Colors.BOLD + name + Colors.DEFAULT + "!" + " " + "💛"+
                '\n' +
                '\n' +
                Colors.BOLD + Colors.YELLOW + "HOW TO PLAY" + Colors.DEFAULT +
                '\n' + " Sudoku is a numeric puzzle set in a 9X9 Grid, and the purpose of this game is to complete the puzzle " +
                '\n' + " without repeating the numbers 1 to 9 in every row, every column, or in every 3x3 box. " + "To play in a " +
                '\n' + " certain cell, just type in the number of the row, the number of the column, and the number you think" +
                '\n' + " it's the best fit, with spaces in between. Like this, for example: " + Colors.GREEN + Colors.ITALIC + "4 2 8" + Colors.DEFAULT + "." + " Easy, right?" +
                '\n' +
                '\n' +
                Colors.BOLD + Colors.YELLOW + "SOME COMMANDS" + Colors.DEFAULT +
                '\n' + Colors.ITALIC + " solvecell " + Colors.DEFAULT + "- gives you any cell you choose (ex: " + Colors.GREEN + Colors.ITALIC + " solvecell " + Colors.DEFAULT + "> " + Colors.GREEN + Colors.ITALIC + "3 9" + Colors.DEFAULT + ")" +
                '\n' + Colors.ITALIC + " solve "  + Colors.DEFAULT + "- gives you the Sudoku." +
                '\n' + Colors.ITALIC + " restart "  + Colors.DEFAULT + "- restarts the puzzle you're trying to figure out." +
                '\n' + Colors.ITALIC + " switch "  + Colors.DEFAULT + "- sets a new puzzle in whatever difficulty you want. (ex:" + Colors.GREEN + Colors.ITALIC + " switch " + Colors.DEFAULT + "> " + Colors.GREEN + Colors.ITALIC + "hard" + Colors.DEFAULT + ")" +
                '\n' + Colors.ITALIC + " leave "  + Colors.DEFAULT + "- the game ends without the solution being revealed." +
                '\n' +
                '\n' +
                Colors.BOLD + Colors.YELLOW + "DIFFICULTY" + Colors.DEFAULT +
                '\n' + " Easy - 40 cells available" +
                '\n' + " Medium - 34 cells available" +
                '\n' + " Hard - 27 cells available" +
                '\n' + " Expert - 20 cells available" +
                '\n' +
                '\n' + "Choose your difficulty:");
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


        Commands command = Commands.valueOf(play.toUpperCase());
        switch (command) {

            case SOLVE -> {
                board.printOriginal();
                System.out.println('\n'+ "Sudoku solved... But not by you 💔 " + '\n' + "Again? Y/N" + '\n');
                String again = scanner.nextLine().trim();

                if (again.equalsIgnoreCase("Y")) {
                    System.out.println('\n' + "Yes, let's go " + name + "! Choose your difficulty:" +'\n');
                    String difficulty = scanner.nextLine().toUpperCase().trim();
                    board = new Board(Difficulty.valueOf(difficulty));
                    return;
                }
                System.out.println('\n' + "Thank you " + name + "! See you next time 👋🏻");
                System.exit(1);
            }

            case SOLVECELL -> {
                System.out.println('\n' + "Which cell would you like to be revealed?" + '\n');
                String solveCell = scanner.nextLine().trim();

                try {
                    String[] parts = solveCell.split(" ");
                    int row = Integer.parseInt(parts[0]) - 1; // Conversion to index 0-8
                    int col = Integer.parseInt(parts[1]) - 1;
                    int num = board.revealCell(row, col);

                    board.updateCell(row, col, num);
                    board.markNotEditableCell(row,col);
                    board.printEditable();
                } catch (UpdateCellException e) {
                    System.out.println(e.getMessage());
                }

            }

            case LEAVE -> {
                System.out.println('\n' + "Do you want to quit the game? Y/N" + '\n');
                String inputExit = scanner.nextLine().trim();
                if (inputExit.equalsIgnoreCase("Y")) {
                    System.out.println('\n' + "Thank you " + name + "! See you next time 👋🏻");
                    System.exit(1);
                }
            }

            case RESTART -> {
                System.out.println('\n' + "Restarting the board..." );
                Thread.sleep(1006);
                board.reset();
                board.printEditable();
            }

            case SWITCH -> {
                System.out.println('\n' + "Choose new difficulty:" + '\n');
                String difficulty = scanner.nextLine().toUpperCase().trim();
                board = new Board(Difficulty.valueOf(difficulty));
            }

        }
    }

}