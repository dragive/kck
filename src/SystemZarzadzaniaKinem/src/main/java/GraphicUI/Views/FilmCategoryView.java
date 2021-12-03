package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmCategoryView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public FilmCategoryView(User user, FilmCategory filmCategory) {
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

        FilmCategoryController filmCategoryController = new FilmCategoryController();
        List<Film> filmList = filmCategoryController.getCategoryFilms(filmCategory.getId());

        JButton exit = new JButton("Wstecz");
        exit.setFont(SettingsService.GenerateFont());
        JButton addFilm = new JButton("Dodaj film");
        addFilm.setFont(SettingsService.GenerateFont());

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
            }
        });
        JLabel addFilmLabel = new JLabel("Wybierz film:");
        addFilmLabel.setFont(SettingsService.GenerateFont());
        this.add(addFilmLabel);


        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        filmList = filmList.stream().sorted(Comparator.comparing(Film::getTitle)).collect(Collectors.toList());

        for(Film film:filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.setFont(SettingsService.GenerateFont());
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
                }
            });
            simpleGridPanel.add(temp);
        }
        JPanel jPanelAddExit = new JPanel(new BorderLayout());
        jPanelAddExit.add(addFilm,BorderLayout.LINE_START);
        addFilm.setBorder(new EmptyBorder(30, 30, 30, 30));
        exit.setBorder(new EmptyBorder(30, 30, 30, 30));
        jPanelAddExit.add(exit,BorderLayout.LINE_END);
        this.add(jPanelAddExit,BorderLayout.SOUTH);
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
