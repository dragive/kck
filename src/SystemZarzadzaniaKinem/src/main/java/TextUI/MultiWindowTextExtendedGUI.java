package TextUI;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;

public class MultiWindowTextExtendedGUI extends MultiWindowTextGUI {
    private static MultiWindowTextExtendedGUI instance;

    private MultiWindowTextExtendedGUI(TextGUIThreadFactory guiThreadFactory, Screen screen, WindowManager windowManager, WindowPostRenderer postRenderer, Component background) {
        super(guiThreadFactory, screen, windowManager, postRenderer, background);
    }

    public static MultiWindowTextExtendedGUI getInstance(TextGUIThreadFactory guiThreadFactory, Screen screen, WindowManager windowManager, WindowPostRenderer postRenderer, Component background) {
        if(instance==null) {
            instance = new MultiWindowTextExtendedGUI(guiThreadFactory, screen, windowManager, postRenderer, background);
        }
        return instance;
    }

    public static MultiWindowTextExtendedGUI getInstance() {
        return instance;
    }
}
