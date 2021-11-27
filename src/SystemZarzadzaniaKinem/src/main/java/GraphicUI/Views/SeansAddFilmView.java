package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.RoomsController;
import Back.Models.Film;
import Back.Models.Seans;
import Back.Models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SeansAddFilmView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Seans seans;
    public SeansAddFilmView(User user, Seans seans) {
        panel = this;
        this.user = user;
        this.seans = seans;
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
        FilmController filmController = new FilmController();
        List<Film> filmList = filmController.getAll();

        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddSeansView seansView = new AddSeansView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                seansView.requestFocus();
            }
        });

        for(Film film: filmList) {
            JButton temp = new JButton(film.getTitle());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    AddSeansView seansView = new AddSeansView(user,seans,film);
                    frame.add(seansView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    seansView.requestFocus();
                }
            });
            this.add(temp);
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
                AddSeansView seansView = new AddSeansView(user,roomsController.getById(seans.getRoomId()));
                frame.add(seansView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                seansView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
