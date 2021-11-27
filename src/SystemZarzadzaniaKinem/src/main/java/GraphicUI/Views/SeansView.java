package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Controllers.SeansController;
import Back.Models.Seans;
import Back.Models.User;
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
        this.user = user;
        this.seans = seans;
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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JButton exit = new JButton("Wstecz");
        JButton remove = new JButton("Usuń");
        JLabel date = new JLabel(simpleDateFormat.format(seans.getDate()));
        FilmController filmController = new FilmController();
        JLabel film = new JLabel(filmController.getById(seans.getFilmId()).getTitle());

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeansController seansController = new SeansController();
                seansController.delete(seans);
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansListView, new GridBagConstraints());
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
                frame.add(seansListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                seansListView.requestFocus();
            }
        });

        this.add(new JLabel("Data seansu (dd-mm-rrrr hh:mm)"));
        this.add(date);

        this.add(new JLabel("Tytuł filmu"));
        this.add(film);

        if(user.isPermission()) {
            this.add(new JLabel(""));
            this.add(remove);
        }

        this.add(new JLabel(""));
        this.add(exit);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                SeansListView seansListView = new SeansListView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansListView, new GridBagConstraints());
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
