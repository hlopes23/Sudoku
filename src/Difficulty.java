public enum Difficulty {

    EASY(41),
    MEDIUM(34),
    HARD(27),
    EXPERT(20);

        private int numberOfFilledCells;

    Difficulty(int numberOfFilledCells){
        this.numberOfFilledCells = numberOfFilledCells;
    }

    public int getNumberOfFilledCells() {
        return numberOfFilledCells;
    }
}