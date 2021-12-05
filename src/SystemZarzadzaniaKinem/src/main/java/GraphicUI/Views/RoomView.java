package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Controllers.RoomsController;
import Back.Models.Room;
import Back.Models.Seat;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RoomView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Room room;
    public RoomView(User user, Room room) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.room = room;
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

        List<Seat> seatList = room.getSeatList();
        JLabel name = new JLabel(room.getName());
        JLabel rows = new JLabel(String.valueOf(seatList.get(seatList.size()-1).getRow()));
        JLabel howManySeats = new JLabel(String.valueOf(seatList.size()));

        JButton exit = new JButton("Wstecz");
        JButton seanses = new JButton("Seanse");
        JButton remove = new JButton("Usuń");

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                roomsController.delete(room);
                CinemaController cinemaController = new CinemaController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomListView roomListView = new RoomListView(user,cinemaController.getById(room.getCinemaId()));
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
            }
        });
        seanses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,room);
                frame.add(seansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CinemaController cinemaController = new CinemaController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomListView roomListView = new RoomListView(user,cinemaController.getById(room.getCinemaId()));
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
            }
        });

        this.add(new JLabel("Nazwa"));
        this.add(name);

        this.add(new JLabel("Liczba rzędów"));
        this.add(rows);

        this.add(new JLabel("Liczba siedzeń"));
        this.add(howManySeats);

        if(user.isPermission()) {
            this.add(new JLabel(""));
            this.add(remove);
        }

        this.add(new JLabel(""));
        this.add(seanses);

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
                CinemaController cinemaController = new CinemaController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomListView roomListView = new RoomListView(user,cinemaController.getById(room.getCinemaId()));
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
