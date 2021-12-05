package TextUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterView {
    private static RegisterView instance = null;

    private RegisterView(){}

    public static RegisterView getInstance() {
        if(instance==null) instance = new RegisterView();
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener {

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
                    LoginView loginView = LoginView.getInstance();
                    loginView.init();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(){
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        TextBox name = new TextBox();
        TextBox password = new TextBox();
        TextBox email = new TextBox();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                LoginView loginView = LoginView.getInstance();
                loginView.init();
            }
        });
        Button accept = new Button("Akceptuj", new Runnable() {
            @Override
            public void run() {
                window.close();
                UsersController usersController = new UsersController();
                User newUser = new User();
                newUser.setName(name.getText());
                newUser.setEmail(email.getText());
                newUser.setPasswordHash(password.getText());
                newUser.setRegistrationDate(new Date());
                newUser.setPermission(false);
                usersController.createNew(newUser);
                MenuView menuView = MenuView.getInstance();
                menuView.init(newUser);
            }
        });

        panel.addComponent(new Label("Nazwa użytkownika"));
        panel.addComponent(name);

        panel.addComponent(new Label("E-mail"));
        panel.addComponent(email);

        panel.addComponent(new Label("Hasło"));
        panel.addComponent(password);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(accept);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle("Rejestracja");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
