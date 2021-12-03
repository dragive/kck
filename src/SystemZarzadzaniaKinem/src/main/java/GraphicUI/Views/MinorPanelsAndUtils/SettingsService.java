package GraphicUI.Views.MinorPanelsAndUtils;

import java.awt.*;

public abstract class SettingsService {
    public static final Integer FONT_SIZE = 22;
    public static Font GenerateFont(){
        return new Font("Dialog", Font.BOLD ,FONT_SIZE);
    }
}
