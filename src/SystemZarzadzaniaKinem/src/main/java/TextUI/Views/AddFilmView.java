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
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddFilmView {
    private static AddFilmView instance = null;
    private FilmCategory filmCategory;
    private AddFilmView(){}

    public static AddFilmView getInstance() {
        if(instance==null) instance = new AddFilmView();
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
                    FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                    filmCategoryView.init(filmCategory);
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
        this.filmCategory = filmCategory;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        TextBox title = new TextBox();
        TextBox desc = new TextBox();
        TextBox date = new TextBox();
        Film film = new Film();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                filmCategoryView.init(filmCategory);
            }
        });
        Button accept = new Button("Zatwierdź", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                window.close();

                film.setFilmCategoryId(filmCategory.getId());
                film.setTitle(title.getText());
                film.setDescription(desc.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                film.setReleaseDate(simpleDateFormat.parse(date.getText()));

                List<Film> filmList = filmCategory.getFilmList();
                if(filmList.size()==0) {
                    film.setId(1);
                }
                else film.setId(filmList.get(filmList.size()-1).getId()+1);

                filmList.add(film);
                filmCategoryController.update(filmCategory);

                FilmController filmController = new FilmController();
                filmController.createNew(film);

                FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                filmCategoryView.init(filmCategory);
            }
        });

        panel.setLayoutManager(new GridLayout(2));
        panel.addComponent(new Label("Tytuł"));
        panel.addComponent(title);

        panel.addComponent(new Label("Opis"));
        panel.addComponent(desc);

        panel.addComponent(new Label("Data (DD-MM-RRRR)"));
        panel.addComponent(date);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(accept);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);


        window.setTitle("Dodaj film");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
