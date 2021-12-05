package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Controllers.ReservationController;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.*;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;

public class ReservationSeansView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Seans seans;
    Object previous;
    public ReservationSeansView(User user, Seans seans, Object previous) {
//        System.out.println("ReservationSeansView  ReservationSeansView");
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.seans = seans;
        this.previous = previous;
        this.setMinimumSize(new Dimension(400,300));

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

        RoomsController roomsController = new RoomsController();
        SeansController seansController = new SeansController();
        List<Seat> seatList = seans.getSeatList();
        Integer rows = seatList.get(seatList.size()-1).getRow();
        Reservation reservation = new Reservation();
//        JPanel mainContainer = new JPanel(new BorderLayout());
        this.setLayout(new BorderLayout());

        JPanel seatsInRoom = new JPanel(new GridLayout(0,rows));
        this.add(seatsInRoom,BorderLayout.CENTER);
//        this.setLayout(new GridLayout(0,rows));//TDO
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(false);
        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CinemaController cinemaController = new CinemaController();
                Room room = roomsController.getById(seans.getRoomId());
                Cinema cinema = cinemaController.getById(room.getCinemaId());

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,previous);
                frame.add(reservationSeansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationSeansListView.requestFocus();
            }
        });

        for(Seat seat: seatList) {
            if (seat.isReserved()) {
                if (user.isPermission() && seat.isReserved()) {
                    JButton temp = new JButton("[*]");
                    temp.setFont(SettingsService.GenerateFont());
                    temp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Reservation tempres = null;
                            seat.setReserved(false);
                            seans.setSeatList(seatList);
                            seansController.update(seans);
                            ReservationController reservationController = new ReservationController();
                            List<Reservation> reservationList = reservationController.getReservationBySeansId(seans.getId());
                            for(Reservation item: reservationList) {
                                if(item.getSeatId().equals(seat.getId())) {
                                    tempres = item;
                                }
                            }
                            reservationController.delete(tempres);
                            CinemaController cinemaController = new CinemaController();
                            Cinema cinema = cinemaController.getById(seans.getCinemaId());

                            JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                            frame.remove(panel);
                            ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,this);
                            frame.add(reservationSeansListView, BorderLayout.CENTER);
                            frame.revalidate();
                            frame.repaint();
                            reservationSeansListView.requestFocus();
                        }
                    });

                    seatsInRoom.add(temp);
                }
                else {
                    JButton temp = new JButton("[*]");
                    temp.setFont(SettingsService.GenerateFont());
                    temp.setEnabled(false);
                    seatsInRoom.add(temp);
                }
            }
            else {
                JButton temp = new JButton("[ ]");
                temp.setFont(SettingsService.GenerateFont());
                temp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reservation.setSeansId(seans.getId());
                        reservation.setUserId(user.getId());
                        reservation.setSeatId(seat.getId());
                        reservation.setPaid(checkBox.isSelected());
                        reservation.setDateOfReservation(seans.getDate());
                        reservation.setDateOfCreation(new Date());
                        seat.setReserved(true);
                        seans.setSeatList(seatList);
                        seansController.update(seans);
                        ReservationController reservationController = new ReservationController();
                        reservationController.createNew(reservation);
                        CinemaController cinemaController = new CinemaController();
                        Cinema cinema = cinemaController.getById(seans.getCinemaId());

                        JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                        frame.remove(panel);
                        ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,this);
                        frame.add(reservationSeansListView, BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                        reservationSeansListView.requestFocus();
                    }
                });

                seatsInRoom.add(temp);
            }
        }


        JPanel footer = new JPanel(new BorderLayout());
        this.add(footer,BorderLayout.SOUTH);


        if(user.isPermission()) {
            JLabel paid = new JLabel("Czy opłacona");
            paid.setFont(SettingsService.GenerateFont());
            JPanel checkboxPanel = new JPanel(new BorderLayout());
            checkboxPanel.add(paid,BorderLayout.EAST);
            checkboxPanel.add(checkBox,BorderLayout.WEST);
            checkBox.setFont(SettingsService.GenerateFont());
//            this.add(paid);
//            this.add(checkBox);

            footer.add(checkboxPanel,BorderLayout.WEST);
        }

        footer.add(exit,BorderLayout.EAST);
        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                RoomsController roomsController = new RoomsController();
                CinemaController cinemaController = new CinemaController();
                Room room = roomsController.getById(seans.getRoomId());
                Cinema cinema = cinemaController.getById(room.getCinemaId());

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,previous);
                frame.add(reservationSeansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationSeansListView.requestFocus();
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
