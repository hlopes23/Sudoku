# Sudoku Console Game

A Java-based console Sudoku game where players can solve puzzles of varying difficulty, get hints, and interact with the board using simple commands.

## Features

- 9x9 Sudoku board with four difficulty levels: Easy, Medium, Hard, Expert
- Interactive command-line interface
- Commands for solving the board, revealing cells, restarting, switching difficulty, and quitting
- Input validation and helpful prompts

## How to Play

1. **Run the application**:  
   Compile and run `Main.java`.

2. **Enter your name** when prompted.

3. **Choose a difficulty**:  
   - Easy (40 cells filled)
   - Medium (34 cells filled)
   - Hard (27 cells filled)
   - Expert (20 cells filled)

4. **Make a move**:  
   Enter your move as:Example:5. **Available commands**:
      - `solvecell` — Reveal a specific cell (e.g., `solvecell` then `3 9`)
      - `solve` — Show the solution
      - `restart` — Restart the current puzzle
      - `switch` — Start a new puzzle with a chosen difficulty
      - `leave` — Quit the game
   
   ## Requirements
   
   - Java 8 or higher
   
   ## Project Structure
   
   - `src/Main.java` — Entry point
   - `src/Game.java` — Game logic and user interaction
   - `src/Board.java` — Board representation and operations
   - `src/Difficulty.java` — Difficulty levels
   - `src/Commands.java` — Supported commands
   
## License

This project is for educational purposes.