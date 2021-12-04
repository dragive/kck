package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        this.setLayout(new GridLayout(1,1));
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
        exit.setFont(SettingsService.GenerateFont());
        JButton delete = new JButton("Usuń");
        delete.setFont(SettingsService.GenerateFont());
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

        JPanel main = new JPanel(new BorderLayout());
        this.add(main);

        JPanel higher = new JPanel(new GridLayout(0,2));
        main.add(higher,BorderLayout.NORTH);
        JLabel titleLabel = new JLabel("Tytuł");
        titleLabel.setFont(SettingsService.GenerateFont());
        higher.add(titleLabel);
        JLabel titleOfFilmLabel = new JLabel(film.getTitle());
        titleOfFilmLabel.setFont(SettingsService.GenerateFont());
        higher.add(titleOfFilmLabel);

        JLabel datelabel =new JLabel("Data (DD-MM-RRRR)");
        datelabel.setFont(SettingsService.GenerateFont());
        higher.add(datelabel);
        JLabel releasedateLabel = new JLabel(simpleDateFormat.format(film.getReleaseDate()));
        releasedateLabel.setFont(SettingsService.GenerateFont());
        higher.add(releasedateLabel);



        JPanel centered = new JPanel(new GridLayout(0,2));
        main.add(centered,BorderLayout.CENTER);

        JLabel filmdesc = new JLabel("Opis");
        filmdesc.setFont(SettingsService.GenerateFont());
        filmdesc.setVerticalAlignment(JLabel.NORTH);
        centered.add(filmdesc);

        ScrollPane scrollPane = new ScrollPane();
        centered.add(scrollPane);
        JTextArea descOfTheFilm =new JTextArea("dupa \ndupa2 \n");
        descOfTheFilm.setEditable(false);
        descOfTheFilm.setFont(SettingsService.GenerateFont());
        scrollPane.add(descOfTheFilm);


        JPanel lower = new JPanel(new BorderLayout());
        main.add(lower,BorderLayout.SOUTH);
        if(user.isPermission())
        {
            lower.add(delete,BorderLayout.WEST);
            delete.setBorder(new EmptyBorder(30, 30, 30, 30));
        }
        exit.setBorder(new EmptyBorder(30, 30, 30, 30));
        lower.add(exit,BorderLayout.EAST);
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
