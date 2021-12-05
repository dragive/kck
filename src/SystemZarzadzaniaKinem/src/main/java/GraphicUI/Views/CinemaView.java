package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CinemaView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public CinemaView(User user, Cinema cinema) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;

        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,2));
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

        JButton exit = new JButton("Wstecz");
        JButton room = new JButton("Sale kinowe");
        JButton delete = new JButton("Usuń kino");
        JButton reservation = new JButton("Zarezerwuj");
        JLabel name = new JLabel(cinema.getName());
        JLabel desc = new JLabel(cinema.getDescription());

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CinemaController cinemaController = new CinemaController();
                cinemaController.delete(cinema);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
                frame.setTitle("Lista kin");
            }
        });
        reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,this);
                frame.add(reservationSeansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationSeansListView.requestFocus();
                frame.setTitle("Rezerwacja");
            }
        });
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomListView roomListView = new RoomListView(user,cinema);
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
                frame.setTitle("Sale");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
                frame.setTitle("Lista kin");
            }
        });

        this.add(new JLabel("Wybierz salę lub rezerwazję w tym kinie:"));
        this.add(new JLabel(""));

        this.add(new JLabel(""));this.add(new JLabel(""));

        this.add(new JLabel("Nazwa kina:"));
        this.add(name);

        this.add(new JLabel("Opis kina:"));
        this.add(desc);

        this.add(new JLabel(""));
        this.add(room);
        this.add(new JLabel(""));
        this.add(reservation);

        if(user.isPermission()) {
            this.add(new JLabel(""));
            this.add(delete);
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
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
