public enum Options {

    SOLVECELL("Gives the player any cell."),
    SOLVE("Gives player the Sudoku."),
    RESTART("Restarts the ongoing puzzle."),
    SWITCH ("Switches to a new puzzle."),
    LEAVE ("The player decided to end the game.");

    private String description;

    private Options(String description){
        this.description = description;
    }
}