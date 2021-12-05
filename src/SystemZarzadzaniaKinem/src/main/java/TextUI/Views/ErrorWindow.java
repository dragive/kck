package TextUI.Views;

import TextUI.MultiWindowTextExtendedGUI;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ErrorWindow {
    private String message;
    private Window window;
    private Panel panel;
    MultiWindowTextExtendedGUI gui;

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
                default:
                    window.close();
                    break;
            }
        }

        @Override
        public void onUnhandledInput(Window window, KeyStroke keyStroke, AtomicBoolean atomicBoolean) {

        }
    }

    public void ShowErrorMessage(String message) {
        MultiWindowTextExtendedGUI gui = MultiWindowTextExtendedGUI.getInstance();
        window = new BasicWindow();
        KeyStrokeListener keyStrokeListener = new KeyStrokeListener();
        window.addWindowListener(keyStrokeListener);
        window.setFixedSize(new TerminalSize(20,5));
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        Panel internalPanel = new Panel();
        Button exit = new Button("Wstecz", new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                window.close();
            }
        });
        internalPanel.setLayoutManager(new LinearLayout());
        internalPanel.addComponent(new Label(message));
        internalPanel.addComponent(exit);
        window.setComponent(internalPanel);
        gui.addWindow(window);
    }
}
