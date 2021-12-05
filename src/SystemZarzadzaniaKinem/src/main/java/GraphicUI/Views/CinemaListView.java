package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class CinemaListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public CinemaListView(User user) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,1));
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
                frame.setTitle("Dodaj kino");
            }
        });

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();


        if (user.isPermission()) this.add(new JLabel("Wybierz kino lub utwórz nowe:"));
        else this.add(new JLabel("Wybierz kino:"));

        for(Cinema cinema: cinemas) {
            JButton temp = new JButton(cinema.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user, cinema);
                    frame.add(cinemaView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
                    frame.setTitle("Kino");
                }
            });
            this.add(temp);
        }

        this.add(new JLabel(""));

        if (user.isPermission()) this.add(addCinema);
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
