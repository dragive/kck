package GraphicUI.Views;
import Back.Controllers.CinemaController;
import Back.Models.Cinema;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCinemaView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public AddCinemaView(User user) {
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
        Cinema cinema = new Cinema();
        JTextField name = new JTextField();
        JTextArea desc = new JTextArea();
        JButton accept = new JButton("Zaakceptuj");
        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cinema.setCityId(1);
                cinema.setName(name.getText());
                cinema.setDescription(desc.getText());

                CinemaController cinemaController = new CinemaController();
                cinemaController.createNew(cinema);

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });

        JPanel upper = new JPanel(new GridLayout(0,2));

        JPanel center = new JPanel(new GridLayout(0,2));

        JPanel footer = new JPanel(new BorderLayout());

        this.add(upper,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        this.add(footer,BorderLayout.SOUTH);
        JLabel nameLabel = new JLabel("Nazwa");
        upper.add(nameLabel);
        nameLabel.setFont(SettingsService.GenerateFont());
        upper.add(name);
        name.setFont(SettingsService.GenerateFont());

        JLabel descLabel = new JLabel("Opis");
        descLabel.setVerticalAlignment(JLabel.NORTH);
        center.add(descLabel);
        desc.setFont(SettingsService.GenerateFont());
        center.add(desc);
        descLabel.setFont(SettingsService.GenerateFont());


        footer.add(accept,BorderLayout.WEST);
        accept.setFont(SettingsService.GenerateFont());
        accept.setBorder(SettingsService.Border());

        footer.add(exit,BorderLayout.EAST);
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
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
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
