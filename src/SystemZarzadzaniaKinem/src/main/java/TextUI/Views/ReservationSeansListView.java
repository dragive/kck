package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Cinema;
import Back.Models.Reservation;
import Back.Models.Seans;
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

public class ReservationSeansListView {
    private static ReservationSeansListView instance = null;
    private Object previous;
    private Cinema cinema;
    private ReservationSeansListView(){}

    public static ReservationSeansListView getInstance() {
        if(instance==null) instance = new ReservationSeansListView();
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
                    if(previous instanceof ReservationCinemaListView)
                    {
                        ReservationCinemaListView reservationCinemaListView = ReservationCinemaListView.getInstance();
                        reservationCinemaListView.init();
                    }
                    else if(previous instanceof CinemaView)
                    {
                        CinemaView cinemaView = CinemaView.getInstance();
                        cinemaView.init(cinema);
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Cinema cinema, Object previous){
        this.cinema = cinema;
        this.previous = previous;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByCinema(cinema.getId());

        for(Seans seans: seansList) {
            panel.addComponent(new Button(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()), new Runnable() {
                @Override
                public void run() {
                    ReservationSeansView reservationSeansView = ReservationSeansView.getInstance();
                    reservationSeansView.init(seans);
                }
            }));
        }

        window.setTitle("Wybierz seans");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}