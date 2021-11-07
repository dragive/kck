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
import java.util.Date;
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
        this.seans = seans;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        RoomsController roomsController = new RoomsController();
        SeansController seansController = new SeansController();
        List<Seat> seatList = seans.getSeatList();
        Integer rows = seatList.get(seatList.size()-1).getRow();
        Reservation reservation = new Reservation();
        panel.setLayoutManager(new GridLayout(rows));
        CheckBox checkBox = new CheckBox();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                CinemaController cinemaController = new CinemaController();
                Room room = roomsController.getById(seans.getRoomId());
                Cinema cinema = cinemaController.getById(room.getCinemaId());
                ReservationSeansListView reservationSeansListView = ReservationSeansListView.getInstance();
                reservationSeansListView.init(cinema,instance);
            }
        });
        for(Seat seat: seatList)
        {
            if(seat.isReserved())
            {
                if(MenuView.getInstance().getUser().isPermission() && seat.isReserved())
                {
                    panel.addComponent(new Button("[*]", new Runnable() {
                        @Override
                        public void run() {
                            seat.setReserved(false);
                            seans.setSeatList(seatList);
                            seansController.update(seans);
                            ReservationController reservationController = new ReservationController();
                            reservationController.delete(reservation);
                            window.close();
                        }
                    }));
                }
                else panel.addComponent(new Button("[*]").setEnabled(false));

            }
            else {
                panel.addComponent(new Button("[ ]", new Runnable() {
                    @Override
                    public void run() {
                        reservation.setSeansId(seans.getId());
                        MenuView menuView = MenuView.getInstance();
                        User user = menuView.getUser();
                        reservation.setUserId(user.getId());
                        reservation.setSeatId(seat.getId());
                        reservation.setPaid(checkBox.isChecked());
                        reservation.setDateOfReservation(seans.getDate());
                        reservation.setDateOfCreation(new Date());
                        seat.setReserved(true);
                        seans.setSeatList(seatList);
                        seansController.update(seans);
                        ReservationController reservationController = new ReservationController();
                        reservationController.createNew(reservation);
                        window.close();
                    }
                }));
            }
        }
        if(MenuView.getInstance().getUser().isPermission())
        {
            panel.addComponent(new Label("Opłacona"));
            panel.addComponent(checkBox);
        }
        panel.addComponent(exit);

        window.setTitle("Rezerwacja");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
