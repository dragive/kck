package GraphicUI.Views;

import Back.Controllers.FilmController;
import Back.Controllers.SeansController;
import Back.Models.Cinema;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReservationSeansListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    private Object previous;
    private Cinema cinema;
    public ReservationSeansListView(User user,Cinema cinema, Object previous) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.cinema = cinema;
        this.previous = previous;
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

        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(previous.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                    frame.add(reservationCinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationCinemaListView.requestFocus();
                    frame.setTitle("Rezerwacja"); //sprawdzić
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user,cinema);
                    frame.add(cinemaView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
                    frame.setTitle("Kino");
                }
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByCinema(cinema.getId());

        for(Seans seans: seansList) {
            JButton temp = new JButton(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()));
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationSeansView reservationSeansView = new ReservationSeansView(user,seans,this);
                    frame.add(reservationSeansView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationSeansView.requestFocus();
                    frame.setTitle("Podgląd sali");
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
                if(previous.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                    frame.add(reservationCinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationCinemaListView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user,cinema);
                    frame.add(cinemaView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
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
