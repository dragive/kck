package GraphicUI.Views;

import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class CinemaListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public CinemaListView(User user) {
        panel = this;
        this.user = user;

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
        JButton addCinema = new JButton("Dodaj kino");
        JButton exit = new JButton("Wstecz");

        addCinema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddCinemaView addCinemaView = new AddCinemaView(user);
                frame.add(addCinemaView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                addCinemaView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
            }
        });

        CinemaController cinemaController = new CinemaController();
        List<Cinema> cinemas = cinemaController.getAll();


        if (user.isPermission()) this.add(new JLabel("Wybierz kino lub utwórz nowe:"));
        else this.add(new JLabel("Wybierz kino:"));
        this.add(new JLabel(""));

        for(Cinema cinema: cinemas) {
            JButton temp = new JButton(cinema.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    CinemaView cinemaView = new CinemaView(user, cinema);
                    frame.add(cinemaView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    cinemaView.requestFocus();
                }
            });
            this.add(temp);
        }

        this.add(new JLabel(""));

        if (user.isPermission()) this.add(addCinema);
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
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
