package GraphicUI.Views;
import Back.Models.User;


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
        panel = this;
        this.previousWindow = previousWindow;
        this.user = user;
        this.panelUser = panelUser;
        Object previous;
        if(previousWindow.getClass().toString().contains("UserListView"))
        {
            //System.out.println("brr");
            previous = previousWindow;
        }
        else {
            //System.out.println(previousWindow.getClass().getName());
            previous = this;
        }

        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(new GridLayout(0,2));
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
                frame.add(editUserView, new GridBagConstraints());
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
                frame.add(userReservationsView, new GridBagConstraints());
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
                    frame.add(userListView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    userListView.requestFocus();
                }
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    MenuView menuView = new MenuView(user);
                    frame.add(menuView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
                }
            }
        });

        this.add(new JLabel("Nazwa użytkownika"));
        this.add(name);

        this.add(new JLabel("Adres e-mail"));
        this.add(email);

        this.add(new JLabel("Data założenia"));
        this.add(date);

        if(panelUser.isPermission()) {
            this.add(new JLabel(""));
            this.add(new JLabel("Pracownik"));
        }

        this.add(new JLabel(""));
        this.add(reservations);

        this.add(new JLabel(""));
        this.add(edit);

        this.add(new JLabel(""));
        this.add(exit);
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
                else {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    MenuView menuView = new MenuView(user);
                    frame.add(menuView, new GridBagConstraints());
                    frame.revalidate();
                    frame.repaint();
                    menuView.requestFocus();
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
