package GraphicUI;

import Back.Models.User;
import GraphicUI.Views.*;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuPanel extends JPanel implements KeyListener {
    JPanel panel;
    public static JPanel bottomPanel;
    public static User user;
    public final Font FONT = SettingsService.GenerateFont();
    public MenuPanel(User user) {
        bottomPanel = null;
        panel = this;
        this.user = user;
        JLabel userWelcomeMessage = new JLabel("Witaj "+user.getName()+"!");

        this.setMinimumSize(new Dimension(600,300));
//        this.setLayout(new GridLayout(1,0));
        this.addKeyListener(this);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();
        
        JButton filmCategories = new JButton("Kategorie filmów");
        filmCategories.setFont(FONT);
        JButton cinemas = new JButton("Kina");
        cinemas.setFont(FONT);
        JButton films = new JButton("Filmy");
        films.setFont(FONT);
        JButton userButton = new JButton("Użytkownik");userButton.setFont(FONT);
        JButton users = new JButton("Użytkownicy");users.setFont(FONT);
        JButton credits = new JButton("Twórcy");credits.setFont(FONT);
        JButton reservation = new JButton("Zarezerwuj");reservation.setFont(FONT);
        JButton logout = new JButton("Wyloguj");logout.setFont(FONT);

        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("CreditsView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    CreditsView creditsView = new CreditsView();
                    frame.add(creditsView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    creditsView.requestFocus();
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                frame.remove(bottomPanel);
                LoginView loginView = new LoginView();
                frame.add(loginView, BorderLayout.PAGE_START);
                frame.revalidate();
                frame.repaint();
                loginView.requestFocus();
            }
        });
        cinemas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("CinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    CinemaListView cinemaListView = new CinemaListView(user);
                    frame.add(cinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    cinemaListView.requestFocus();
                }
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("UserView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    UserView userView = new UserView(user,user,this);
                    frame.add(userView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    userView.requestFocus();
                }
            }
        });
        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("UserListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    UserListView userListView = new UserListView(user);
                    frame.add(userListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    userListView.requestFocus();
                }
            }
        });
        films.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("MenuFilmView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    MenuFilmView menuFilmView = new MenuFilmView(user);
                    frame.add(menuFilmView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    menuFilmView.requestFocus();
                }
            }
        });
        filmCategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("FilmCategoryListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    FilmCategoryListView filmCategoryListView = new FilmCategoryListView(user);
                    frame.add(filmCategoryListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    filmCategoryListView.requestFocus();
                }
            }
        });
        reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bottomPanel.getClass().toString().contains("ReservationCinemaListView")) {
                    JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                    frame.remove(bottomPanel);
                    ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                    frame.add(reservationCinemaListView, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                    reservationCinemaListView.requestFocus();
                }
            }
        });

        this.add(cinemas);
        this.add(filmCategories);
        this.add(films);
        this.add(reservation);
        this.add(userButton);
        if(user.isPermission()) {
            this.add(users);
        }
        this.add(credits);
        this.add(logout);
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
                frame.remove(bottomPanel);
                LoginView loginView = new LoginView();
                frame.add(loginView, BorderLayout.PAGE_START);
                frame.revalidate();
                frame.repaint();
                loginView.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
