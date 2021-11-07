package TextUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FilmCategoryView {
    private static FilmCategoryView instance = null;

    private FilmCategoryView(){}

    public static FilmCategoryView getInstance() {
        if(instance==null) instance = new FilmCategoryView();
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
                    FilmCategoryListView filmCategoryListView = FilmCategoryListView.getInstance();
                    filmCategoryListView.init();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(FilmCategory filmCategory){
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
                FilmCategoryListView filmCategoryListView = FilmCategoryListView.getInstance();
                filmCategoryListView.init();
            }
        });
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(new Label("wybierz kategorię filmu:"));
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        FilmCategoryController filmCategoryController = new FilmCategoryController();
        List<Film> filmList = filmCategoryController.getCategoryFilms(filmCategory.getId());
        for(Film film:filmList)
        {
            panel.addComponent(new Button(film.getTitle(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    FilmView filmView = FilmView.getInstance();
                    filmView.init(film, instance);
                }
            }));
        }
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(new Button("Dodaj film", new Runnable() {
            @Override
            public void run() {
                window.close();
                AddFilmView addFilmView = AddFilmView.getInstance();
                addFilmView.init(filmCategory);
            }
        }));
        panel.addComponent(exit);


        window.setTitle(filmCategory.getName());
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
