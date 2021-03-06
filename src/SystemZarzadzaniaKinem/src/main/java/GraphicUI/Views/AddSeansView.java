package GraphicUI.Views;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Film;
import Back.Models.Room;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
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
        date.setFont(SettingsService.GenerateFont());
        JButton button = new JButton("Wybierz Film");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
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

        JButton accept = new JButton("Zatwierdź");

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
                frame.setTitle("Wybierz film");
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
                frame.setTitle("Seanse");
            }
        });

        accept.setFont(SettingsService.GenerateFont());
        accept.setBorder(SettingsService.Border());
        accept.setEnabled(false);

        button.setFont(SettingsService.GenerateFont());
        button.setBorder(SettingsService.Border());

        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());

        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);


        JLabel dateL = new JLabel("Data seansu (dd-mm-rrrr hh:mm)");
        dateL.setFont(SettingsService.GenerateFont());
        upper.add(dateL);
        upper.add(date);

        JPanel lowerEast = new JPanel(new BorderLayout());
        JPanel lowerEastEast = new JPanel(new BorderLayout());

//        lowerEast.add(exit,BorderLayout.WEST);
        lowerEast.add(lowerEastEast,BorderLayout.EAST);
        lowerEast.add(button,BorderLayout.WEST);
        lowerEastEast.add(accept,BorderLayout.WEST);
        lowerEastEast.add(exit,BorderLayout.EAST);

        lower.add(lowerEast,BorderLayout.EAST);

    }

    public AddSeansView(User user, Seans seans, Film film) {
        panel = this;
        RoomsController roomsController = new RoomsController();
        this.user = user;
        this.room = roomsController.getById(seans.getRoomId());
        JTextField date = new JTextField();
        JButton button = new JButton("Zmień film");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        date.setText(String.valueOf(simpleDateFormat.format(seans.getDate())));
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

        JButton exit = new JButton("Wstecz");
        JButton accept = new JButton("Zatwierdź");

        button.setFont(SettingsService.GenerateFont());
        button.setBorder(SettingsService.Border());

        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());

        accept.setFont(SettingsService.GenerateFont());
        accept.setBorder(SettingsService.Border());

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
                frame.setTitle("Sala");
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
                frame.setTitle("Zmień film");
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
                frame.setTitle("Seanse");
            }
        });



        button.setFont(SettingsService.GenerateFont());
        button.setBorder(SettingsService.Border());

        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());

        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);


        JLabel dateL = new JLabel("Data seansu (dd-mm-rrrr hh:mm)");
        dateL.setFont(SettingsService.GenerateFont());
        upper.add(dateL);
        upper.add(date);
        date.setFont(SettingsService.GenerateFont());

        JPanel lowerEast = new JPanel(new BorderLayout());
        JPanel lowerEastEast = new JPanel(new BorderLayout());

//        lowerEast.add(exit,BorderLayout.WEST);
        lowerEast.add(lowerEastEast,BorderLayout.EAST);
        lowerEast.add(button,BorderLayout.WEST);
        lowerEastEast.add(accept,BorderLayout.WEST);
        lowerEastEast.add(exit,BorderLayout.EAST);

        lower.add(lowerEast,BorderLayout.EAST);


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
