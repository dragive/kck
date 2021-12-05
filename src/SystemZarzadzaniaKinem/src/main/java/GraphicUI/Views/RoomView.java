package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Controllers.RoomsController;
import Back.Models.Room;
import Back.Models.Seat;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

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

        List<Seat> seatList = room.getSeatList();
        JLabel name = new JLabel(room.getName());
        JLabel rows = new JLabel(String.valueOf(seatList.get(seatList.size()-1).getRow()));
        JLabel howManySeats = new JLabel(String.valueOf(seatList.size()));

        name.setFont(SettingsService.GenerateFont());
        rows.setFont(SettingsService.GenerateFont());
        howManySeats.setFont(SettingsService.GenerateFont());



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

        exit.setFont(SettingsService.GenerateFont());
        seanses.setFont(SettingsService.GenerateFont());
        remove.setFont(SettingsService.GenerateFont());

        exit.setBorder(SettingsService.Border());
        seanses.setBorder(SettingsService.Border());
        remove.setBorder(SettingsService.Border());

        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);

        JLabel nameL = new JLabel("Nazwa");
        nameL.setFont(SettingsService.GenerateFont());
        upper.add(nameL);
        upper.add(name);

        JLabel rowsL = new JLabel("Liczba rzędów");
        rowsL.setFont(SettingsService.GenerateFont());
        upper.add(rowsL);
        upper.add(rows);

        JLabel howManySeatsL =new JLabel("Liczba siedzeń");
        howManySeatsL.setFont(SettingsService.GenerateFont());
        upper.add(howManySeatsL);
        upper.add(howManySeats);


        JPanel lowerleft = new JPanel(new BorderLayout());
        JPanel lowerright = new JPanel(new BorderLayout());
        lower.add(lowerleft,BorderLayout.WEST);
        lower.add(lowerright,BorderLayout.EAST);

        if(user.isPermission()) {
            lowerleft.add(remove,BorderLayout.EAST);
        }

        lowerright.add(seanses,BorderLayout.WEST);

        lowerright.add(exit,BorderLayout.EAST);
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
