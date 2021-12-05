package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Controllers.FilmController;
import Back.Controllers.SeansController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

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
        System.out.println("SeansListView");
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByRoomId(room.getId());

        JButton addSeans = new JButton("Dodaj seans");
        JButton exit = new JButton("Wstecz");

        addSeans.setFont(SettingsService.GenerateFont());
        exit.setFont(SettingsService.GenerateFont());

        addSeans.setBorder(SettingsService.Border());
        exit.setBorder(SettingsService.Border());

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
                frame.setTitle("Dodaj seans");
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
                frame.setTitle("Sala");
            }
        });


        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);


        for(Seans item: seansList) {
//            for(int i =5;i<100;i++)
            {
                JButton temp = new JButton(filmController.getById(item.getFilmId()).getTitle() + " " + simpleDateFormat.format(item.getDate()));
                temp.setFont(SettingsService.GenerateFont());
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
                        frame.setTitle("Seans");
                    }
                });
                simpleGridPanel.add(temp);
            }
        }

        JPanel footer = new JPanel(new BorderLayout());
        this.add(footer,BorderLayout.SOUTH);

        if(user.isPermission())
        {
            footer.add(addSeans,BorderLayout.WEST);
        }

        footer.add(exit,BorderLayout.EAST);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
