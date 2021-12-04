package GraphicUI.Views;

import Back.Controllers.RoomsController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.User;
import GraphicUI.MenuPanel;
import com.googlecode.lanterna.gui2.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRoomView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Cinema cinema;
    public AddRoomView(User user, Cinema cinema) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.cinema = cinema;
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,2));//TODO
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
        JTextField name = new JTextField();
        JTextField rows = new JTextField();
        JTextField cols = new JTextField();
        JButton button = new JButton("Dodaj");
        JButton exit = new JButton("Wstecz");
        Room room = new Room();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                room.setCinemaId(cinema.getId());
                room.setName(name.getText());
                room.setSeatList(roomsController.FillRoom(Integer.parseInt(rows.getText()),Integer.parseInt(cols.getText())));
                roomsController.createNew(room);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaView cinemaView = new CinemaView(user,cinema);
                frame.add(cinemaView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
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

        this.add(new JLabel("Nazwa"));
        this.add(name);

        this.add(new JLabel("Liczba siedzeń w rzędzie"));
        this.add(rows);

        this.add(new JLabel("Liczba rzędów"));
        this.add(cols);

        this.add(new JLabel(""));
        this.add(button);

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
                RoomListView roomListView = new RoomListView(user,cinema);
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
