import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.List;

public class PieceMoves {

    //pion
    public static List<int[]> getPawnMoves(String[][] board, int row, int col, boolean isWhite, boolean doubleMoveUsed) {
        List<int[]> moves = new ArrayList<>();
        int direction = isWhite ? -1 : 1;

        //muta 2 patrate
        if (!doubleMoveUsed && ((isWhite && row == 6) || (!isWhite && row == 1))) {  // Pionul se află pe linia de start
            if (isInBounds(row + 2 * direction, col) && board[row + 2 * direction][col] == null && board[row + direction][col] == null) {
                moves.add(new int[]{row + 2 * direction, col});
            }
        }

        //muta 1 patrat
        if (isInBounds(row + direction, col) && board[row + direction][col] == null) {
            moves.add(new int[]{row + direction, col});
        }

        //diagonala
        if (isInBounds(row + direction, col + 1) && board[row + direction][col + 1] != null && !isSameColor(board[row][col], board[row + direction][col + 1])) {
            moves.add(new int[]{row + direction, col + 1});
        }
        if (isInBounds(row + direction, col - 1) && board[row + direction][col - 1] != null && !isSameColor(board[row][col], board[row + direction][col - 1])) {
            moves.add(new int[]{row + direction, col - 1});
        }

        return moves;
    }


    //cal
    public static List<int[]> getKnightMoves(String[][] board, int row, int col) {
        List<int[]> moves = new ArrayList<>();
        int[][] offsets = {
                {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
                {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            if (isInBounds(newRow, newCol) && (board[newRow][newCol] == null || !isSameColor(board[row][col], board[newRow][newCol]))) {
                moves.add(new int[]{newRow, newCol});
            }
        }

        return moves;
    }
    //nebun
    public static List<int[]>getBishopMoves(String[][] board,int row,int col){
        List<int[]>moves=new ArrayList<>();
        int [][] directions={
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] direction : directions){
            int r=row,c=col;
            while(isInBounds(r+direction[0],c+direction[1])){
                r+=direction[0];
                c+=direction[1];

                if(board[r][c]==null){
                    moves.add(new int[]{r,c});
                }else{
                    if(!isSameColor(board[row][col],board[r][c])){
                        moves.add(new int[]{r,c});
                    }
                    break;
                }
            }
        }
        return moves;
    }
    //tura
    public static List<int[]> getRookMoves(String[][] board, int row, int col) {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] direction : directions) {
            int r = row, c = col;
            while (isInBounds(r + direction[0], c + direction[1])) {
                r += direction[0];
                c += direction[1];

                if (board[r][c] == null) {
                    moves.add(new int[]{r, c});
                } else {
                    if (!isSameColor(board[row][col], board[r][c])) {
                        moves.add(new int[]{r, c});
                    }
                    break;
                }
            }
        }

        return moves;
    }
    //rege
    public static List<int[]> getKingMoves(String[][] board, int row, int col) {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            if (isInBounds(r, c) && (board[r][c] == null || !isSameColor(board[row][col], board[r][c]))) {
                moves.add(new int[]{r, c});
            }
        }

        return moves;
    }

    //regina
    public static List<int[]>getQueenMoves(String[][] board, int row,int col){
        List<int[]>moves=new ArrayList();

        moves.addAll(getBishopMoves(board,row,col));
        moves.addAll(getRookMoves(board,row,col));

    return moves;
    }



    //verificam tabla
    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    //verificam culorile
    private static boolean isSameColor(String piece1, String piece2) {
        if (piece1 == null || piece2 == null) return false;
        return piece1.startsWith("white") == piece2.startsWith("white");
    }
}
