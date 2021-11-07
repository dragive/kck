package TextUI.Views;

import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.FilmCategory;
import Back.Models.Room;
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


public class SeansListView {
    private static SeansListView instance = null;
    private Room room;
    private SeansListView(){}

    public static SeansListView getInstance() {
        if(instance==null) instance = new SeansListView();
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
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                RoomView roomView = RoomView.getInstance();
                roomView.init(room);
            }
        });
        panel.setLayoutManager(new GridLayout(1));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByRoomId(room.getId());
        for(Seans item: seansList)
        {
            panel.addComponent(new Button(filmController.getById(item.getFilmId()).getTitle() + " " +simpleDateFormat.format(item.getDate()), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    SeansView seansView = SeansView.getInstance();
                    seansView.init(item);
                }
            }));
        }
        if(MenuView.getInstance().getUser().isPermission())
        {
            panel.addComponent(new Button("Dodaj seans", new Runnable() {
                @Override
                public void run() {
                    window.close();
                    AddSeansView addSeansView = AddSeansView.getInstance();
                    addSeansView.init(room);
                }
            }));
        }

        panel.addComponent(exit);
        window.setTitle(room.getName() + " - Lista seansów");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
