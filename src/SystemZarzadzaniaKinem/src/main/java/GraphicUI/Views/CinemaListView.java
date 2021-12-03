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
            for(int i = 0; i<100; i++){
                JButton temp = new JButton(cinema.getName()+ i);
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
        }

//        this.add(new JLabel(""));
        addCinema.setFont(SettingsService.GenerateFont());
        addCinema.setBorder(new EmptyBorder(20, 20, 20, 20));
        
//        addCinema.setBorderPainted(true);
        if (user.isPermission()) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BorderLayout());
//            JPanel emptyPanel = new JPanel();
            jPanel.setBorder( BorderFactory.createEmptyBorder(20,0,0,0) );
//            jPanel.add(emptyPanel,BorderLayout.NORTH);
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
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
