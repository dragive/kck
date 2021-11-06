package TextUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.FilmCategory;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FilmCategoryListView {
    private static FilmCategoryListView instance=null;

    private FilmCategoryListView() {}

    public static FilmCategoryListView getInstance() {
        if(instance==null) instance = new FilmCategoryListView();
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
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel panel = new Panel();
        FilmCategoryController filmCategoryController = new FilmCategoryController();
        List<FilmCategory> filmCategories = filmCategoryController.getAll();
        panel.setLayoutManager(new GridLayout(1));
        for(FilmCategory item: filmCategories)
        {
            panel.addComponent(new Button(item.getName(), new Runnable() {
                @Override
                public void run() {
                    window.close();
                    FilmCategoryView filmCategoryView = FilmCategoryView.getInstance();
                    filmCategoryView.init(item);
                }
            }));
        }
        panel.addComponent(new Button("Dodaj kategorię", new Runnable() {
            @Override
            public void run() {
                window.close();
                AddFilmCategoryView addFilmCategoryView = AddFilmCategoryView.getInstance();
                addFilmCategoryView.init();
            }
        }));
        window.setTitle("Kategorie Filmów");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
