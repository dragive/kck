package TextUI.Views;

import Back.Controllers.*;
import Back.Models.*;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationSeansView {
    private static ReservationSeansView instance = null;
    private Seans seans;
    private ReservationSeansView(){}

    public static ReservationSeansView getInstance() {
        if(instance==null) instance = new ReservationSeansView();
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

                    CinemaController cinemaController = new CinemaController();
                    RoomsController roomsController = new RoomsController();
                    Room room = roomsController.getById(seans.getRoomId());
                    Cinema cinema = cinemaController.getById(room.getCinemaId());
                    ReservationSeansListView reservationSeansListView = ReservationSeansListView.getInstance();
                    reservationSeansListView.init(cinema,instance);
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
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        RoomsController roomsController = new RoomsController();
        SeansController seansController = new SeansController();
        Room room = roomsController.getById(seans.getRoomId());
        List<Seat> seatList = roomsController.getSeatsByRoomId(seans.getRoomId());
        Integer rows = seatList.get(seatList.size()-1).getRow();
        Integer cols = seatList.size()/rows;
        Reservation reservation = new Reservation();
        panel.setLayoutManager(new GridLayout(cols));

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                panel.addComponent(new Button("[*]", new Runnable() {
                    @Override
                    public void run() {
                        reservation.setSeansId(seans.getId());
                        MenuView menuView = MenuView.getInstance();
                        User user = menuView.getUser();
                        reservation.setUserId(user.getId());
                        ReservationController reservationController = new ReservationController();
                        reservationController.createNew(reservation);
                        window.close();
                    }
                }));
            }
        }

        window.setTitle("Rezerwacja");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
