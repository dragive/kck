package TextUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddCinemaView {
    private static AddCinemaView instance;

    private AddCinemaView(){}

    public static AddCinemaView getInstance() {
        if(instance==null) instance = new AddCinemaView();
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
                    CinemaListView cinemaListView = CinemaListView.getInstance();
                    cinemaListView.init();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init() {
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        Panel panel = new Panel();
        Cinema cinema = new Cinema();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        TextBox name = new TextBox();
        TextBox desc = new TextBox();
        Button accept = new Button("Zaakceptuj", new Runnable() {
            @Override
            public void run() {
                cinema.setCityId(1);
                cinema.setName(name.getText());
                cinema.setDescription(desc.getText());

                CinemaController cinemaController = new CinemaController();
                cinemaController.createNew(cinema);

                window.close();
                CinemaListView cinemaListView = CinemaListView.getInstance();
                cinemaListView.init();
            }
        });

        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setTitle("Dodaj kino");

        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Nazwa"));
        panel.addComponent(name);

        panel.addComponent(new Label("Opis"));
        panel.addComponent(desc);

        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(accept);

        window.setComponent(panel);
        gui.addWindow(window);
    }

}
