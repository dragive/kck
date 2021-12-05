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

public class ReservationView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    User panelUser;
    public ReservationView(User user, User panelUser, Reservation reservation) {
        System.out.println("RezervationView");
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
        SeansController seansController = new SeansController();
        Seans seans = seansController.getById(reservation.getSeansId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        FilmController filmController = new FilmController();

        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(reservation.isPaid());
        //System.out.println(checkBox.isSelected()+" "+reservation.isPaid());
        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(checkBox.isSelected()!=reservation.isPaid()){
                    reservation.setPaid(checkBox.isSelected());
                    ReservationController reservationController = new ReservationController();
                    reservationController.update(reservation);
                }
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserReservationsView reservationView = new UserReservationsView(user,panelUser);
                frame.add(reservationView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationView.requestFocus();
            }
        });

        this.add(new JLabel("Idziesz na"));
        this.add(new JLabel(filmController.getById(seans.getFilmId()).getTitle()));

        this.add(new JLabel("Data seansu"));
        this.add(new JLabel(simpleDateFormat.format(reservation.getDateOfReservation())));

        this.add(new JLabel("Data rezerwacji"));
        this.add(new JLabel(simpleDateFormat.format(reservation.getDateOfCreation())));

        if(user.isPermission()) {
            this.add(new JLabel("Opłacona"));
            this.add(checkBox);
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
                UserReservationsView reservationView = new UserReservationsView(user,panelUser);
                frame.add(reservationView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationView.requestFocus();
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
