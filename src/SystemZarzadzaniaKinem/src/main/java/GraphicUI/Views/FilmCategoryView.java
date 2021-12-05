package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FilmCategoryView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public FilmCategoryView(User user, FilmCategory filmCategory) {
        panel = this;
        MenuPanel.bottomPanel = this;
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

        FilmCategoryController filmCategoryController = new FilmCategoryController();
        List<Film> filmList = filmCategoryController.getCategoryFilms(filmCategory.getId());

        JButton exit = new JButton("Wstecz");
        JButton addFilm = new JButton("Dodaj film");

        addFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddFilmView addFilmView = new AddFilmView(user,filmCategory);
                frame.add(addFilmView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addFilmView.requestFocus();
                frame.setTitle("Dodaj film");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                FilmCategoryListView filmCategoryListView = new FilmCategoryListView(user);
                frame.add(filmCategoryListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryListView.requestFocus();
                frame.setTitle("Lista kategorii");
            }
        });

        this.add(new JLabel("Wybierz film:"));
        this.add(new JLabel(""));
        for(Film film:filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmView filmView = new FilmView(user,film,this);
                    frame.add(filmView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmView.requestFocus();
                    frame.setTitle("Film");
                }
            });
            this.add(temp);
        }
        this.add(new JLabel(""));
        this.add(addFilm);
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
                FilmCategoryListView filmCategoryListView = new FilmCategoryListView(user);
                frame.add(filmCategoryListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
