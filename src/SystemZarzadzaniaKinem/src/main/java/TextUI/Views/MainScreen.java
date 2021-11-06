package TextUI.Views;

import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class MainScreen {
    private static MainScreen instance;
    private Screen screen;
    private Panel panel;
    private CreditsWindow internalWindow;

    private MainScreen () {}

    public static MainScreen getInstance() {
        if(instance==null) instance = new MainScreen();
        return instance;
    }

    public void init(Terminal terminal) throws IOException {
        screen = new TerminalScreen(terminal);
        screen.startScreen();
    }

    @SneakyThrows
    public void modify() {
        LoginView loginView = LoginView.getInstance();
        loginView.init();
    }
}
