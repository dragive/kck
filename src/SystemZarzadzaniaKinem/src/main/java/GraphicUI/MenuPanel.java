package GraphicUI;

import Back.Models.User;
import GraphicUI.Views.*;

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
    public MenuPanel(User user) {
        bottomPanel = null;
        panel = this;
        this.user = user;
        JLabel userWelcomeMessage = new JLabel("Witaj "+user.getName()+"!");

        this.setMinimumSize(new Dimension(600,300));
        this.setLayout(new GridLayout(1,0));
        this.addKeyListener(this);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();

        JButton filmcategories = new JButton("Kategorie filmów");
        JButton cinemas = new JButton("Kina");
        JButton films = new JButton("Filmy");
        JButton userButton = new JButton("Użytkownik");
        JButton users = new JButton("Użytkownicy");
        JButton credits = new JButton("Twórcy");
        JButton reservation = new JButton("Zarezerwuj");
        JButton logout = new JButton("Wyloguj");

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
                    frame.setTitle("Twórcy");
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
                frame.setTitle("Menu");
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
                    frame.setTitle("Lista kin");
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
                    frame.setTitle("Twoje konto");
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
                    frame.setTitle("Lista użytkowników");
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
                    frame.setTitle("Filmy");
                }
            }
        });
        filmcategories.addActionListener(new ActionListener() {
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
                    frame.setTitle("Kategorie filmów");
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
                    frame.setTitle("Rezerwacja");
                }
            }
        });

        this.add(cinemas);
        this.add(filmcategories);
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
