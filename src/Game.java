import java.util.Scanner;

public class Game {

    public Game() {

        System.out.println(
                '\n' + "\u001B[33m" + "  *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    S U D O K U    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    " +
                        '\n' + '\n' + "\u001B[30m" + "Please insert player's name: ");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Welcome to Sudoku " + name + "!" +
                '\n' +
                '\n' + "If this is your first time playing Sudoku, you must know that Sudoku is a numeric puzzle set in a 9X9 Grid, and the purpose of this" +
                '\n' + "game is to complete the puzzle without repeating the numbers (1 to 9) in every row, every column, or in every 3x3 box. Easy, right?" +
                '\n' + "Don't worry, if you're feeling stuck and that you could use some guidance," +
                '\n' + "you can check the list bellow to help you solve this puzzle. But try not to get used to it!" +
                '\n' +
                '\n' +
                "OPTIONS: " +
                '\n' + "-> solvecell + row number + col number -- gives you any cell you choose (ex: solvecell 3 9)." +
                '\n' + "-> solve -- gives you the Sudoku (ex: solve)." +
                '\n' + "-> restart -- restarts the puzzle you're trying to figure out. (ex: restart)" +
                '\n' + "-> switch + difficulty -- sets a new puzzle in whatever difficulty you want. (ex: switch hard)" +
                '\n' + "-> leave -- the game ends without the solution being revealed. (ex: leave)" +
                '\n' +
                '\n' +
                "LEVELS:" +
                '\n' + "-> Easy -- 40 cells available" +
                '\n' + "-> Medium -- 34 cells available" +
                '\n' + "-> Hard -- 27 cells available" +
                '\n' + "-> Expert -- 20 cells available" +
                '\n' +
                '\n' +
                "Select your level: ");


        Difficulty difficulty = Difficulty.valueOf((scanner.next()).toUpperCase());
        System.out.println("\u001B[30m" + "Good luck " + name + "!" + '\n');
        Board board = new Board(difficulty);
        System.out.println("");
        Scanner input = new Scanner(System.in);


        while (true) {

            String play = input.nextLine();

            //solve();
            if (play.equalsIgnoreCase(Options.SOLVE.toString().trim())) {
                System.out.println("\u001B[30m" + "Sudoku solved... But not by you:( " + '\n' + "Let's go again? Press <enter>!" + '\n');
                board.printOriginal();
                System.out.println(" ");
                continue;
            }


            //solveCell();

            if (play.equalsIgnoreCase(Options.SOLVECELL.toString().trim())) {
                System.out.println("\u001B[30m" + "Which cell would you like to be revealed?" + '\n');


                String[] parts = play.split(" ");
                int row = Integer.parseInt(parts[0]) - 1; // Conversion to index 0-8
                int col = Integer.parseInt(parts[1]) - 1;
                int num = board.revealCell(row, col);

                try{
                    board.updateCell(row, col, num);
                } catch (UpdateCellException e) {
                    System.out.println(e.getMessage());
                }
                board.printEditable();
                System.out.println(" ");
                continue;
            }




            if (play.equalsIgnoreCase(Options.LEAVE.toString().trim())) {
                System.out.println("\u001B[30m" + "Do you want to quit game? Y/N");
                String inputExit = scanner.nextLine();
                if (inputExit.equalsIgnoreCase("Y")) {
                    System.out.println("\u001B[30m" + "See you next time!");
                    break;
                }
                continue;
            }


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
}