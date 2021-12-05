package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CinemaListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public CinemaListView(User user) {
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
        JButton addCinema = new JButton("Dodaj kino");

        addCinema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddCinemaView addCinemaView = new AddCinemaView(user);
                frame.add(addCinemaView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addCinemaView.requestFocus();
            }
        });

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();


        if (user.isPermission()) this.add(new JLabel("Wybierz kino lub utwórz nowe:"));
        else this.add(new JLabel("Wybierz kino:"),BorderLayout.NORTH);

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        cinemas = cinemas.stream().sorted(Comparator.comparing(Cinema::getName)).collect(Collectors.toList());

        for(Cinema cinema: cinemas) {
                JButton temp = new JButton(cinema.getName());
                temp.setBorder(new EmptyBorder(30, 30, 30, 30));
                temp.setFont(SettingsService.GenerateFont());
                temp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                        frame.remove(panel);
                        CinemaView cinemaView = new CinemaView(user, cinema);
                        frame.add(cinemaView/*, BorderLayout.CENTER*/);
                        frame.revalidate();
                        frame.repaint();
                        cinemaView.requestFocus();
                    }
                });
                simpleGridPanel.add(temp);
        }

        addCinema.setFont(SettingsService.GenerateFont());
        addCinema.setBorder(new EmptyBorder(20, 20, 20, 20));

        if (user.isPermission()) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BorderLayout());
            jPanel.setBorder( BorderFactory.createEmptyBorder(20,0,0,0) );
            jPanel.add(addCinema,BorderLayout.SOUTH);
            this.add(jPanel,BorderLayout.SOUTH);



             /*jPanel.setLayout(new BorderLayout());
            JPanel emptyPanel = new JPanel();
//            emptyPanel.set(new Dimension(1,200));*/
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
