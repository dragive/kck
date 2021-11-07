package TextUI.Views;

import Back.Controllers.CinemaController;
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
import java.util.concurrent.atomic.AtomicBoolean;


public class UserView {
    private static UserView instance = null;
    private Object previousWindow;
    private User user;
    private UserView(){}

    public static UserView getInstance() {
        if(instance==null) instance = new UserView();
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
                    if(previousWindow instanceof MenuView) {
                        window.close();
                    }
                    else if(previousWindow instanceof UserListView) {
                        window.close();
                        UserListView userListView = UserListView.getInstance();
                        userListView.init();
                    }
                    else if(previousWindow instanceof EditUserView) {
                        window.close();
                    }
                    else if(previousWindow instanceof UserReservationsView) {
                        window.close();
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
        Object previous;
        if(previousWindow instanceof UserListView) previous = previousWindow;
        else previous = instance;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        panel.addComponent(new Label("Dane użytkownika:"));
        panel.addComponent(new EmptySpace(new TerminalSize(2,1)));
        Label name = new Label(user.getName());
        Label email = new Label(user.getEmail());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Label date = new Label(simpleDateFormat.format(user.getRegistrationDate()));
        Button edit = new Button("Edytuj dane", new Runnable() {
            @Override
            public void run() {
                window.close();
                EditUserView editUserView = EditUserView.getInstance();
                editUserView.init(user,previous);
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
            }
        });
        Button reservations = new Button("Rezerwacje", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                UserReservationsView reservationsView = UserReservationsView.getInstance();
                reservationsView.init(user);
            }
        });

        panel.addComponent(new Label("Nazwa użytkownika"));
        panel.addComponent(name);

        panel.addComponent(new Label("Adres e-mail"));
        panel.addComponent(email);

        panel.addComponent(new Label("Data założenia"));
        panel.addComponent(date);

        panel.addComponent(new Label("Rezerwacje"));
        panel.addComponent(reservations);


        if(user.isPermission())
        {
            panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
            panel.addComponent(new Label("Pracownik"));
        }

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(edit);

        window.setTitle(user.getName());
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
