package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class SeansView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Seans seans;
    public SeansView(User user, Seans seans) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.seans = seans;
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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JButton exit = new JButton("Wstecz");
        JButton remove = new JButton("Usuń");

        exit.setFont(SettingsService.GenerateFont());
        remove.setFont(SettingsService.GenerateFont());

        JLabel date = new JLabel(simpleDateFormat.format(seans.getDate()));
        FilmController filmController = new FilmController();
        JLabel film = new JLabel(filmController.getById(seans.getFilmId()).getTitle());

        date.setFont(SettingsService.GenerateFont());
        film.setFont(SettingsService.GenerateFont());

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeansController seansController = new SeansController();
                seansController.delete(seans);
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();
            }
        });

        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);


        JLabel dateL = new JLabel("Data seansu (dd-mm-rrrr hh:mm)");
        dateL.setFont(SettingsService.GenerateFont());
        upper.add(dateL);
        upper.add(date);

        JLabel filmL = new JLabel("Tytuł filmu");
        filmL.setFont(SettingsService.GenerateFont());
        upper.add(filmL);
        upper.add(film);

        if(user.isPermission()) {
            lower.add(remove,BorderLayout.WEST);
        }

remove.setBorder(SettingsService.Border());
        exit.setBorder(SettingsService.Border());
        lower.add(exit,BorderLayout.EAST);
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
            case 27:
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
