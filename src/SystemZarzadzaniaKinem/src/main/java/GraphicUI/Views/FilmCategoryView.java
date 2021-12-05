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
        JLabel addFilmLabel = new JLabel("Wybierz film:");
        addFilmLabel.setFont(SettingsService.GenerateFont());
        addFilmLabel.setBorder(SettingsService.Border());
        this.add(addFilmLabel,BorderLayout.NORTH);


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
                    frame.setTitle("Film");
                }
            });
            simpleGridPanel.add(temp);
        }
        JPanel footer = new JPanel(new BorderLayout());
        JPanel footerRight = new JPanel(new BorderLayout());
        footer.add(footerRight,BorderLayout.EAST);
        footerRight.add(addFilm,BorderLayout.WEST);
        addFilm.setBorder(SettingsService.Border());
        exit.setBorder(SettingsService.Border());
        footerRight.add(exit,BorderLayout.EAST);
        this.add(footer,BorderLayout.SOUTH);
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
