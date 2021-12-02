package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class FilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Object previousWindow;
    Film film;
    public FilmView(User user, Film film, Object previousWindow) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.film = film;
        this.previousWindow = previousWindow;
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
        JButton delete = new JButton("Usuń");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                FilmCategory filmCategory = filmCategoryController.getById(film.getFilmCategoryId());
                List<Film> filmList = filmCategory.getFilmList();
                filmList.remove(film);
                filmCategory.setFilmList(filmList);
                filmCategoryController.update(filmCategory);
                FilmController filmController = new FilmController();
                filmController.delete(film);
                if(previousWindow.getClass().toString().contains("FilmCategoryView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategoryController.getById(film.getFilmCategoryId()));
                    frame.add(filmCategoryView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmCategoryView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    MenuFilmView menuView = new MenuFilmView(user);
                    frame.add(menuView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(previousWindow.getClass().toString().contains("FilmCategoryView")) {
                    FilmCategoryController filmCategoryController = new FilmCategoryController();
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategoryController.getById(film.getFilmCategoryId()));
                    frame.add(filmCategoryView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmCategoryView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    MenuFilmView menuView = new MenuFilmView(user);
                    frame.add(menuView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
                }
            }
        });

        this.add(new JLabel("Tytuł"));
        this.add(new JLabel(film.getTitle()));

        this.add(new JLabel("Opis"));
        this.add(new JLabel(film.getDescription()));

        this.add(new JLabel("Data (DD-MM-RRRR)"));
        this.add(new JLabel(simpleDateFormat.format(film.getReleaseDate())));

        if(user.isPermission())
        {
            this.add(delete);
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
                if(previousWindow.getClass().toString().contains("FilmCategoryView")) {
                    FilmCategoryController filmCategoryController = new FilmCategoryController();
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategoryController.getById(film.getFilmCategoryId()));
                    frame.add(filmCategoryView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmCategoryView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    MenuFilmView menuView = new MenuFilmView(user);
                    frame.add(menuView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
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
