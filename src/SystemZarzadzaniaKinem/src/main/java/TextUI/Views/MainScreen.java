package TextUI.Views;

import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Arrays;

@Getter
@Setter
public class MainScreen {
    private static MainScreen instance;
    private Screen screen;
    private Panel panel;
    private Window internalWindow;
    private boolean internalWindowShown;

    private MainScreen () {
        internalWindowShown = false;
    }

    public static MainScreen getInstance() {
        if(instance==null) instance = new MainScreen();
        return instance;
    }

    public void init(Terminal terminal) throws IOException {
        screen = new TerminalScreen(terminal);
        screen.startScreen();
    }

    public void modify() {

        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("CZESIO'S CINEMA MANAGER"));
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(new Label("Login"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("Password"));
        panel.addComponent(new TextBox().setMask('*'));

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(new Button("Sign in", new Runnable() {
            @Override
            public void run() {
                internalWindow = new BasicWindow();
                internalWindow.setFixedSize(new TerminalSize(20,5));
                internalWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
                Panel internalPanel = new Panel();
                internalPanel.setLayoutManager(new LinearLayout());
                internalPanel.addComponent(new Label("Krzysiu tu byl"));
                internalWindow.setComponent(internalPanel);
                internalWindowShown = true;
                gui.addWindow(internalWindow);
            }
        }));

        BasicWindow window = new BasicWindow();

        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);

        gui.addWindow(window);
    }

    public void closeResponseWindow()
    {
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        internalWindowShown = false;
        gui.removeWindow(internalWindow);
    }

    public void Stop() throws IOException {
        screen.refresh();
        screen.close();
    }
}
