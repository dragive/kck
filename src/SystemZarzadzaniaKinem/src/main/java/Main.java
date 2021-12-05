import Back.Models.Film;
import Back.Services.FileDataBaseService;
import GraphicUI.MainFrame;
import TextUI.MainLaterna;
import lombok.SneakyThrows;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args)
    {
        //MainLaterna.main(args);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
