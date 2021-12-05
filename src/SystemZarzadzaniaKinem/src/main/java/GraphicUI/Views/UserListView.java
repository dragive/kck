package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class UserListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public UserListView(User user) {
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
        UsersController usersController = new UsersController();
        List<User> userList = usersController.getAll();

        JButton addUser = new JButton("Dodaj użytkownika");

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddUserView addUserView = new AddUserView(user);
                frame.add(addUserView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                addUserView.requestFocus();
                frame.setTitle("Dodaj użytkownika");
            }
        });

        for(User item : userList) {
            JButton temp = new JButton(item.getName());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    UserView cinemaListView = new UserView(user,item,this);
                    frame.add(cinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaListView.requestFocus();
                    frame.setTitle("Użytkownik");
                }
            });
            this.add(temp);
        }
        this.add(addUser);
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
