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
import java.util.concurrent.atomic.AtomicBoolean;

public class CinemaView {
    private static CinemaView instance = null;
    private Cinema cinema;
    private CinemaView(){}

    public static CinemaView getInstance() {
        if(instance==null) instance = new CinemaView();
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener {

        @Override
        public void onResized(Window window, TerminalSize terminalSize, TerminalSize terminalSize1) {

        }

        @Override
        public void onMoved(Window window, TerminalPosition terminalPosition, TerminalPosition terminalPosition1) {

        }

        @Override
        public void onInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {
            switch (keyStroke.getKeyType()) {
                case Escape:
                    window.close();
                    CinemaListView cinemaListView = CinemaListView.getInstance();
                    cinemaListView.init();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Cinema cinema) {
        this.cinema = cinema;
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
                CinemaListView cinemaListView = CinemaListView.getInstance();
                cinemaListView.init();
            }
        });
        Button room = new Button("Sale kinowe", new Runnable() {
            @Override
            public void run() {
                window.close();
                RoomListView roomListView = RoomListView.getInstance();
                roomListView.init(cinema);
            }
        });
        Button delete = new Button("Usun kino", new Runnable() {
            @Override
            public void run() {
                CinemaController cinemaController = new CinemaController();
                cinemaController.delete(cinema);
                window.close();
                CinemaListView cinemaListView = CinemaListView.getInstance();
                cinemaListView.init();
            }
        });
        Button reservation = new Button("Zarezerwuj", new Runnable() {
            @Override
            public void run() {
                window.close();
                ReservationSeansListView reservationSeansListView = ReservationSeansListView.getInstance();
                reservationSeansListView.init(cinema,instance);
            }
        });

        window.setTitle(cinema.getName());
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(room);
        panel.addComponent(reservation);
        if (MenuView.getInstance().getUser().isPermission())
        {
            panel.addComponent(delete);
        }
        panel.addComponent(exit);

        window.setTitle(cinema.getName());
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
