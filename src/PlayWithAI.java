import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayWithAI {

    public void startGame(JFrame frame) {
        Board board=new Board();
        board.displayBoard();
    }
}
