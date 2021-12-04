package GraphicUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

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

        JLabel title = new JLabel("Czesio's Cinema Manager");
        JLabel empty = new JLabel("");
        JLabel loginL = new JLabel("Login: ");
        JLabel emailL = new JLabel("Email: ");
        JLabel passwordL = new JLabel("Hasło: ");
        this.add(title);
        this.add(empty);
        this.add(loginL);
        this.add(login);
        this.add(emailL);
        this.add(email);
        this.add(passwordL);
        this.add(password);
        this.add(exit);
        this.add(signUp);

        title.setFont(SettingsService.GenerateFont());
        empty.setFont(SettingsService.GenerateFont());
        loginL.setFont(SettingsService.GenerateFont());
        login.setFont(SettingsService.GenerateFont());
        emailL.setFont(SettingsService.GenerateFont());
        email.setFont(SettingsService.GenerateFont());
        passwordL.setFont(SettingsService.GenerateFont());
        password.setFont(SettingsService.GenerateFont());
        exit.setFont(SettingsService.GenerateFont());
        signUp.setFont(SettingsService.GenerateFont());
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
