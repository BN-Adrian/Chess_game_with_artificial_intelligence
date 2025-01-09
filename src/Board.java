import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Board {

    private final String[][] initialSetup = {
            {"black_rook", "black_knight", "black_bishop", "black_queen", "black_king", "black_bishop", "black_knight", "black_rook"},
            {"black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn"},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {"white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn"},
            {"white_rook", "white_knight", "white_bishop", "white_queen", "white_king", "white_bishop", "white_knight", "white_rook"}
    };

    public void displayBoard() {
        JFrame frame = new JFrame("Tabla de joc");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(8, 8));

        // culori
        Color lightColor = new Color(255, 255, 255);
        Color darkColor = new Color(46, 139, 87);

        // patratele și piesele
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }

                String piece = initialSetup[row][col];
                if (piece != null) {
                    // cale imagini
                    String imagePath = "image/" + piece + ".png";
                    URL imageUrl = getClass().getResource(imagePath);
                    if (imageUrl == null) {
                        System.out.println("imaginea nu este: " + imagePath);
                    } else {
                        ImageIcon icon = new ImageIcon(imageUrl);
                        JLabel label = new JLabel(icon);
                        square.add(label);
                    }
                }

                frame.add(square);
            }
        }

        frame.setVisible(true);
    }


}
