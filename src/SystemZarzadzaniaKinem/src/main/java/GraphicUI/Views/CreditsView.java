package GraphicUI.Views;

import Back.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditsView extends JPanel implements KeyListener {
    JPanel panel;
    private User user;
    public CreditsView(User user) {
        this.user = user;
        panel = this;
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

        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
            }
        });

        this.add(new JLabel("Autorzy Projektu:"));
        this.add(new JLabel(""));
        this.add(new JLabel("Maciek Fender"));
        this.add(new JLabel("Kacper Chrost"));
        this.add(new JLabel("Krzysztof Funkowski"));
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
                MenuView menuView = new MenuView(user);
                frame.add(menuView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
