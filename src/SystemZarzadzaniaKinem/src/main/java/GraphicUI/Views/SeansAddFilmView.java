package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Models.Film;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SeansAddFilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Seans seans;
    public SeansAddFilmView(User user, Seans seans) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.seans = seans;
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
        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();

        JButton exit = new JButton("Wstecz");
        exit.setBorder(SettingsService.Border());
        exit.setFont(SettingsService.GenerateFont());

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddSeansView seansView = new AddSeansView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansView.requestFocus();
                frame.setTitle("Dodaj seans");
            }
        });

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);


        for(Film film: filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    AddSeansView seansView = new AddSeansView(user,seans,film);
                    frame.add(seansView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    seansView.requestFocus();
                    frame.setTitle("Podgląd");
                }
            });
            simpleGridPanel.add(temp);
        }
        JPanel footer = new JPanel(new BorderLayout());
        this.add(footer,BorderLayout.SOUTH);

        footer.add(exit,BorderLayout.EAST);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddSeansView seansView = new AddSeansView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansView.requestFocus();
                break;
            case 122:
                JFrame frame2 = (JFrame) SwingUtilities.windowForComponent(panel);
                System.out.println(frame2.getExtendedState());
                if(frame2.getExtendedState()==0) {
                    frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame2.setVisible(true);
                }
                else {
                    frame2.setExtendedState(JFrame.DO_NOTHING_ON_CLOSE);
                    frame2.setVisible(true);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
