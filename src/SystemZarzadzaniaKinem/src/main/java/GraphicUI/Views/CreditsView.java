package GraphicUI.Views;

import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditsView extends JPanel implements KeyListener {
    JPanel panel;
    public CreditsView() {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new BorderLayout());
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

        JPanel panel = new JPanel(new GridLayout(0,1));
        this.add(panel,BorderLayout.NORTH);

        JLabel title = new JLabel("Autorzy Projektu:");
        JLabel empty = new JLabel("");
        JLabel mf = new JLabel("Maciek Fender");
        JLabel kc = new JLabel("Kacper Chrost");
        JLabel kf = new JLabel("Krzysztof Funkowski");

        title.setFont(SettingsService.GenerateFont());
        empty.setFont(SettingsService.GenerateFont());
        mf.setFont(SettingsService.GenerateFont());
        kf.setFont(SettingsService.GenerateFont());
        kc.setFont(SettingsService.GenerateFont());


        panel.add(title);
        panel.add(empty);
        panel.add(mf);
        panel.add(kf);
        panel.add(kc);
        panel.setBorder(SettingsService.Border());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
