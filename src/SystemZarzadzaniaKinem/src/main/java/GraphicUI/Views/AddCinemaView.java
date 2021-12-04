package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCinemaView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public AddCinemaView(User user) {
        panel = this;
        this.user = user;
        MenuPanel.bottomPanel = this;
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
        Cinema cinema = new Cinema();
        JTextField name = new JTextField();
        JTextField desc = new JTextField();
        JButton accept = new JButton("Zaakceptuj");
        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cinema.setCityId(1);
                cinema.setName(name.getText());
                cinema.setDescription(desc.getText());

                CinemaController cinemaController = new CinemaController();
                cinemaController.createNew(cinema);

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });

        this.add(new JLabel("Nazwa"));
        this.add(name);
        this.add(new JLabel("Opis"));
        this.add(desc);
        this.add(new JLabel(""));
        this.add(accept);
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
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
