package TextUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public class EditUserView {
    private static EditUserView instance = null;
    private Object previousWindow;
    private User user;
    private EditUserView(){}

    public static EditUserView getInstance() {
        if(instance==null) instance = new EditUserView();
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
                    if(previousWindow instanceof UserView) {
                        window.close();
                        UserView userView = UserView.getInstance();
                        userView.init(user,instance);
                    }
                    else if(previousWindow instanceof UserListView) {
                        window.close();
                        UserListView userListView = UserListView.getInstance();
                        userListView.init();
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(User user, Object previousWindow){
        this.previousWindow = previousWindow;
        this.user = user;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        TextBox name = new TextBox(user.getName());
        MenuView menuView = MenuView.getInstance();
        TextBox email = new TextBox(user.getEmail());
        Button delete = new Button("Usun konto", new Runnable() {
            @Override
            public void run() {
                window.close();
                UsersController usersController = new UsersController();
                usersController.delete(user);
                if(previousWindow instanceof UserListView) {
                    UserListView userListView = UserListView.getInstance();
                    userListView.init();
                }
                else {
                    MenuView menuView = MenuView.getInstance();
                    menuView.getWindow().close();
                    LoginView loginView = LoginView.getInstance();
                    loginView.init();
                }
            }
        });
        CheckBox checkBox = new CheckBox();
        checkBox.setChecked(user.isPermission());
        Button accept = new Button("Zatwierdź", new Runnable() {
            @Override
            public void run() {
                window.close();
                UsersController usersController = new UsersController();
                user.setName(name.getText());
                user.setEmail(email.getText());
                user.setPermission(checkBox.isChecked());
                usersController.update(user);
                if(previousWindow instanceof UserListView) {
                    UserListView userListView = UserListView.getInstance();
                    userListView.init();
                }
                else if(previousWindow instanceof EditUserView) {
                    UserView userView = UserView.getInstance();
                    userView.init(user,instance);
                }
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                UserView userView = UserView.getInstance();
                userView.init(user,instance);
            }
        });

        panel.addComponent(new Label("Nazwa użytkownika"));
        panel.addComponent(name);

        panel.addComponent(new Label("Adres e-mail"));
        panel.addComponent(email);

        if(menuView.getUser().isPermission())
        {
            panel.addComponent(new Label("Pracownik"));
            panel.addComponent(checkBox);
        }

        panel.addComponent(accept);
        panel.addComponent(delete);

        window.setTitle(user.getName());
        window.setComponent(panel);
        gui.addWindow(window);
    }

}
