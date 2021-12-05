package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditUserView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    User panelUser;
    Object previous;
    public EditUserView(User user,User panelUser,Object previousWindow) {
        previous = previousWindow;
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;
        this.panelUser = panelUser;
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

        JTextField name = new JTextField();
        name.setText(panelUser.getName());
        JTextField email = new JTextField();
        email.setText(panelUser.getEmail());

        JButton delete = new JButton("Usuń konto");
        JButton accept = new JButton("Zatwierdź");
        JButton exit = new JButton("Wstecz");
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(panelUser.isPermission());

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                usersController.delete(panelUser);
                if(previousWindow.getClass().toString().contains("UserListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    UserListView userListView = new UserListView(user);
                    frame.add(userListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    userListView.requestFocus();
                    frame.setTitle("Lista użytkowników");
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    LoginView loginView = new LoginView();
                    frame.add(loginView, BorderLayout.PAGE_START);
                    frame.revalidate();
                    frame.repaint();
                    loginView.requestFocus();
                    frame.setTitle("Menu");
                }
            }
        });
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                panelUser.setName(name.getText());
                panelUser.setEmail(email.getText());
                panelUser.setPermission(checkBox.isSelected());
                usersController.update(user);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserView userView = new UserView(user,panelUser,previous);
                frame.add(userView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userView.requestFocus();
                frame.setTitle("Twoje konto");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserView userView = new UserView(user,panelUser,previous);
                frame.add(userView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userView.requestFocus();
                frame.setTitle("Twoje konto");
            }
        });

        this.add(new JLabel("Nazwa użytkownika"));
        this.add(name);

        this.add(new JLabel("Adres e-mail"));
        this.add(email);

        if(user.isPermission()) {
            this.add(new JLabel("Pracownik"));
            this.add(checkBox);
        }

        this.add(new JLabel(""));
        this.add(accept);

        this.add(new JLabel(""));
        this.add(delete);

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
                UserView userView = new UserView(user,panelUser,previous);
                frame.add(userView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
