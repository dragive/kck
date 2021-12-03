package GraphicUI;

import Back.Models.User;
import GraphicUI.Views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements KeyListener {
    public User user;
    public MainFrame() {
        this.setLayout(new BorderLayout(20,0));
        this.setPreferredSize(new Dimension(720,445));
        this.setMinimumSize(new Dimension(720,445));
        this.setFocusable(true);
        this.getContentPane().setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.addKeyListener(this);
        LoginView loginView = new LoginView();
        this.add(loginView, BorderLayout.PAGE_START);
        loginView.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
