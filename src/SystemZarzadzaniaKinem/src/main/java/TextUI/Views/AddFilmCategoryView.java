package TextUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.FilmCategory;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddFilmCategoryView {
    private static AddFilmCategoryView instance = null;

    private AddFilmCategoryView(){}

    public static AddFilmCategoryView getInstance() {
        if(instance==null) instance = new AddFilmCategoryView();
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
                    FilmCategoryListView filmCategoryListView = FilmCategoryListView.getInstance();
                    filmCategoryListView.init();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void init(){
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        FilmCategory filmCategory = new FilmCategory();
        TextBox name = new TextBox();

        panel.addComponent(new Label("Nazwa kategorii"));
        panel.addComponent(name);
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(new Button("Zatwierdź", new Runnable() {
            @Override
            public void run() {
                window.close();
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                filmCategory.setName(name.getText());
                filmCategory.setFilmList(new ArrayList<>());
                filmCategoryController.createNew(filmCategory);
                FilmCategoryListView filmCategoryListView = FilmCategoryListView.getInstance();
                filmCategoryListView.init();
            }
        }));

        window.setTitle("");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
