package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        exit.setFont(SettingsService.GenerateFont());
        JButton room = new JButton("Sale kinowe");
        room.setFont(SettingsService.GenerateFont());
        JButton delete = new JButton("Usuń kino");
        delete.setFont(SettingsService.GenerateFont());
        JButton reservation = new JButton("Zarezerwuj");
        reservation.setFont(SettingsService.GenerateFont());
        JLabel name = new JLabel(cinema.getName());
        name.setFont(SettingsService.GenerateFont());
        JTextArea desc = new JTextArea(cinema.getDescription());
        desc.setEditable(false);
        desc.setFont(SettingsService.GenerateFont());

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
            }
        });

        JPanel main = new JPanel(new BorderLayout());

        JPanel labels = new JPanel(new GridLayout(0,1));
        main.add(labels,BorderLayout.NORTH);

        JPanel buttons = new JPanel(new BorderLayout());
        main.add(buttons);

        this.add(main);


        JLabel selectEverything = new JLabel("Wybierz salę lub rezerwazję w tym kinie:");
        selectEverything.setFont(SettingsService.GenerateFont());
        labels.add(selectEverything);


        JPanel nameP = new JPanel(new GridLayout(0,2));
        JLabel nameOfCinemaL = new JLabel("Nazwa kina:");
        nameOfCinemaL.setFont(SettingsService.GenerateFont());
        nameP.add(nameOfCinemaL);
        nameP.add(name);
        labels.add(nameP);

        JPanel descP = new JPanel(new GridLayout(0,2));
        JLabel descOfCinema = new JLabel("Opis kina:");
        descOfCinema.setFont(SettingsService.GenerateFont());
        descP.add(descOfCinema);
        descP.add(new JScrollPane(desc));
        labels.add(descP);


        JPanel buttonsGrid = new JPanel(new GridLayout(0,2));
        if(user.isPermission()) {
            delete.setBorder(SettingsService.Border());
            buttonsGrid.add(delete);
        }
        else {
            buttonsGrid.add(new JPanel());
        }
        buttonsGrid.add(room);
        room.setBorder(SettingsService.Border());
        
        buttonsGrid.add(exit);
        exit.setBorder(SettingsService.Border());

        buttonsGrid.add(reservation);
        reservation.setBorder(SettingsService.Border());


        buttons.add(buttonsGrid,BorderLayout.SOUTH);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
            frame.remove(panel);
            CinemaListView cinemaListView = new CinemaListView(user);
            frame.add(cinemaListView, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            cinemaListView.requestFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
