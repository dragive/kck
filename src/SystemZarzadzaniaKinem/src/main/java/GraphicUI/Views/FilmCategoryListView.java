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
                frame.setTitle("Dodaj kategorię");
            }
        });
        JLabel selectCategory =new JLabel("Wybierz kategorię filmu:");
        selectCategory.setBorder(SettingsService.Border());
        selectCategory.setFont(SettingsService.GenerateFont());
        this.add(selectCategory,BorderLayout.NORTH);

        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);

        filmCategories = filmCategories.stream().sorted(Comparator.comparing(FilmCategory::getName)).collect(Collectors.toList());


        for(FilmCategory item: filmCategories) {
            JButton temp = new JButton(item.getName());
            temp.setBorder(SettingsService.Border());
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
                    frame.setTitle("Filmy");
                }
            });
            simpleGridPanel.add(temp);
        }
        addCategory.setBorder(SettingsService.Border());

        if(user.isPermission()) {
            JPanel footer = new JPanel(new BorderLayout());
            JPanel footerRight = new JPanel(new BorderLayout());
            footer.add(footerRight,BorderLayout.EAST);

//            .setBorder(SettingsService.Border());
            footerRight.add(addCategory,BorderLayout.SOUTH);

            this.add(footer,BorderLayout.SOUTH);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
