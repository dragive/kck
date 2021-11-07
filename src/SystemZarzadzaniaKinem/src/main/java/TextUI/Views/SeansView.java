package TextUI.Views;

import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Models.Room;
import Back.Models.Seans;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SeansView {
    private static SeansView instance = null;
    private Seans seans;
    private SeansView(){}

    public static SeansView getInstance() {
        if(instance==null) instance = new SeansView();
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
                    SeansListView seansListView = SeansListView.getInstance();
                    seansListView.init(roomsController.getById(seans.getRoomId()));
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
                SeansListView seansListView = SeansListView.getInstance();
                seansListView.init(roomsController.getById(seans.getRoomId()));
            }
        });
        panel.setLayoutManager(new GridLayout(2));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Label date = new Label(simpleDateFormat.format(seans.getDate()));
        FilmController filmController = new FilmController();
        Label film = new Label(filmController.getById(seans.getFilmId()).getTitle());

        panel.addComponent(new Label("Data seansu (dd-mm-rrrr hh:mm)"));
        panel.addComponent(date);

        panel.addComponent(new Label("Tytuł filmu"));
        panel.addComponent(film);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle("Seans");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
