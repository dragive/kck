package TextUI.Views;

import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Film;
import Back.Models.Room;
import Back.Models.Seans;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddSeansView {
    private static AddSeansView instance = null;
    private Room room;
    private AddSeansView(){}

    public static AddSeansView getInstance() {
        if(instance==null) instance = new AddSeansView();
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
                    RoomView roomView = RoomView.getInstance();
                    roomView.init(room);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Room room){
        this.room = room;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        Seans seans = new Seans();
        TextBox date = new TextBox();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Button button = new Button("Wybierz film", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                seans.setSeatList(room.getSeatList());
                seans.setRoomId(room.getId());
                seans.setDate(simpleDateFormat.parse(date.getText()));
                SeansAddFilmView seansAddFilmView = SeansAddFilmView.getInstance();
                seansAddFilmView.init(seans);
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                RoomView roomView = RoomView.getInstance();
                roomView.init(room);
            }
        });

        panel.addComponent(new Label("Data seansu (dd-mm-rrrr hh:mm)"));
        panel.addComponent(date);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(button);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle("Dodaj seans");
        window.setComponent(panel);
        gui.addWindow(window);
    }

    public void init(Seans seans, Film film) {
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        TextBox date = new TextBox(String.valueOf(simpleDateFormat.format(seans.getDate())));
        Button button = new Button("Wybierz film", new Runnable() {
            @Override
            public void run() {
                window.close();
                SeansAddFilmView seansAddFilmView = SeansAddFilmView.getInstance();
                seansAddFilmView.init(seans);
            }
        });
        Button accept = new Button("Zatwierdź", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                RoomsController roomsController = new RoomsController();
                SeansController seansController = new SeansController();
                room = roomsController.getById(seans.getRoomId());
                seans.setDate(simpleDateFormat.parse(date.getText()));
                seans.setFilmId(film.getId());
                seans.setCinemaId(room.getCinemaId());
                seansController.createNew(seans);
                window.close();
                RoomView roomView = RoomView.getInstance();
                roomView.init(room);
            }
        });

        if(!date.equals(""))
        {
            panel.addComponent(new Label(simpleDateFormat.format(seans.getDate())));
        }
        else panel.addComponent(new Label("Data seansu (dd-mm-rrrr hh:mm)"));
        panel.addComponent(date);

        panel.addComponent(accept);
        panel.addComponent(button);

        window.setTitle("Dodaj seans");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
