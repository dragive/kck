package GraphicUI.Views;

import Back.Models.User;
import GraphicUI.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditsView extends JPanel implements KeyListener {
    JPanel panel;
    public CreditsView() {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,1));//TODO
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

        this.add(new JLabel("Autorzy Projektu:"));
        this.add(new JLabel("Maciek Fender"));
        this.add(new JLabel("Kacper Chrost"));
        this.add(new JLabel("Krzysztof Funkowski"));
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
