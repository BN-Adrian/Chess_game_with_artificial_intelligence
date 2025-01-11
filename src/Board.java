import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public class Board {
    private final String[][] boardState = {
            {"black_rook", "black_knight", "black_bishop", "black_queen", "black_king", "black_bishop", "black_knight", "black_rook"},
            {"black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn", "black_pawn"},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {"white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn", "white_pawn"},
            {"white_rook", "white_knight", "white_bishop", "white_queen", "white_king", "white_bishop", "white_knight", "white_rook"}
    };

    public static final int CELL_SIZE = 75;
    private JPanel[][] squares = new JPanel[8][8];
    private GameLogic gameLogic;
    private Runnable turnSwitcher;
    private Supplier<Boolean> turnChecker;

    public Board() {
        gameLogic = new GameLogic(boardState);
    }

    public void setTurnChecker(Supplier<Boolean> turnChecker) {
        this.turnChecker = turnChecker;
    }

    public void setTurnSwitcher(Runnable turnSwitcher) {
        this.turnSwitcher = turnSwitcher;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void displayBoard() {
        JFrame frame = new JFrame("Tabla de joc");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(8, 8));

        Color lightColor = new Color(255, 255, 255);
        Color darkColor = new Color(46, 139, 87);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground((row + col) % 2 == 0 ? lightColor : darkColor);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                String piece = boardState[row][col];
                if (piece != null) {
                    JLabel label = new JLabel(new ImageIcon(getClass().getResource("image/" + piece + ".png")));
                    square.add(label);
                }

                final int currentRow = row;
                final int currentCol = col;

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleClick(currentRow, currentCol);
                    }
                });

                squares[row][col] = square;
                frame.add(square);
            }
        }

        frame.setVisible(true);
    }


    private void handleClick(int row, int col) {
        String piece = boardState[row][col];

        if (piece != null && isCorrectTurn(piece)) {
            if (gameLogic.selectPiece(row, col)) {
                squares[row][col].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else if (gameLogic.movePiece(row, col)) {
            //schimba randul doar dupa o mutare buna
            refreshBoard();
            turnSwitcher.run();
        } else {
            System.out.println("Mutare invalidă sau rând greșit.");
        }
    }

    private boolean isCorrectTurn(String piece) {
        return (turnChecker.get() && piece.startsWith("white")) || (!turnChecker.get() && piece.startsWith("black"));
    }

    private void refreshBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].removeAll();
                String piece = boardState[row][col];
                if (piece != null) {
                    JLabel label = new JLabel(new ImageIcon(getClass().getResource("image/" + piece + ".png")));
                    squares[row][col].add(label);
                }
                squares[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                squares[row][col].revalidate();
                squares[row][col].repaint();
            }
        }
    }
}
