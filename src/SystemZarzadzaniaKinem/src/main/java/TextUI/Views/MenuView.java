package TextUI.Views;

import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@Setter
@Getter
public class MenuView {
    private static MenuView instance = null;
    private User user;
    private BasicWindow window;
    private MenuView(){}

    public static MenuView getInstance() {
        if(instance==null) instance = new MenuView();
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
                    MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
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

    @SneakyThrows
    public void init(User user) {
        this.user = user;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();

        Label userWelcomeMessage = new Label("Witaj " + user.getName());
        Button filmcategories = new Button("Kategorie filmów", new Runnable() {
            @Override
            public void run() {
                FilmCategoryListView filmCategoryListView = FilmCategoryListView.getInstance();
                filmCategoryListView.init();
            }
        });
        Button cinemas = new Button("Kina", new Runnable() {
            @Override
            public void run() {
                CinemaListView cinemaListView = CinemaListView.getInstance();
                cinemaListView.init();
            }
        });
        Button films = new Button("Filmy", new Runnable() {
            @Override
            public void run() {
                MenuFilmView menuFilmView = MenuFilmView.getInstance();
                menuFilmView.init();
            }
        });
        Button userButton = new Button("Użytkownik", new Runnable() {
            @Override
            public void run() {
                UserView userView = UserView.getInstance();
                userView.init(user,instance);
            }
        });
        Button users = new Button("Użytkownikcy", new Runnable() {
            @Override
            public void run() {
                UserListView userListView = UserListView.getInstance();
                userListView.init();
            }
        });
        Button credits = new Button("Twórcy", new Runnable() {
            @Override
            public void run() {
                CreditsWindow creditsWindow = CreditsWindow.getInstance();
                creditsWindow.init();
            }
        });
        Button reservation = new Button("Zarezerwuj", new Runnable() {
            @Override
            public void run() {
                ReservationCinemaListView reservationCinemaListView = ReservationCinemaListView.getInstance();
                reservationCinemaListView.init();
            }
        });
        Button exit = new Button("Wyloguj", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                LoginView loginView = LoginView.getInstance();
                loginView.init();
            }
        });

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(userWelcomeMessage);
        panel.addComponent(new EmptySpace(new TerminalSize(0,1)));
        panel.addComponent(cinemas);
        panel.addComponent(filmcategories);
        panel.addComponent(films);
        panel.addComponent(reservation);
        panel.addComponent(userButton);
        panel.addComponent(users);
        panel.addComponent(credits);
        panel.addComponent(exit);


        window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setFixedSize(new TerminalSize(20,10));
        window.setHints(Arrays.asList(Window.Hint.CENTERED,Window.Hint.FIXED_SIZE));
        window.setComponent(panel);
        window.setTitle("Menu");
        gui.addWindow(window);
    }
}
