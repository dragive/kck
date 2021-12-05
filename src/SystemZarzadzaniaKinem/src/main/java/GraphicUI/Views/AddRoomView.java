package GraphicUI.Views;

import Back.Controllers.RoomsController;
import Back.Models.Cinema;
import Back.Models.Room;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import com.googlecode.lanterna.gui2.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRoomView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Cinema cinema;
    public AddRoomView(User user, Cinema cinema) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.cinema = cinema;
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
        JTextField name = new JTextField();
        JTextField rows = new JTextField();
        JTextField cols = new JTextField();
        JButton button = new JButton("Dodaj");
        JButton exit = new JButton("Wstecz");
        Room room = new Room();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomsController roomsController = new RoomsController();
                room.setCinemaId(cinema.getId());
                room.setName(name.getText());
                room.setSeatList(roomsController.FillRoom(Integer.parseInt(rows.getText()),Integer.parseInt(cols.getText())));
                roomsController.createNew(room);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaView cinemaView = new CinemaView(user,cinema);
                frame.add(cinemaView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                cinemaView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RoomListView roomListView = new RoomListView(user,cinema);
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
            }
        });

        JPanel upper = new JPanel(new GridLayout(0,2));

        JPanel footer = new JPanel(new BorderLayout());

        this.add(upper,BorderLayout.NORTH);
        this.add(footer,BorderLayout.SOUTH);

        JLabel nameL = new JLabel("Nazwa");
        nameL.setFont(SettingsService.GenerateFont());
        name.setFont(SettingsService.GenerateFont());
        upper.add(nameL);

        upper.add(name);


        JLabel rowsL = new JLabel("Liczba siedzeń w rzędzie");
        rowsL.setFont(SettingsService.GenerateFont());
        upper.add(rowsL);
        upper.add(rows);

        rows.setFont(SettingsService.GenerateFont());

        JLabel colsL = new JLabel("Liczba rzędów");
        colsL.setFont(SettingsService.GenerateFont());
        cols.setFont(SettingsService.GenerateFont());
        upper.add(colsL);
        upper.add(cols);

        footer.add(button,BorderLayout.EAST);
        footer.add(exit,BorderLayout.WEST);

        button.setBorder(SettingsService.Border());
        button.setFont(SettingsService.GenerateFont());

        exit.setBorder(SettingsService.Border());
        exit.setFont(SettingsService.GenerateFont());
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
                RoomListView roomListView = new RoomListView(user,cinema);
                frame.add(roomListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                roomListView.requestFocus();
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
