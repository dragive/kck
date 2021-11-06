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

public class MenuView {
    private static MenuView instance = null;

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
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();

        Label userWelcomeMessage = new Label("Witaj " + user.getName());
        Button films = new Button("Filmy", new Runnable() {
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
        Button credits = new Button("Twórcy", new Runnable() {
            @Override
            public void run() {
                CreditsWindow creditsWindow = CreditsWindow.getInstance();
                creditsWindow.init();
            }
        });

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(userWelcomeMessage);
        panel.addComponent(new EmptySpace(new TerminalSize(0,1)));
        panel.addComponent(cinemas);
        panel.addComponent(films);
        panel.addComponent(credits);



        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setFixedSize(new TerminalSize(20,10));
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);
        window.setTitle("Menu");
        gui.addWindow(window);
    }
}
