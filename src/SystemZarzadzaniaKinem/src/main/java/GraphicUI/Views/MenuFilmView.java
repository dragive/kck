package GraphicUI.Views;

import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MenuFilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public MenuFilmView(User user) {
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

        JButton exit = new JButton("Wstecz");

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

        this.add(new JLabel("Wybierz film z listy:"));

        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();
        for(Film film:filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmView menuView = new FilmView(user,film,this);
                    frame.add(menuView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
                }
            });
            this.add(temp);
        }

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
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
                frame.remove(this);
                frame.requestFocusInWindow();
                frame.repaint();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
