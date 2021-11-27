package GraphicUI.Views;

import Back.Controllers.UsersController;
import Back.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        JButton signUp = new JButton("Zarejestruj");
        JButton exit = new JButton("Wyjdź");
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
                        MenuView menuView = new MenuView(user);
                        frame.add(menuView, new GridBagConstraints());
                        frame.revalidate();
                        frame.repaint();
                        menuView.requestFocus();
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
                frame.add(registerView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                registerView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.add(new JLabel("Czesio's Cinema Manager"));
        this.add(new JLabel(""));
        this.add(new JLabel("Login: "));
        this.add(login);
        this.add(new JLabel("Hasło: "));
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
                        MenuView menuView = new MenuView(user);
                        frame2.add(menuView, new GridBagConstraints());
                        frame2.revalidate();
                        frame2.repaint();
                        menuView.requestFocus();
                    }
                    else {
                        new ErrorFrame("Zly login lub haslo");
                    }
                }
                else {
                    new ErrorFrame("Zly login lub haslo");
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
