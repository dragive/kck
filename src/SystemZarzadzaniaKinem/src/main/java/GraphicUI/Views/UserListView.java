package GraphicUI.Views;
import Back.Controllers.UsersController;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserListView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public UserListView(User user) {
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

        UsersController usersController = new UsersController();
        List<User> userList = usersController.getAll();

        JButton addUser = new JButton("Dodaj użytkownika");
        addUser.setFont(SettingsService.GenerateFont());
        addUser.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                AddUserView addUserView = new AddUserView(user);
                frame.add(addUserView);
                frame.revalidate();
                frame.repaint();
                addUserView.requestFocus();
                frame.setTitle("Dodaj użytkownika");
            }
        });

        userList = userList.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());



        SimpleGridPanel simpleGridPanel = new SimpleGridPanel();
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.CENTER);


        addUser.setBorder(SettingsService.Border());

        addUser.setFont(SettingsService.GenerateFont());

        userList = userList.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());

        for(User item : userList) {
            JButton temp = new JButton(item.getName());
            temp.setBorder(SettingsService.Border());
            temp.setFont(SettingsService.GenerateFont());
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(panel);
                    UserView cinemaListView = new UserView(user,item,this);
                    frame.add(cinemaListView);
                    frame.revalidate();
                    frame.repaint();
                    cinemaListView.requestFocus();
                    frame.setTitle("Użytkownik");
                }
            });
            simpleGridPanel.add(temp);
            temp.setFont(SettingsService.GenerateFont());
        }

        JPanel footer = new JPanel(new BorderLayout());
        this.add(footer,BorderLayout.SOUTH);

        footer.add(addUser,BorderLayout.EAST);

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
