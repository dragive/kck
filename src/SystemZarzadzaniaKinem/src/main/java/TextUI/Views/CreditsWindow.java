package TextUI.Views;

import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreditsWindow {
    private static CreditsWindow instance = null;

    private CreditsWindow(){}

    public static CreditsWindow getInstance() {
        if(instance==null) {
            instance = new CreditsWindow();
        }
        return instance;
    }

    protected class KeyStrokeListener implements WindowListener {

        @Override
        public void onResized(Window window, TerminalSize terminalSize, TerminalSize terminalSize1) {

        }

        @Override
        public void onMoved(Window window, TerminalPosition terminalPosition, TerminalPosition terminalPosition1) {

        }

        @Override
        public void onInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {
            switch (keyStroke.getKeyType()) {
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

    public void init(){
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        BasicWindow window = new BasicWindow();
        window.setTitle("Twórcy");
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setFixedSize(new TerminalSize(40,7));
        window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FIXED_SIZE));
        Panel internalPanel = new Panel();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();

            }
        });
        internalPanel.setLayoutManager(new LinearLayout());
        internalPanel.addComponent(new Label("Autorzy Projektu:"));
        internalPanel.addComponent(new EmptySpace(new TerminalSize(1,1)));
//        internalPanel.addComponent(new Label("Kozackie chlopaki"));
        internalPanel.addComponent(new Label("Maciek Fender"));
        internalPanel.addComponent(new Label("Kacper Chrost"));
        internalPanel.addComponent(new Label("Krzysztof Funkowski"));
        internalPanel.addComponent(new EmptySpace(new TerminalSize(1,1)));
        internalPanel.addComponent(exit);
        window.setComponent(internalPanel);
        gui.addWindow(window);
    }
}
