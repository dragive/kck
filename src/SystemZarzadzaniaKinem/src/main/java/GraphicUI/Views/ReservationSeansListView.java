package GraphicUI.Views;

import Back.Controllers.FilmController;
import Back.Controllers.SeansController;
import Back.Models.Cinema;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationSeansListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    private Object previous;
    private Cinema cinema;
    public ReservationSeansListView(User user,Cinema cinema, Object previous) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.cinema = cinema;
        this.previous = previous;
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

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(previous.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                    frame.add(reservationCinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationCinemaListView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user,cinema);
                    frame.add(cinemaView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
                }
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SeansController seansController = new SeansController();
        FilmController filmController = new FilmController();
        List<Seans> seansList = seansController.getSeansByCinema(cinema.getId());

        seansList = seansList.stream().sorted(Comparator.comparing(Seans::getDate)).collect(Collectors.toList());

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        for(Seans seans: seansList) {
            JButton temp = new JButton(filmController.getById(seans.getFilmId()).getTitle() + " " + simpleDateFormat.format(seans.getDate()));
            temp.setFont(SettingsService.GenerateFont());
//            exit.setBorder(SettingsService.Border());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationSeansView reservationSeansView = new ReservationSeansView(user,seans,this);
                    frame.add(reservationSeansView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationSeansView.requestFocus();
                }
            });

            simpleGridPanel.add(temp);
        }


        JPanel footer = new JPanel(new BorderLayout());
        footer.add(exit,BorderLayout.EAST);
        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());
        this.add(footer,BorderLayout.SOUTH);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                if(previous.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                    frame.add(reservationCinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationCinemaListView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user,cinema);
                    frame.add(cinemaView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
                }
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
