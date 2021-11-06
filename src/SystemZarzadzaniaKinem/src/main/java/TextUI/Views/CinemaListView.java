package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CinemaListView {
    private static CinemaListView instance;

    private CinemaListView(){}

    public static CinemaListView getInstance() {
        if(instance==null) instance = new CinemaListView();
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener{

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
        Panel panel = new Panel();

        Button addCinema = new Button("Dodaj kino", new Runnable() {
            @Override
            public void run() {
                window.close();
                AddCinemaView addCinemaView = AddCinemaView.getInstance();
                addCinemaView.init();
            }
        });

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();

        panel.setLayoutManager(new GridLayout(1));
        for(Cinema cinema: cinemas) {
            panel.addComponent(new Button(cinema.getName(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    CinemaView cinemaView = CinemaView.getInstance();
                    cinemaView.init(cinema);
                }
            }));
        }
        panel.addComponent(addCinema);

        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setComponent(panel);
        window.setTitle("Kina");
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        gui.addWindow(window);
    }
}
