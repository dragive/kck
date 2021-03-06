package GraphicUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class LoginView extends JPanel implements KeyListener {
    private JTextField login;
    private JTextField password;
    private JPanel panel;
    public User user;
    public LoginView() {
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,2));
        this.setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                requestFocus();
            }
        });
        login = new JTextField();
        password = new JTextField();
        JButton signIn = new JButton("Zaloguj");
        signIn.setFont(SettingsService.GenerateFont());
        JButton signUp = new JButton("Zarejestruj");
        signUp.setFont(SettingsService.GenerateFont());
        JButton exit = new JButton("Wyjdź");
        exit.setFont(SettingsService.GenerateFont());
        panel = this;
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                user = usersController.getByName(login.getText());
                System.out.println(login.getText()+" "+password.getText());
                if(user!=null)
                {
                    if(user.getName().equals(login.getText()) && user.getPasswordHash().equals(password.getText())) {
                        JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                        frame.remove(panel);
                        MenuPanel menuPanel = new MenuPanel(user);
                        frame.add(menuPanel, BorderLayout.NORTH);
                        CinemaListView cinemaListView = new CinemaListView(user);
                        frame.add(cinemaListView, BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                        cinemaListView.requestFocus();
                        frame.setTitle("Menu");
                    }
                    else {
                        new ErrorFrame("Zly login lub haslo");
                    }
                }
                else {
                    new ErrorFrame("Zly login lub haslo");
                }
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                RegisterView registerView = new RegisterView();
                frame.add(registerView, BorderLayout.PAGE_START);
                frame.revalidate();
                frame.repaint();
                registerView.requestFocus();
                frame.setTitle("Rejestracja");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        login.setFont(SettingsService.GenerateFont());
        password.setFont(SettingsService.GenerateFont());
        JLabel title = new JLabel("Czesio's Cinema Manager");
        title.setFont(SettingsService.GenerateFont());
        this.add(title);//.setFont(SettingsService.GenerateFont());
        JLabel empty = new JLabel("");
        empty.setFont(SettingsService.GenerateFont());
        this.add(empty);
        JLabel loginText = new JLabel("Login: ");
        loginText.setFont(SettingsService.GenerateFont());
        this.add(loginText);
        this.add(login);
        JLabel pass = new JLabel("Hasło: ");
        pass.setFont(SettingsService.GenerateFont());
        this.add(pass);
        this.add(password);
        this.add(signUp);
        this.add(signIn);
        this.add(exit);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 27:
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                break;
            case 10:
                UsersController usersController = new UsersController();
                user = usersController.getByName(login.getText());
                System.out.println(login.getText()+" "+password.getText());
                if(user!=null)
                {
                    if(user.getName().equals(login.getText()) && user.getPasswordHash().equals(password.getText())) {
                        JFrame frame2 = (JFrame) SwingUtilities.windowForComponent(panel);
                        frame2.remove(panel);
                        MenuPanel menuPanel = new MenuPanel(user);
                        frame2.add(menuPanel, BorderLayout.PAGE_START);
                        CinemaListView cinemaListView = new CinemaListView(user);
                        frame2.add(cinemaListView, BorderLayout.CENTER);
                        frame2.revalidate();
                        frame2.repaint();
                        cinemaListView.requestFocus();
                    }
                    else {
                        new ErrorFrame("Zly login lub haslo");
                    }
                }
                else {
                    new ErrorFrame("Zly login lub haslo");
                }
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
