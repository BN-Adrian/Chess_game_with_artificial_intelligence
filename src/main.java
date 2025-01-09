import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    public static void main(String[] argv) {
        //fereastra principala
        JFrame frame = new JFrame("Meniu principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200);
        frame.setLayout(new GridBagLayout());

        //container butoane
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(1,2,10,10));


        //buton pentru jocul cu pc-ul
        JButton playWithAIButton =new JButton("Joaca cu pc-ul");
        playWithAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayWithAI game=new PlayWithAI();
                game.startGame(frame);
            }
        });


        //buton pentru jocul in doi
        JButton playWithMeButton =new JButton("joc in doi");
        playWithMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayWithMe game=new PlayWithMe();
                game.startGame(frame);
            }
        });


        //butoane in container
        panel.add(playWithMeButton);
        panel.add(playWithAIButton);

        //adaug container in fereastra
        frame.add(panel, new GridBagConstraints());

        //afisare
        frame.setVisible(true);
    }

}