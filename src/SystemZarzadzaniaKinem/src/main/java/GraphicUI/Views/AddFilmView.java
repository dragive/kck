package GraphicUI.Views;
import Back.Controllers.FilmCategoryController;
import Back.Controllers.FilmController;
import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

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

        Film film = new Film();
        JButton exit = new JButton("Wstecz");
        exit.setFont(SettingsService.GenerateFont());
        JButton accept = new JButton("Zatwierdź");
        accept.setFont(SettingsService.GenerateFont());
        JTextField title = new JTextField();
        title.setFont(SettingsService.GenerateFont());
        JTextField desc = new JTextField();
        desc.setFont(SettingsService.GenerateFont());
        JTextField date = new JTextField();
        date.setFont(SettingsService.GenerateFont());

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
            }
        });

        JPanel upper = new JPanel(new GridLayout(0,1));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);

        JPanel panel;
        panel = new JPanel(new GridLayout(1,2));
        JLabel titleLabel = new JLabel("Tytuł");
        titleLabel.setFont(SettingsService.GenerateFont());

        panel.add(titleLabel);
        panel.add(title);

        upper.add(panel);


        panel = new JPanel(new GridLayout(1,2));
        JLabel dateLabel = new JLabel("Data (DD-MM-RRRR)");
        dateLabel.setFont(SettingsService.GenerateFont());
        panel.add(dateLabel);
        panel.add(date);
        upper.add(panel);

        panel = new JPanel(new GridLayout(1,2));
        JLabel descLabel = new JLabel("Opis");
        descLabel.setFont(SettingsService.GenerateFont());
        panel.add(descLabel);
        panel.add(desc);
        upper.add(panel);



        panel = new JPanel(new GridLayout(1,1));

        panel.add(accept);
        accept.setBorder(new EmptyBorder(30, 30, 30, 30));
        lower.add(panel,BorderLayout.WEST);

        panel = new JPanel(new GridLayout(1,1));
        exit.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.add(exit);
        lower.add(panel,BorderLayout.EAST);
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
