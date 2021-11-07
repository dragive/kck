package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Models.Film;
import Back.Models.Seans;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class SeansAddFilmView {
    private static SeansAddFilmView instance = null;
    private Seans seans;
    private SeansAddFilmView(){}

    public static SeansAddFilmView getInstance() {
        if(instance==null) instance = new SeansAddFilmView();
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
                    RoomsController roomsController = new RoomsController();
                    AddSeansView addSeansView = AddSeansView.getInstance();
                    addSeansView.init(roomsController.getById(seans.getRoomId()));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Seans seans){
        this.seans = seans;
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
                RoomsController roomsController = new RoomsController();
                AddSeansView addSeansView = AddSeansView.getInstance();
                addSeansView.init(roomsController.getById(seans.getRoomId()));
            }
        });
        panel.setLayoutManager(new GridLayout(1));
        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();
        for(Film film: filmList) {
            panel.addComponent(new Button(film.getTitle(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    AddSeansView addSeansView = AddSeansView.getInstance();
                    addSeansView.init(seans,film);
                }
            }));
        }
        panel.addComponent(exit);

        window.setTitle("Filmy");
        window.setComponent(panel);
        gui.addWindow(window);
    }

}
