package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddUserView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public AddUserView(User user) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;

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

        JButton accept = new JButton("Akceptuj");
        JButton exit = new JButton("Wstecz");
        JTextField name = new JTextField();
        JTextField password = new JTextField();
        JTextField email = new JTextField();
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(false);

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                User newUser = new User();
                newUser.setName(name.getText());
                newUser.setEmail(email.getText());
                newUser.setPasswordHash(password.getText());
                newUser.setRegistrationDate(new Date());
                newUser.setPermission(checkBox.isSelected());
                usersController.createNew(newUser);

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
            }
        });

        this.add(new JLabel("Nazwa użytkownika"));
        this.add(name);

        this.add(new JLabel("E-mail"));
        this.add(email);

        this.add(new JLabel("Hasło"));
        this.add(password);

        this.add(new JLabel("Pracownik"));
        this.add(checkBox);

        this.add(new JLabel(""));
        this.add(accept);

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
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
