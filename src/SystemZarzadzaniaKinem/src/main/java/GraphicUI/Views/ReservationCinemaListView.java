package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ReservationCinemaListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public ReservationCinemaListView(User user) {
        panel = this;
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

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();

        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
            }
        });

        this.add(new JLabel("Wybierz kino by móc umieścić rezerwację\nw jednym z jego seansów:"));
        this.add(new JLabel(""));

        for(Cinema cinema: cinemas) {
            JButton temp = new JButton(cinema.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,this);
                    frame.add(reservationSeansListView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    reservationSeansListView.requestFocus();
                }
            });

            this.add(temp);
        }

        this.add(new JLabel(""));

        this.add(exit);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
