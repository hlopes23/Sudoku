import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

    public final class Field {

    private static final String MAIN_TITTLE = "S U D O K U";
    private static final String OBSTACLE_STRING = "✿";
    private static final String SNAKE_BODY_STRING = ".";
    private static final String SNAKE_HEAD_STRING = "o";
    private static final String SNAKE_DEATH = " G A M E   O V E R ";
    private static final String SIZE = "SNAKE SIZE: ";

    private static int width;
    private static int height;
    private static Screen screen;
    private static ScreenWriter screenWriter;

    private Field() {
    }

   /* public static void init(int width, int height) {

        screen = TerminalFacade.createScreen();

        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        screen.refresh();
    }*/
}
