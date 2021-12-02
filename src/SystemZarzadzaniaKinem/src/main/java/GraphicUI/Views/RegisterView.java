package GraphicUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class RegisterView extends JPanel implements KeyListener {
    JPanel panel;
    public RegisterView() {
        panel = this;
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,2));
        this.addKeyListener(this);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        JButton exit = new JButton("Wstecz");
        JTextField login = new JTextField();
        JTextField password = new JTextField();
        JTextField email = new JTextField();
        JButton signUp = new JButton("Zarejestruj");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                LoginView loginView = new LoginView();
                frame.add(loginView, BorderLayout.PAGE_START);
                frame.revalidate();
                frame.repaint();
                loginView.requestFocus();
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                User newUser = new User();
                newUser.setName(login.getText());
                newUser.setEmail(email.getText());
                newUser.setPasswordHash(password.getText());
                newUser.setRegistrationDate(new Date());
                newUser.setPermission(false);
                usersController.createNew(newUser);
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuPanel menuPanel = new MenuPanel(newUser);
                frame.add(menuPanel, BorderLayout.PAGE_START);
                CinemaListView cinemaListView = new CinemaListView(newUser);
                frame.add(cinemaListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                menuPanel.requestFocus();
            }
        });

        this.add(new JLabel("Czesio's Cinema Manager"));
        this.add(new JLabel(""));
        this.add(new JLabel("Login: "));
        this.add(login);
        this.add(new JLabel("Email: "));
        this.add(email);
        this.add(new JLabel("Hasło: "));
        this.add(password);
        this.add(exit);
        this.add(signUp);
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
                LoginView loginView = new LoginView();
                frame.add(loginView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                loginView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
