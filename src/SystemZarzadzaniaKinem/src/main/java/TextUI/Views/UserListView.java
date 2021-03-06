package TextUI.Views;

import Back.Controllers.RoomsController;
import Back.Controllers.UsersController;
import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserListView {
    private static UserListView instance = null;

    private UserListView(){}

    public static UserListView getInstance() {
        if(instance==null) instance = new UserListView();
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener {

        @Override
        public void onResized(Window window, TerminalSize terminalSize, TerminalSize terminalSize1) {
            System.out.println("onResized");
        }

        @Override
        public void onMoved(Window window, TerminalPosition terminalPosition, TerminalPosition terminalPosition1) {
            System.out.println("onMoved");
        }

        @SneakyThrows
        @Override
        public void onInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {
            switch (keyStroke.getKeyType()){
                case Escape:
                    window.close();
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
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
            }
        });
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(new Label("U??ytkownicy systemu: "));
        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        UsersController usersController = new UsersController();
        List<User> userList = usersController.getAll();
        for(User user : userList) {
            panel.addComponent(new Button(user.getName(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    UserView userView = UserView.getInstance();
                    userView.init(user,instance);
                }
            }));
        }
        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        panel.addComponent(new Button("Dodaj u??ytkownika", new Runnable() {
            @Override
            public void run() {
                window.close();
                AddUserView addUserView = AddUserView.getInstance();
                addUserView.init();
            }
        }));
        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        panel.addComponent(exit);

        window.setTitle("U??ytkownicy");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
