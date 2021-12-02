package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Controllers.FilmController;
import Back.Controllers.SeansController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class SeansListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Room room;
    public SeansListView(User user, Room room) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.room = room;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByRoomId(room.getId());

        JButton addSeans = new JButton("Dodaj seans");
        JButton exit = new JButton("Wstecz");

        addSeans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddSeansView addSeansView = new AddSeansView(user,room);
                frame.add(addSeansView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addSeansView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomView roomView = new RoomView(user,room);
                frame.add(roomView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomView.requestFocus();
            }
        });

        for(Seans item: seansList) {
            JButton temp = new JButton(filmController.getById(item.getFilmId()).getTitle() + " " +simpleDateFormat.format(item.getDate()));
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    SeansView seansView = new SeansView(user, item);
                    frame.add(seansView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    seansView.requestFocus();
                }
            });
            this.add(temp);
        }

        if(user.isPermission())
        {
            this.add(addSeans);
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
                RoomView roomView = new RoomView(user,room);
                frame.add(roomView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
