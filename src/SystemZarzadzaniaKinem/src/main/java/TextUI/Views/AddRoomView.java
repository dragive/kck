package TextUI.Views;

import Back.Controllers.RoomsController;
import Back.Models.Cinema;
import Back.Models.Room;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;



public class AddRoomView {
    private static AddRoomView instance = null;
    private Cinema cinema;
    private AddRoomView(){}

    public static AddRoomView getInstance() {
        if(instance==null) instance = new AddRoomView();
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
                    RoomListView roomListView = RoomListView.getInstance();
                    roomListView.init(cinema);
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
        panel.setLayoutManager(new GridLayout(2));
        TextBox name = new TextBox();
        TextBox rows = new TextBox();
        TextBox cols = new TextBox();
        Room room = new Room();
        Button button = new Button("Dodaj", new Runnable() {
            @Override
            public void run() {
                RoomsController roomsController = new RoomsController();
                room.setCinemaId(cinema.getId());
                room.setName(name.getText());
                room.setSeatList(roomsController.FillRoom(Integer.parseInt(rows.getText()),Integer.parseInt(cols.getText())));
                roomsController.createNew(room);
                window.close();
                RoomListView roomListView = RoomListView.getInstance();
                roomListView.init(cinema);
            }
        });
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                RoomListView roomListView = RoomListView.getInstance();
                roomListView.init(cinema);
            }
        });

        panel.addComponent(new Label("Nazwa"));
        panel.addComponent(name);

        panel.addComponent(new Label("Liczba siedzeń w rzędzie"));
        panel.addComponent(rows);

        panel.addComponent(new Label("Liczba rzędów"));
        panel.addComponent(cols);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(button);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(exit);

        window.setTitle("Dodaj salę kinową");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
