package GraphicUI.Views;
import Back.Controllers.RoomsController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RoomListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Cinema cinema;
    public RoomListView(User user, Cinema cinema) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.cinema = cinema;
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

        JButton addRoom = new JButton("Dodaj salę kinową");
        JButton exit = new JButton("Wstecz");

        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddRoomView addRoomView = new AddRoomView(user,cinema);
                frame.add(addRoomView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addRoomView.requestFocus();
                frame.setTitle("Dodaj salę");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaView cinemaView = new CinemaView(user,cinema);
                frame.add(cinemaView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaView.requestFocus();
                frame.setTitle("Kino");
            }
        });
        RoomsController roomsController = new RoomsController();
        List<Room> roomList = roomsController.getByCinemaId(cinema.getId());
        for(Room room: roomList)
        {
            JButton temp = new JButton(room.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    RoomView roomView = new RoomView(user,room);
                    frame.add(roomView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    roomView.requestFocus();
                    frame.setTitle("Sala");
                }
            });
            this.add(temp);
        }

        this.add(new JLabel(""));
        if(user.isPermission()) {
            this.add(addRoom);
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
                CinemaView cinemaView = new CinemaView(user,cinema);
                frame.add(cinemaView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
