package TextUI.Views;

import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginView {
    private static LoginView instance = null;
    private Panel panel;
    private User user;
    private TextBox login;
    private TextBox password;

    private LoginView(){}

    public static LoginView getInstance() {
        if(instance==null) instance = new LoginView();
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener{

        @Override
        public void onResized(Window window, TerminalSize terminalSize, TerminalSize terminalSize1) {

        }

        @Override
        public void onMoved(Window window, TerminalPosition terminalPosition, TerminalPosition terminalPosition1) {

        }

        @SneakyThrows
        @Override
        public void onInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {
            switch (keyStroke.getKeyType()){
                case Escape:
                    window.close();
                    MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
                    gui.getScreen().stopScreen();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    @SneakyThrows
    public void init()
    {
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        login = new TextBox();
        password = new TextBox();
        login.setSize(new TerminalSize(20,1));
        password.setSize(new TerminalSize(20,1));

        user = new User();
        user.setName("Krzysiek");
        user.setPasswordHash("123");

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("CZESIO'S CINEMA MANAGER"));
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(new Label("Login"));
        panel.addComponent(login);

        panel.addComponent(new Label("Hasło"));
        panel.addComponent(password.setMask('*'));

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(new Button("Zaloguj się", new Runnable() {
            @Override
            public void run() {
                System.out.println(login.getText()+" "+password.getText());
                if(user.getName().equals(login.getText()) && user.getPasswordHash().equals(password.getText())) {
                    window.close();
                    MenuView menuView = MenuView.getInstance();
                    menuView.init(user);
                }
                else {
                    ErrorWindow internalWindow = new ErrorWindow();
                    internalWindow.ShowErrorMessage("Zly login lub haslo");
                }
            }
        }));


        window.setFixedSize(new TerminalSize(60,20));
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
