package TextUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FilmView {
    private static FilmView instance = null;
    private Film film;

    private FilmView(){}

    public static FilmView getInstance() {
        if(instance==null) instance = new FilmView();
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
                    FilmCategoryController filmCategoryController = new FilmCategoryController();
                    FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                    filmCategoryView.init(filmCategoryController.getById(film.getFilmCategoryId()));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Film film){
        this.film = film;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        Button delete = new Button("Usuń", new Runnable() {
            @Override
            public void run() {
                window.close();
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                FilmCategory filmCategory = filmCategoryController.getById(film.getFilmCategoryId());
                List<Film> filmList = filmCategory.getFilmList();
                filmList.remove(film);
                filmCategory.setFilmList(filmList);
                filmCategoryController.update(filmCategory);
                FilmController filmController = new FilmController();
                filmController.delete(film);
                FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                filmCategoryView.init(filmCategory);
            }
        });

        panel.setLayoutManager(new GridLayout(2));
        panel.addComponent(new Label("Tytuł"));
        panel.addComponent(new Label(film.getTitle()));

        panel.addComponent(new Label("Opis"));
        panel.addComponent(new Label(film.getDescription()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        panel.addComponent(new Label("Data (DD-MM-RRRR)"));
        panel.addComponent(new Label(simpleDateFormat.format(film.getReleaseDate())));

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(delete);

        window.setTitle(film.getTitle());
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
