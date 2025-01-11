import javax.swing.*;

public class PlayWithMe extends JPanel {
    //alb incepe
    private boolean isWhiteTurn = true;
    private Board board;

    public void startGame(JFrame frame) {
        board = new Board();
        board.setTurnChecker(this::isWhiteTurn);
        board.setTurnSwitcher(this::switchTurn);
        board.displayBoard();
        new clock();
    }

    private boolean isWhiteTurn() {
        return isWhiteTurn;
    }
    //schimbam randul
    private void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        System.out.println("Rândul jucătorului: " + (isWhiteTurn ? "alb" : "negru"));
    }
}
