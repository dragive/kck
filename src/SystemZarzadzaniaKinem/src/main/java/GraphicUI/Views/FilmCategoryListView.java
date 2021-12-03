package GraphicUI.Views;
import Back.Controllers.FilmCategoryController;
import Back.Models.Cinema;
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
import java.util.stream.Collectors;

public class FilmCategoryListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public FilmCategoryListView(User user) {
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
        List<FilmCategory> filmCategories = filmCategoryController.getAll();

        JButton addCategory = new JButton("Dodaj kategorię");
        addCategory.setFont(SettingsService.GenerateFont());
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
            }
        });
        JLabel selectCategory =new JLabel("Wybierz kategorię filmu:");
        selectCategory.setFont(SettingsService.GenerateFont());
        this.add(selectCategory,BorderLayout.NORTH);

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        filmCategories = filmCategories.stream().sorted(Comparator.comparing(FilmCategory::getName)).collect(Collectors.toList());


        for(FilmCategory item: filmCategories) {
            JButton temp = new JButton(item.getName());
            temp.setFont(SettingsService.GenerateFont());
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
                }
            });
            simpleGridPanel.add(temp);
        }
        addCategory.setBorder(new EmptyBorder(30, 30, 30, 30));

        if(user.isPermission()) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BorderLayout());
            jPanel.setBorder(new EmptyBorder(20, 0,0,0));
            jPanel.add(addCategory,BorderLayout.SOUTH);

            this.add(jPanel,BorderLayout.SOUTH);
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
