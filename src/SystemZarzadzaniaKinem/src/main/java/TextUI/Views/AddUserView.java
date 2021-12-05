package TextUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;


public class AddUserView {
    private static AddUserView instance = null;

    private AddUserView(){}

    public static AddUserView getInstance() {
        if(instance==null) instance = new AddUserView();
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
                    UserListView userListView = UserListView.getInstance();
                    userListView.init();
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
        CheckBox checkBox = new CheckBox();
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
                newUser.setPermission(checkBox.isChecked());
                usersController.createNew(newUser);
                UserListView userListView = UserListView.getInstance();
                userListView.init();
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                UserListView userListView = UserListView.getInstance();
                userListView.init();
            }
        });

        panel.addComponent(new Label("Nazwa użytkownika"));
        panel.addComponent(name);

        panel.addComponent(new Label("E-mail"));
        panel.addComponent(email);

        panel.addComponent(new Label("Hasło"));
        panel.addComponent(password);

        panel.addComponent(new Label("Pracownik"));
        panel.addComponent(checkBox);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(accept);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle("Dodaj użytkownika");
        window.setComponent(panel);
        gui.addWindow(window);
    }

}
