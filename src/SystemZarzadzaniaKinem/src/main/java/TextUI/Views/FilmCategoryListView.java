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
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
            }
        });
        FilmCategoryController filmCategoryController = new FilmCategoryController();
        List<FilmCategory> filmCategories = filmCategoryController.getAll();
        panel.setLayoutManager(new GridLayout(1));
        panel.addComponent(new Label("Wybierz kategori?? filmu:"));

        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
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
        panel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        if(MenuView.getInstance().getUser().isPermission())
        {
            panel.addComponent(new Button("Dodaj kategori??", new Runnable() {
                @Override
                public void run() {
                    window.close();
                    AddFilmCategoryView addFilmCategoryView = AddFilmCategoryView.getInstance();
                    addFilmCategoryView.init();
                }
            }));
        }

        panel.addComponent(exit);
        window.setTitle("Kategorie Film??w");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
