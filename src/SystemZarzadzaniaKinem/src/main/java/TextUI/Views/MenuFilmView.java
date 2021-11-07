package TextUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuFilmView {
    private static MenuFilmView instance = null;

    private MenuFilmView(){}

    public static MenuFilmView getInstance() {
        if(instance==null) instance = new MenuFilmView();
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
        panel.setLayoutManager(new GridLayout(1));
        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();
        for(Film film:filmList)
        {
            panel.addComponent(new Button(film.getTitle(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    FilmView filmView = FilmView.getInstance();
                    filmView.init(film);
                }
            }));
        }

        window.setTitle("Filmy");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
