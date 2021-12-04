package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Set;

public class ReservationCinemaListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public ReservationCinemaListView(User user) {
        System.out.println("ReservationCinemaListView");
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;

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

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();
        JLabel title = new JLabel("<html>Wybierz kino by móc umieścić rezerwację w jednym z jego seansów:</html>");
        title.setBorder(SettingsService.Border());
        title.setFont(SettingsService.GenerateFont());
        this.add(title,BorderLayout.NORTH);

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        jScrollPane.add(new JPanel());
        for(Cinema cinema: cinemas) {
            JButton temp = new JButton(cinema.getName());
            temp.setFont(SettingsService.GenerateFont());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    ReservationSeansListView reservationSeansListView = new ReservationSeansListView(user,cinema,this);
                    frame.add(reservationSeansListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationSeansListView.requestFocus();
                }
            });
//            JPanel tempp = new JPanel(new GridLayout(0,1));
//            tempp.add(temp);
            simpleGridPanel.add(temp);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
