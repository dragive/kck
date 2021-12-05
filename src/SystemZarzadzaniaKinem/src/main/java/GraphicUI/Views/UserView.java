package GraphicUI.Views;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class UserView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    Object previousWindow;
    User panelUser;
    public UserView(User user,User panelUser, Object previousWindow) {
        System.out.println("UserView");
        panel = this;
        this.previousWindow = previousWindow;
        this.user = user;
        this.panelUser = panelUser;
        MenuPanel.bottomPanel = this;
        Object previous;
        if(previousWindow.getClass().toString().contains("UserListView"))
        {
            previous = previousWindow;
        }
        else {
            previous = this;
        }

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

        JLabel name = new JLabel(panelUser.getName());
        JLabel email = new JLabel(panelUser.getEmail());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        JLabel date = new JLabel(simpleDateFormat.format(panelUser.getRegistrationDate()));

        JButton edit = new JButton("Edytuj dane");
        JButton reservations = new JButton("Rezerwacje");
        JButton exit = new JButton("Wstecz");

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                EditUserView editUserView = new EditUserView(user,panelUser,previous);
                frame.add(editUserView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                editUserView.requestFocus();
            }
        });

        reservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserReservationsView userReservationsView = new UserReservationsView(user,panelUser);
                frame.add(userReservationsView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                userReservationsView.requestFocus();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(previousWindow.getClass().toString().contains("UserListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    UserListView userListView = new UserListView(user);
                    frame.add(userListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    userListView.requestFocus();
                }
            }
        });

        JPanel upper = new JPanel(new GridLayout(0,2));
        this.add(upper,BorderLayout.NORTH);

        JLabel nameOfUser = new JLabel("Nazwa użytkownika");
        nameOfUser.setFont(SettingsService.GenerateFont());
        upper.add(nameOfUser);
        upper.add(name);

        JLabel userEmail = new JLabel("Adres e-mail");
        userEmail.setFont(SettingsService.GenerateFont());
        upper.add(userEmail);
        upper.add(email);

        JLabel dateOfCreation = new JLabel("Data założenia");
        dateOfCreation.setFont(SettingsService.GenerateFont());
        upper.add(dateOfCreation);
        upper.add(date);

        name.setFont(SettingsService.GenerateFont());
        email.setFont(SettingsService.GenerateFont());
        date.setFont(SettingsService.GenerateFont());


        if(panelUser.isPermission()) {
            upper.add(new JLabel(""));
            JLabel pracownik = new JLabel("Pracownik");
            pracownik.setFont(SettingsService.GenerateFont());
            upper.add(pracownik);
        }



        JPanel lower = new JPanel(new BorderLayout());
        this.add(lower,BorderLayout.SOUTH);

        JPanel lowerLeft = new JPanel(new GridLayout(0,2));
        lower.add(lowerLeft,BorderLayout.WEST);

        JPanel lowerRight = new JPanel(new BorderLayout() );
        lower.add(lowerRight,BorderLayout.NORTH);

        lowerLeft.add(reservations);
        lowerLeft.add(edit);

        reservations.setFont(SettingsService.GenerateFont());
        reservations.setBorder(SettingsService.Border());

        edit.setFont(SettingsService.GenerateFont());
        edit.setBorder(SettingsService.Border());


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                if(previousWindow.getClass().toString().contains("UserListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    UserListView userListView = new UserListView(user);
                    frame.add(userListView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    userListView.requestFocus();
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
