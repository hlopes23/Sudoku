    import java.util.Random;

    public class Board {
        private final static int BOARD_SIZE = 9;
        private final static int SUB_BOARD_SIZE = 3;
        private final int [][] originalBoard;
        private int[][] editableBoard;
        private boolean[][] notEditableBoard;



        public Board(Difficulty difficulty) {
            originalBoard = new int[BOARD_SIZE][BOARD_SIZE];
            editableBoard = new int[BOARD_SIZE][BOARD_SIZE];
            notEditableBoard = new boolean[BOARD_SIZE][BOARD_SIZE];

            boolean isFilled = fillBoard(editableBoard, originalBoard);

            if (isFilled) {
                remove(editableBoard, difficulty);
                markNotEditableCells();
                printEditable();
            }
        }


        private boolean fillBoard(int[][]editableBoard, int[][]originalBoard) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (originalBoard[row][col] == 0) {
                        for (int i = 1; i <= BOARD_SIZE; i++) { // number to try
                            int number = generatedNumbers();
                            if (validPlacement(originalBoard, row, col, number)) {
                                originalBoard[row][col] = number;
                                editableBoard[row][col] = number;


                                if (fillBoard(editableBoard, originalBoard)) {   // Recursive function
                                    return true;
                                } else {
                                    originalBoard[row][col] = 0; // Backtrack
                                }
                            }
                        }
                        return false; // if none of the numbers are valid,
                    }
                }
            }
            return true;
        }


        private void markNotEditableCells(){
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (editableBoard[row][col] != 0) {
                        notEditableBoard[row][col] = true;
                    }
                }
            }
        }



        private boolean validPlacement(int[][] editableBoard, int row, int col, int number) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if(editableBoard[row][c] == number) {
                    return false;
                }
            }

            for (int r = 0; r < BOARD_SIZE; r++) {
                if (editableBoard[r][col] == number) {
                    return false;
                }
            }

            int subRow = row - (row % SUB_BOARD_SIZE);
            int subCol = col - (col % SUB_BOARD_SIZE);
            for (int r = subRow; r < subRow + SUB_BOARD_SIZE; r++) {
                for (int c = subCol; c < subCol + SUB_BOARD_SIZE; c++) {
                    if (editableBoard[r][c] == number) {
                        return false;
                    }
                }
            }
            return true;
        }



        private void remove(int editable[][], Difficulty difficulty) {
            int filledCells = 81;

            while (filledCells > difficulty.getNumberOfFilledCells()) {
                int randomRow = new Random().nextInt(9);
                int randomColumn = new Random().nextInt(9);
                if (editable[randomRow][randomColumn] != 0) {
                    editable[randomRow][randomColumn] = 0;
                    filledCells--;
                }
            }
        }


        private int generatedNumbers() {
            return new Random().nextInt(1, 10);
        }


        public void printEditable() {
            for (int row = 0; row < BOARD_SIZE; row++) {
                if ((row % SUB_BOARD_SIZE == 0) && (row != 0)) {
                    System.out.println("\u001B[30m" + "- - - - - - - - - - -");
                }
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if ((col % SUB_BOARD_SIZE == 0) && (col != 0)) {
                        System.out.print("\u001B[30m" + "| ");
                    }
                    final int cellValue = editableBoard[row][col];

                    if (cellValue == 0){
                        System.out.print(" ");
                    } else{
                        System.out.print("\u001B[32m" + cellValue);
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        }


        public void printOriginal() {
            for (int row = 0; row < BOARD_SIZE; row++) {
                if ((row % SUB_BOARD_SIZE == 0) && (row != 0)) {
                    System.out.println("\u001B[30m" + "- - - - - - - - - - -");
                }
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if ((col % SUB_BOARD_SIZE == 0) && (col != 0)) {
                        System.out.print("\u001B[30m" + "| ");
                    }
                    final int cellValue = originalBoard[row][col];

                    if (cellValue == 0){
                        System.out.print(" ");

                    } else{
                        System.out.print("\u001B[32m" + cellValue);
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        }



        public void updateCell(int row, int col, int num) throws UpdateCellException {

            if ((num < 1 || num > 9)
                    || !(row >= 0 && row <= 8)
                    || !(col >= 0 && col <= 8)) {
                throw new UpdateCellException("\u001B[31m" + "Aaaah, almost there. Remember: only numbers between 1 and 9; rows and columns also between 1 and 9.");
            }


            if (notEditableBoard[row][col]){
                throw new UpdateCellException("\u001B[31m" + "This cell cannot be edited. Please try another cell.");
            }

            editableBoard[row][col] = num;

        }


        public int revealCell(int row, int col) {
            return originalBoard[row][col];
        }
    }