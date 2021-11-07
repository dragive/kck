package TextUI.Views;

import Back.Controllers.RoomsController;
import Back.Controllers.UsersController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.User;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoomListView {
    private static RoomListView instance = null;
    private Cinema cinema;
    private RoomListView(){}

    public static RoomListView getInstance() {
        if(instance==null) instance = new RoomListView();
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
                    CinemaView cinemaView = CinemaView.getInstance();
                    cinemaView.init(cinema);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(Cinema cinema){
        this.cinema = cinema;
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        RoomsController roomsController = new RoomsController();
        List<Room> roomList = roomsController.getByCinemaId(cinema.getId());
        for(Room room : roomList) {
            panel.addComponent(new Button(room.getName(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    RoomView roomView = RoomView.getInstance();
                    roomView.init(room);
                }
            }));
        }
        panel.addComponent(new Button("Dodaj salę kinową", new Runnable() {
            @Override
            public void run() {
                window.close();
                AddRoomView addRoomView = AddRoomView.getInstance();
                addRoomView.init(cinema);
            }
        }));

        window.setTitle("Sale kinowe");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
