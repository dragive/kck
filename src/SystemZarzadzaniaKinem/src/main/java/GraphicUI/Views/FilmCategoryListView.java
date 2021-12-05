package GraphicUI.Views;
import Back.Controllers.FilmCategoryController;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FilmCategoryListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public FilmCategoryListView(User user) {
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
        List<FilmCategory> filmCategories = filmCategoryController.getAll();

        JButton addCategory = new JButton("Dodaj kategorię");

        addCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddFilmCategoryView addFilmCategoryView = new AddFilmCategoryView(user);
                frame.add(addFilmCategoryView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addFilmCategoryView.requestFocus();
                frame.setTitle("Dodaj kategorię");
            }
        });

        this.add(new JLabel("Wybierz kategorię filmu:"));

        for(FilmCategory item: filmCategories) {
            JButton temp = new JButton(item.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    FilmCategoryView filmCategoryView = new FilmCategoryView(user,item);
                    frame.add(filmCategoryView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmCategoryView.requestFocus();
                    frame.setTitle("Filmy");
                }
            });
            this.add(temp);
        }

        this.add(new JLabel(""));
        if(user.isPermission()) {
            this.add(addCategory);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
