package GraphicUI.Views;
import Back.Controllers.RoomsController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        JButton addRoom = new JButton("Dodaj salę kinową");
        addRoom.setFont(SettingsService.GenerateFont());

        JButton exit = new JButton("Wstecz");
        exit.setFont(SettingsService.GenerateFont());

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

        roomList = roomList.stream().sorted(Comparator.comparing(Room::getName)).collect(Collectors.toList());

//        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
//        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
//        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        this.add(jScrollPane,BorderLayout.CENTER);

        JPanel tempp = new JPanel(new GridLayout(0,1));
        this.add(tempp,BorderLayout.NORTH);

        for(Room room: roomList)
        {
            JButton temp = new JButton(room.getName());
            temp.setFont(SettingsService.GenerateFont());
            temp.setBorder(SettingsService.Border());
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

            tempp.add(temp);

        }

        JPanel footer = new JPanel(new BorderLayout());
        this.add(footer,BorderLayout.SOUTH);
        if(user.isPermission()) {
            addRoom.setBorder(SettingsService.Border());
            footer.add(addRoom,BorderLayout.EAST);
        }
        footer.add(exit,BorderLayout.WEST);
        exit.setBorder(SettingsService.Border());
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
