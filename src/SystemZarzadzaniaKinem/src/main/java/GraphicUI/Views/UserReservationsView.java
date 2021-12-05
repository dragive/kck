package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.ReservationController;
import Back.Controllers.SeansController;
import Back.Models.Reservation;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserReservationsView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    User panelUser;
    public UserReservationsView(User user,User panelUser) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.panelUser = panelUser;
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

        ReservationController reservationController = new ReservationController();
        List<Reservation> reservationList = reservationController.getReservationByUserId(user.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        FilmController filmController = new FilmController();

        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserView userView = new UserView(user,panelUser,this);
                frame.add(userView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userView.requestFocus();
                frame.setTitle("Twoje konto");
            }
        });

        for(Reservation reservation:reservationList) {
            if(user.isPermission()) {
                if(panelUser.equals(user)) {
                    if(reservation.getUserId().equals(panelUser.getId())) {
                        SeansController seansController = new SeansController();
                        Seans seans = seansController.getById(reservation.getSeansId());
                        JButton temp = new JButton(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()));
                        temp.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                                frame.remove(panel);
                                ReservationView reservationView = new ReservationView(user,panelUser,reservation);
                                frame.add(reservationView, BorderLayout.CENTER);
                                frame.revalidate();
                                frame.repaint();
                                reservationView.requestFocus();
                                frame.setTitle("Twoja rezerwacja");
                            }
                        });
                        this.add(temp);
                    }
                }
                else {
                    if(reservation.getUserId().equals(panelUser.getId())) {
                        SeansController seansController = new SeansController();
                        Seans seans = seansController.getById(reservation.getSeansId());
                        JButton temp = new JButton(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()));
                        temp.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                                frame.remove(panel);
                                ReservationView reservationView = new ReservationView(user,panelUser,reservation);
                                frame.add(reservationView, BorderLayout.CENTER);
                                frame.revalidate();
                                frame.repaint();
                                reservationView.requestFocus();
                                frame.setTitle("Twoja rezerwacja");
                            }
                        });
                        this.add(temp);
                    }
                }
            }
            else {
                if(reservation.getUserId().equals(panelUser.getId())) {
                    SeansController seansController = new SeansController();
                    Seans seans = seansController.getById(reservation.getSeansId());
                    JButton temp = new JButton(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()));
                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                            frame.remove(panel);
                            ReservationView reservationView = new ReservationView(user,panelUser,reservation);
                            frame.add(reservationView, BorderLayout.CENTER);
                            frame.revalidate();
                            frame.repaint();
                            reservationView.requestFocus();
                            frame.setTitle("Twoja rezerwacja");
                        }
                    });
                    this.add(temp);
                }
            }
        }
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
                UserView userView = new UserView(user,panelUser,this);
                frame.add(userView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
