package TextUI;

import TextUI.Views.MainScreen;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainLaterna {
    public static void main(String[] args) throws IOException {

        boolean run = true;
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        MainScreen mainScreen = MainScreen.getInstance();
        mainScreen.init(terminal);
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance(new SeparateTextGUIThread.Factory(), mainScreen.getScreen(), new DefaultWindowManager(), new WindowShadowRenderer(), new EmptySpace(TextColor.ANSI.RED_BRIGHT));

        mainScreen.modify();
        ((AsynchronousTextGUIThread)gui.getGUIThread()).start();

        /*while (run) {
            KeyStroke key = terminal.pollInput();
            if(key!=null) {
                switch (key.getKeyType()) {
                    case Escape:
                        if(mainScreen.isInternalWindowShown()) mainScreen.closeResponseWindow();
                        else run = false;
                        break;
                    default:
                        break;
                }
            }
        }*/


    }
}
