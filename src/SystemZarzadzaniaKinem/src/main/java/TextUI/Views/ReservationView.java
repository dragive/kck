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
import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationView {
    private static ReservationView instance = null;
    private User user;
    private ReservationView(){}

    public static ReservationView getInstance() {
        if(instance==null) instance = new ReservationView();
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
                    UserReservationsView userReservationsView = UserReservationsView.getInstance();
                    userReservationsView.init(user);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(User user, Reservation reservation){
        this.user = user;
        SeansController seansController = new SeansController();
        Seans seans = seansController.getById(reservation.getSeansId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        FilmController filmController = new FilmController();
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        CheckBox checkBox = new CheckBox();
        if(reservation.isPaid()) checkBox.setChecked(true);
        panel.setLayoutManager(new GridLayout(2));
        Button payment = new Button("Zapłać za rezerwację", new Runnable() {
            @Override
            public void run() {

            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                if(checkBox.isChecked()!=reservation.isPaid()){
                    reservation.setPaid(checkBox.isChecked());
                    ReservationController reservationController = new ReservationController();
                    reservationController.update(reservation);
                }
                UserReservationsView userReservationsView = UserReservationsView.getInstance();
                userReservationsView.init(user);
            }
        });

        panel.addComponent(new Label("Idziesz na"));
        panel.addComponent(new Label(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate())));

        panel.addComponent(new Label("Data seansu"));
        panel.addComponent(new Label(simpleDateFormat.format(reservation.getDateOfReservation())));

        panel.addComponent(new Label("Data rezerwacji"));
        panel.addComponent(new Label(simpleDateFormat.format(reservation.getDateOfCreation())));

        if(MenuView.getInstance().getUser().isPermission()) {
            panel.addComponent(new Label("Opłacona"));
            panel.addComponent(checkBox);
        }

        panel.addComponent(exit);

        window.setTitle("Rezerwacja");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
