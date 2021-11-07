package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
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

public class RoomView {
    private static RoomView instance = null;
    private Room room;
    private RoomView(){}

    public static RoomView getInstance() {
        if(instance==null) instance = new RoomView();
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
                    RoomListView roomListView = RoomListView.getInstance();
                    roomListView.init(cinemaController.getById(room.getCinemaId()));
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
        this.room=room;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        List<Seat> seatList = room.getSeatList();
        Label name = new Label(room.getName());
        panel.setLayoutManager(new GridLayout(2));
        Label rows = new Label(String.valueOf(seatList.get(seatList.size()-1).getRow()));
        Label howManySeats = new Label(String.valueOf(seatList.size()));
        Button seanses = new Button("Seanse", new Runnable() {
            @Override
            public void run() {
                window.close();
                SeansListView seansListView = SeansListView.getInstance();
                seansListView.init(room);
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                CinemaController cinemaController = new CinemaController();
                RoomListView roomListView = RoomListView.getInstance();
                roomListView.init(cinemaController.getById(room.getCinemaId()));
            }
        });
        Button remove = new Button("Usuń", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                RoomsController roomsController = new RoomsController();
                roomsController.delete(room);
            }
        });

        panel.addComponent(new Label("Nazwa"));
        panel.addComponent(name);

        panel.addComponent(new Label("Liczba rzędów"));
        panel.addComponent(rows);

        panel.addComponent(new Label("Liczba siedzeń"));
        panel.addComponent(howManySeats);

        if (MenuView.getInstance().getUser().isPermission())
        {
            panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
            panel.addComponent(remove);
        }

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(seanses);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle(room.getName());
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
