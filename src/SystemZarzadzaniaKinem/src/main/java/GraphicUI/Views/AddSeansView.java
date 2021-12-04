package GraphicUI.Views;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Film;
import Back.Models.Room;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class AddSeansView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Room room;
    public AddSeansView(User user, Room room) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.room = room;
        Seans seans = new Seans();
        JTextField date = new JTextField();
        JButton button = new JButton("Wybierz film");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
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

        JButton exit = new JButton("Wstecz");

        button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                seans.setSeatList(room.getSeatList());
                seans.setRoomId(room.getId());
                seans.setDate(simpleDateFormat.parse(date.getText()));
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansAddFilmView seansAddFilmView = new SeansAddFilmView(user,seans);
                frame.add(seansAddFilmView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansAddFilmView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
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

        this.add(new JLabel("Data seansu (dd-mm-rrrr hh:mm)"));
        this.add(date);

        this.add(new JLabel(""));
        this.add(button);

        this.add(new JLabel(""));
        this.add(exit);
    }

    public AddSeansView(User user, Seans seans, Film film) {
        panel = this;
        RoomsController roomsController = new RoomsController();
        this.user = user;
        this.room = roomsController.getById(seans.getRoomId());
        JTextField date = new JTextField();
        JButton button = new JButton("Wybierz film");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        date.setText(String.valueOf(simpleDateFormat.format(seans.getDate())));
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

        JButton exit = new JButton("Wstecz");
        JButton accept = new JButton("Zatwierdź");

        accept.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                SeansController seansController = new SeansController();
                room = roomsController.getById(seans.getRoomId());
                seans.setDate(simpleDateFormat.parse(date.getText()));
                seans.setFilmId(film.getId());
                seans.setCinemaId(room.getCinemaId());
                seansController.createNew(seans);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomView roomView = new RoomView(user,room);
                frame.add(roomView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomView.requestFocus();
            }
        });
        button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                seans.setSeatList(room.getSeatList());
                seans.setRoomId(room.getId());
                seans.setDate(simpleDateFormat.parse(date.getText()));
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansAddFilmView seansAddFilmView = new SeansAddFilmView(user,seans);
                frame.add(seansAddFilmView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansAddFilmView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
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

        this.add(new JLabel("Data seansu (dd-mm-rrrr hh:mm)"));
        this.add(date);

        this.add(accept);
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
                SeansListView seansListView = new SeansListView(user,room);
                frame.add(seansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
