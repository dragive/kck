package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddFilmCategoryView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public AddFilmCategoryView(User user) {
        panel = this;
        this.user = user;
        MenuPanel.bottomPanel = this;
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

        FilmCategory filmCategory = new FilmCategory();

        JButton exit = new JButton("Wstecz");
        JButton accept = new JButton("Zatwierdź");
        JTextField name = new JTextField();

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilmCategoryController filmCategoryController = new FilmCategoryController();
                filmCategory.setName(name.getText());
                filmCategory.setFilmList(new ArrayList<>());
                filmCategoryController.createNew(filmCategory);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                FilmCategoryListView filmCategoryListView = new FilmCategoryListView(user);
                frame.add(filmCategoryListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                filmCategoryListView.requestFocus();
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

        this.add(new JLabel("Nazwa kategorii"));
        this.add(name);

        this.add(accept);
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
