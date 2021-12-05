package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.Reservation;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationCinemaListView {
    private static ReservationCinemaListView instance = null;

    private ReservationCinemaListView(){}

    public static ReservationCinemaListView getInstance() {
        if(instance==null) instance = new ReservationCinemaListView();
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
        panel.addComponent(new Label("Wybierz kino by móc umieścić rezerwację\nw jednym z jego seansów:"));
        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
            }
        });

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();

        for(Cinema cinema: cinemas) {
            panel.addComponent(new Button(cinema.getName(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    ReservationSeansListView reservationSeansListView = ReservationSeansListView.getInstance();
                    reservationSeansListView.init(cinema,instance);
                }
            }));
        }

        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        panel.addComponent(exit);
        window.setTitle("Wybierz kino");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
