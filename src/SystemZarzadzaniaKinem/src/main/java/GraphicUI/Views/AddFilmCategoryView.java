package GraphicUI.Views;

import Back.Controllers.FilmCategoryController;
import Back.Models.FilmCategory;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

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
                frame.setTitle("Lista kategorii");
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

        JPanel upper = new JPanel(new GridLayout(1,2));
        upper.setBorder(SettingsService.Border());
        JLabel categoryname =  new JLabel("Nazwa kategorii");
        categoryname.setFont(SettingsService.GenerateFont());
        name.setFont(SettingsService.GenerateFont());
        upper.add(categoryname);
        upper.add(name);

        this.add(upper,BorderLayout.NORTH);


        JPanel lower = new JPanel(new BorderLayout());

        this.add(lower,BorderLayout.SOUTH);

        lower.add(accept,BorderLayout.EAST);
        lower.add(exit,BorderLayout.WEST);

        accept.setFont(SettingsService.GenerateFont());
        accept.setBorder(SettingsService.Border());

        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());

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
