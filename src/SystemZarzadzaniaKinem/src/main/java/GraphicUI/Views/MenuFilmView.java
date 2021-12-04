package GraphicUI.Views;

import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuFilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public MenuFilmView(User user) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;

        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new BorderLayout());
        this.addKeyListener(this);
        this.setVisible(true);
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                requestFocus();
            }
        });
        JLabel mainLabel = new JLabel("Wybierz film z listy:");
        mainLabel.setFont(SettingsService.GenerateFont());
        mainLabel.setBorder(SettingsService.Border());
        this.add(mainLabel,BorderLayout.NORTH);

        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();

        filmList = filmList.stream().sorted(Comparator.comparing(Film::getTitle)).collect(Collectors.toList());

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);


        for(Film film:filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.setFont(SettingsService.GenerateFont());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmView menuView = new FilmView(user,film,this);
                    frame.add(menuView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
                }
            });
            simpleGridPanel.add(temp);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
