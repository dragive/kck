package GraphicUI.Views;

import Back.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    public MenuView(User user) {
        panel = this;
        this.user = user;
        JLabel userWelcomeMessage = new JLabel("Witaj "+user.getName()+"!");

        this.setMinimumSize(new Dimension(600,300));
        this.setLayout(new GridLayout(0,2));
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
        JButton exit = new JButton("Wstecz");

        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CreditsView creditsView = new CreditsView(user);
                frame.add(creditsView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                creditsView.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                LoginView loginView = new LoginView();
                frame.add(loginView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                loginView.requestFocus();
            }
        });
        cinemas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                CinemaListView cinemaListView = new CinemaListView(user);
                frame.add(cinemaListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserView cinemaListView = new UserView(user,user,this);
                frame.add(cinemaListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                cinemaListView.requestFocus();
            }
        });
        users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserListView userListView = new UserListView(user);
                frame.add(userListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                userListView.requestFocus();
            }
        });
        films.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                MenuFilmView menuFilmView = new MenuFilmView(user);
                frame.add(menuFilmView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                menuFilmView.requestFocus();
            }
        });
        filmcategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                FilmCategoryListView filmCategoryListView = new FilmCategoryListView(user);
                frame.add(filmCategoryListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                filmCategoryListView.requestFocus();
            }
        });
        reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                ReservationCinemaListView reservationCinemaListView = new ReservationCinemaListView(user);
                frame.add(reservationCinemaListView, new GridBagConstraints());
                frame.revalidate();
                frame.repaint();
                reservationCinemaListView.requestFocus();
            }
        });

        this.add(userWelcomeMessage);
        this.add(new JLabel(""));
        this.add(new JLabel(""));this.add(new JLabel(""));
        this.add(new JLabel("Panel główny programu zarządzania kinem."));
        this.add(new JLabel(""));
        this.add(new JLabel("Wybierz jedną z poniższych opcji."));
        this.add(new JLabel(""));
        this.add(new JLabel(""));this.add(new JLabel(""));
        this.add(cinemas);
        this.add(new JLabel("Zarządzanie kinami"));
        this.add(filmcategories);
        this.add(new JLabel("Zarządzanie kategoriami filmów"));
        this.add(films);
        this.add(new JLabel("Zarządzanie filmami"));
        this.add(reservation);
        this.add(new JLabel("Zarządzanie Rezerwacjami"));
        this.add(userButton);
        this.add(new JLabel("Informacje o aktualnym użytkowniku"));
        if(user.isPermission()) {
            this.add(users);
            this.add(new JLabel("Panel administracyjny użytkowników"));
        }
        this.add(credits);
        this.add(new JLabel("Informacje o twórcach systemu"));
        this.add(new JLabel(""));this.add(new JLabel(""));
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
                LoginView loginView = new LoginView();
                frame.add(loginView, new GridBagConstraints());
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
