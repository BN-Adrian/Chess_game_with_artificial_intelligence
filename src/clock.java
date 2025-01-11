import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clock extends JFrame {
    private static final int INITIAL_TIME = 300; // 5 minute pentru fiecare jucător
    private int whiteTime = INITIAL_TIME;
    private int blackTime = INITIAL_TIME;
    private boolean isWhiteTurn = true; // Alb începe primul
    private Timer timer;
    private JLabel whiteTimeLabel, blackTimeLabel;

    public clock() {
        // Setăm fereastra
        setTitle("Ceas de joc de șah");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        // Etichetele pentru timpul rămas
        whiteTimeLabel = new JLabel("Alb: " + formatTime(whiteTime), SwingConstants.CENTER);
        blackTimeLabel = new JLabel("Negru: " + formatTime(blackTime), SwingConstants.CENTER);

        // Setăm fontul și dimensiunea etichetelor
        whiteTimeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        blackTimeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Adăugăm etichetele în fereastră
        add(whiteTimeLabel);
        add(blackTimeLabel);

        // Buton pentru a porni ceasul
        JButton startButton = new JButton("Începe jocul");
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        setVisible(true);
    }

    // Formatarea timpului pentru afișare
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    // Începerea cronometrului
    private void startTimer() {
        // Timer care se actualizează la fiecare secundă
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isWhiteTurn) {
                    whiteTime--;
                    whiteTimeLabel.setText("Alb: " + formatTime(whiteTime));
                    if (whiteTime <= 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Timpul pentru alb a expirat!");
                    }
                } else {
                    blackTime--;
                    blackTimeLabel.setText("Negru: " + formatTime(blackTime));
                    if (blackTime <= 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Timpul pentru negru a expirat!");
                    }
                }
            }
        });

        timer.start(); // Pornește timer-ul
    }

    // Metoda pentru a comuta între jucători
    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
    }
}
