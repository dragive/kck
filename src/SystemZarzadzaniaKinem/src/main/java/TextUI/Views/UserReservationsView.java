package TextUI.Views;

import Back.Controllers.FilmController;
import Back.Controllers.ReservationController;
import Back.Controllers.SeansController;
import Back.Models.Reservation;
import Back.Models.Seans;
import Back.Models.User;
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

public class UserReservationsView {

    private static UserReservationsView instance = null;
    private User user;
    private UserReservationsView(){}

    public static UserReservationsView getInstance() {
        if(instance==null) instance = new UserReservationsView();
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
                    UserView userView = UserView.getInstance();
                    userView.init(user, instance);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(User user){
        this.user = user;
        System.out.println(user.getName());
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
                UserView userView = UserView.getInstance();
                userView.init(user, instance);
            }
        });
        panel.setLayoutManager(new GridLayout(1));
        ReservationController reservationController = new ReservationController();
        List<Reservation> reservationList = reservationController.getReservationByUserId(user.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        FilmController filmController = new FilmController();
        for(Reservation reservation:reservationList)
        {
            if(MenuView.getInstance().getUser().isPermission()) {
                if(user.equals(MenuView.getInstance().getUser())) {
                    if(reservation.getUserId().equals(user.getId())) {
                        SeansController seansController = new SeansController();
                        Seans seans = seansController.getById(reservation.getSeansId());
                        panel.addComponent(new Button(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()), new Runnable() {
                            @Override
                            public void run() {
                                window.close();
                                ReservationView reservationView = ReservationView.getInstance();
                                reservationView.init(user,reservation);
                            }
                        }));
                    }
                }
                else {
                    if(reservation.getUserId().equals(user.getId())) {
                        SeansController seansController = new SeansController();
                        Seans seans = seansController.getById(reservation.getSeansId());
                        panel.addComponent(new Button(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()), new Runnable() {
                            @Override
                            public void run() {
                                window.close();
                                ReservationView reservationView = ReservationView.getInstance();
                                reservationView.init(user,reservation);
                            }
                        }));
                    }
                }
            }
            else {
                if(reservation.getUserId().equals(user.getId())) {
                    SeansController seansController = new SeansController();
                    Seans seans = seansController.getById(reservation.getSeansId());
                    panel.addComponent(new Button(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()), new Runnable() {
                        @Override
                        public void run() {
                            window.close();
                            ReservationView reservationView = ReservationView.getInstance();
                            reservationView.init(user,reservation);
                        }
                    }));
                }
            }
        }
        panel.addComponent(exit);
        window.setTitle("Lista rezerwacji");
        window.setComponent(panel);
        gui.addWindow(window);
    }

}
