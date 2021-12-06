package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddUserView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public AddUserView(User user) {
        panel = this;
        MenuPanel.bottomPanel = this;
        this.user = user;

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


        JButton accept = new JButton("Akceptuj");
        JButton exit = new JButton("Wstecz");
        JTextField name = new JTextField();
        JTextField password = new JTextField();
        JTextField email = new JTextField();
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(false);

        accept.setFont(SettingsService.GenerateFont());
        accept.setBorder(SettingsService.Border());

        exit.setFont(SettingsService.GenerateFont());
        exit.setBorder(SettingsService.Border());

        name.setFont(SettingsService.GenerateFont());
        name.setBorder(SettingsService.Border());

        password.setFont(SettingsService.GenerateFont());
        password.setBorder(SettingsService.Border());

        email.setFont(SettingsService.GenerateFont());
        email.setBorder(SettingsService.Border());

        checkBox.setFont(SettingsService.GenerateFont());
        checkBox.setBorder(SettingsService.Border());

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersController usersController = new UsersController();
                User newUser = new User();
                newUser.setName(name.getText());
                newUser.setEmail(email.getText());
                newUser.setPasswordHash(password.getText());
                newUser.setRegistrationDate(new Date());
                newUser.setPermission(checkBox.isSelected());
                usersController.createNew(newUser);

                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
                frame.setTitle("Lista użytkowników");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
                frame.setTitle("Lista użytkowników");
            }
        });


        JPanel upper = new JPanel(new GridLayout(0,2));

        JPanel footer = new JPanel(new BorderLayout());

        JPanel footerRight = new JPanel(new BorderLayout());


        this.add(upper,BorderLayout.NORTH);
        this.add(footer,BorderLayout.SOUTH);

        upper.setBorder(SettingsService.Border());

        JLabel nameofuser = new JLabel("Nazwa użytkownika");
        nameofuser.setFont(SettingsService.GenerateFont());
        name.setFont(SettingsService.GenerateFont());
        upper.add(nameofuser);
        upper.add(name);

        JLabel emailL=  new JLabel("E-mail");
        emailL.setFont(SettingsService.GenerateFont());
        email.setFont(SettingsService.GenerateFont());
        upper.add(emailL);
        upper.add(email);

        JLabel passwordL =new JLabel("Hasło");
        upper.add(passwordL);
        upper.add(password);
        passwordL.setFont(SettingsService.GenerateFont());
        password.setFont(SettingsService.GenerateFont());

        JLabel pracownikL =new JLabel("Pracownik");

        upper.add(pracownikL);
        upper.add(checkBox);
        pracownikL.setFont(SettingsService.GenerateFont());
        checkBox.setFont(SettingsService.GenerateFont());

        footer.add(footerRight,BorderLayout.EAST);

        footerRight.add(exit,BorderLayout.EAST);
        footerRight.add(accept,BorderLayout.WEST);

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
                UserListView userListView = new UserListView(user);
                frame.add(userListView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
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
