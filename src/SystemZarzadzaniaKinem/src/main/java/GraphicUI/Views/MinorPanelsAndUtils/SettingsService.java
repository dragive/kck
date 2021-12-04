package GraphicUI.Views.MinorPanelsAndUtils;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class SettingsService {
    public static final Integer FONT_SIZE = 22;
    public static Font GenerateFont(){
        return new Font("Dialog", Font.BOLD ,FONT_SIZE);
    }
    public static EmptyBorder Border(){
        return new EmptyBorder(30, 30, 30, 30);
    }
}
