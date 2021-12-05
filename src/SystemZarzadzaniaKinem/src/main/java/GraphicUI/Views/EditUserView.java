package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

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

        JTextField name = new JTextField();
        name.setFont(SettingsService.GenerateFont());
        name.setText(panelUser.getName());
        JTextField email = new JTextField();
        email.setFont(SettingsService.GenerateFont());
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
        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);



        JLabel nameLabel = new JLabel("Nazwa użytkownika");
        nameLabel.setFont(SettingsService.GenerateFont());
        upper.add(nameLabel);
        name.setFont(SettingsService.GenerateFont());
        upper.add(name);

        JLabel emialLabel = new JLabel("Adres e-mail");
        emialLabel.setFont(SettingsService.GenerateFont());
        upper.add(emialLabel);
        upper.add(email);

        if(user.isPermission()) {
            JLabel pracownik = new JLabel("Pracownik");
            pracownik.setFont(SettingsService.GenerateFont());
            upper.add(pracownik);
            upper.add(checkBox);
        }

        JPanel lowerLeft = new JPanel(new BorderLayout());
        lower.add(lowerLeft,BorderLayout.WEST);

        JPanel lowerRight = new JPanel(new BorderLayout());
        lower.add(lowerRight,BorderLayout.EAST);



        lowerRight.add(accept,BorderLayout.WEST);
        accept.setBorder(SettingsService.Border());
        accept.setFont(SettingsService.GenerateFont());

        lowerLeft.add(delete, BorderLayout.CENTER);
        delete.setBorder(SettingsService.Border());
        delete.setFont(SettingsService.GenerateFont());

        lowerRight.add(exit,BorderLayout.EAST);
        exit.setBorder(SettingsService.Border());
        exit.setFont(SettingsService.GenerateFont());
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
