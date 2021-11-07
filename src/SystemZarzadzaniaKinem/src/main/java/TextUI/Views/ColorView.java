package TextUI.Views;
import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ColorView {
    private static ColorView instance = null;

    private ColorView(){}

    public static ColorView getInstance() {
        if(instance==null) instance = new ColorView();
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
                    OptionView optionView = OptionView.getInstance();
                    optionView.init();
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
        panel.setLayoutManager(new GridLayout(1));
        TextColor.ANSI[] colors = TextColor.ANSI.values();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
                OptionView optionView = OptionView.getInstance();
                optionView.init();
            }
        });
        for (TextColor.ANSI color: colors)
        {
            panel.addComponent(new Button(color.name(), new Runnable() {
                @Override
                public void run() {
                }
            }));
        }
        panel.addComponent(new Button("Wyjdź", new Runnable() {
            @Override
            public void run() {
                window.close();
                OptionView optionView = OptionView.getInstance();
                optionView.init();
            }
        }));
        panel.addComponent(exit);

        window.setTitle("Wybierz kolor");
        window.setComponent(panel);
        gui.addWindow(window);
    }
}
