package GraphicUI.Views;
import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddFilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    FilmCategory filmCategory;
    public AddFilmView(User user, FilmCategory filmCategory) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.filmCategory = filmCategory;
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

        Film film = new Film();
        JButton exit = new JButton("Wstecz");
        JButton accept = new JButton("Zatwierdź");
        JTextField title = new JTextField();
        JTextField desc = new JTextField();
        JTextField date = new JTextField();

        accept.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                film.setFilmCategoryId(filmCategory.getId());
                film.setTitle(title.getText());
                film.setDescription(desc.getText());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                film.setReleaseDate(simpleDateFormat.parse(date.getText()));

                List<Film> filmList = filmCategory.getFilmList();
                if(filmList.size()==0) {
                    film.setId(1);
                }
                else film.setId(filmList.get(filmList.size()-1).getId()+1);

                filmList.add(film);
                filmCategoryController.update(filmCategory);

                FilmController filmController = new FilmController();
                filmController.createNew(film);

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategory);
                frame.add(filmCategoryView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryView.requestFocus();
                frame.setTitle("Filmy");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategory);
                frame.add(filmCategoryView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryView.requestFocus();
                frame.setTitle("Filmy");
            }
        });

        this.add(new JLabel("Tytuł"));
        this.add(title);

        this.add(new JLabel("Opis"));
        this.add(desc);

        this.add(new JLabel("Data (DD-MM-RRRR)"));
        this.add(date);

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
                FilmCategoryView filmCategoryView = new FilmCategoryView(user,filmCategory);
                frame.add(filmCategoryView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
