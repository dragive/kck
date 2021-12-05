package GraphicUI.Views;
import Back.Controllers.FilmController;
import Back.Controllers.ReservationController;
import Back.Controllers.SeansController;
import Back.Models.Reservation;
import Back.Models.Seans;
import Back.Models.User;
import GraphicUI.MenuPanel;
import GraphicUI.Views.MinorPanelsAndUtils.SettingsService;
import GraphicUI.Views.MinorPanelsAndUtils.SimpleGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Set;

public class ReservationView extends JPanel implements KeyListener {
    JPanel panel;
    User user;
    User panelUser;
    public ReservationView(User user, User panelUser, Reservation reservation) {
        System.out.println("RezervationView");
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
        SeansController seansController = new SeansController();
        Seans seans = seansController.getById(reservation.getSeansId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        FilmController filmController = new FilmController();

        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(reservation.isPaid());
        //System.out.println(checkBox.isSelected()+" "+reservation.isPaid());
        JButton exit = new JButton("Wstecz");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(checkBox.isSelected()!=reservation.isPaid()){
                    reservation.setPaid(checkBox.isSelected());
                    ReservationController reservationController = new ReservationController();
                    reservationController.update(reservation);
                }
                JFrame frame = (JFrame) SwingUtilities.windowForComponent(panel);
                frame.remove(panel);
                UserReservationsView reservationView = new UserReservationsView(user,panelUser);
                frame.add(reservationView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationView.requestFocus();
                frame.setTitle("Rezerwacje");
            }
        });

        JPanel simpleGridPanel = new JPanel(new GridLayout(0,2));
        JScrollPane jScrollPane = new JScrollPane(simpleGridPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(jScrollPane,BorderLayout.NORTH);

        JLabel idzieszNa = new JLabel("Idziesz na");
        simpleGridPanel.add(idzieszNa);
        idzieszNa.setFont(SettingsService.GenerateFont());

        JLabel tytulidzieszna = new JLabel(filmController.getById(seans.getFilmId()).getTitle());
        tytulidzieszna.setFont(SettingsService.GenerateFont());
        simpleGridPanel.add(tytulidzieszna);

        JLabel dataSeansu = new JLabel("Data seansu");
        dataSeansu.setFont(SettingsService.GenerateFont());
        simpleGridPanel.add(dataSeansu);

        JLabel dataSeansuWartosc = new JLabel(simpleDateFormat.format(reservation.getDateOfReservation()));
        dataSeansuWartosc.setFont(SettingsService.GenerateFont());
        simpleGridPanel.add(dataSeansuWartosc);

        JLabel datarezerwacji = new JLabel("Data rezerwacji");
        datarezerwacji.setFont(SettingsService.GenerateFont());
        simpleGridPanel.add(datarezerwacji);


        JLabel dataRezerwacjiWartosc = new JLabel(simpleDateFormat.format(reservation.getDateOfCreation()));
        dataRezerwacjiWartosc.setFont(SettingsService.GenerateFont());
        simpleGridPanel.add(dataRezerwacjiWartosc);

        if(user.isPermission()) {
            simpleGridPanel.add(new JPanel());
            JPanel checkboxReservationpanel = new JPanel(new BorderLayout());
            checkboxReservationpanel.add(checkBox,BorderLayout.WEST);
            JLabel oplacona = new JLabel("Opłacona");
            checkboxReservationpanel.add(oplacona,BorderLayout.CENTER);
            oplacona.setFont(SettingsService.GenerateFont());
            simpleGridPanel.add(checkboxReservationpanel);
//            this.add(checkBox);
//            this.add();
        }


        JPanel footer = new JPanel(new BorderLayout());
        JPanel footerRight = new JPanel(new BorderLayout());
        footer.add(footerRight,BorderLayout.EAST);

        this.add(footer,BorderLayout.SOUTH);

        exit.setBorder(SettingsService.Border());
        exit.setFont(SettingsService.GenerateFont());

        footerRight.add(exit,BorderLayout.EAST);
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
                UserReservationsView reservationView = new UserReservationsView(user,panelUser);
                frame.add(reservationView, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reservationView.requestFocus();
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
