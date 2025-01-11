import java.util.List;

public class GameLogic {
    private final String[][] boardState;
    private int selectedRow = -1;
    private int selectedCol = -1;
    //piese mutate
    private final boolean[][] hasMoved = new boolean[8][8];  // Ținem evidența mutărilor pieselor
    //pioni au merg 2 patrate
    private final boolean[][] doubleMoveUsed = new boolean[8][8]; // Ținem evidența pionilor care au făcut deja mutarea de două pătrate

    public GameLogic(String[][] initialState) {
        this.boardState = initialState;
    }

    // Selectăm piesa
    public boolean selectPiece(int row, int col) {
        if (boardState[row][col] != null) {
            selectedRow = row;
            selectedCol = col;
            return true;
        }
        return false;
    }

    // Mutăm piesa
    public boolean movePiece(int row, int col) {
        if (selectedRow != -1 && selectedCol != -1) {
            List<int[]> possibleMoves = getPossibleMoves(selectedRow, selectedCol);
            boolean isValidMove = false;

            // Verificăm mutarea
            for (int[] move : possibleMoves) {
                if (move[0] == row && move[1] == col) {
                    isValidMove = true;
                    break;
                }
            }

            if (isValidMove) {
                String piece = boardState[selectedRow][selectedCol];

                //mutare 2 patrate
                if (piece.equals("white_pawn") || piece.equals("black_pawn")) {

                    //mutarea este de 2 patrate si este pe linia de start
                    if (Math.abs(selectedRow - row) == 2 && !doubleMoveUsed[selectedRow][selectedCol]) {
                        doubleMoveUsed[selectedRow][selectedCol] = true;
                    }
                }

                //muta piesa
                boardState[row][col] = boardState[selectedRow][selectedCol];
                boardState[selectedRow][selectedCol] = null;

                //piesa mutate
                hasMoved[row][col] = true;

                //actualizare 2 patrate
                if (piece.equals("white_pawn") || piece.equals("black_pawn")) {
                    doubleMoveUsed[row][col] = doubleMoveUsed[selectedRow][selectedCol];
                }
                selectedRow = -1;
                selectedCol = -1;
                return true;
            }
        }
        return false;
    }
    public List<int[]> getPossibleMoves(int row, int col) {
        String piece = boardState[row][col];
        if (piece == null) return null;

        switch (piece) {
            case "white_knight":
            case "black_knight":
                return PieceMoves.getKnightMoves(boardState, row, col);
            case "white_pawn":
            case "black_pawn":
                return PieceMoves.getPawnMoves(boardState, row, col, piece.startsWith("white"), doubleMoveUsed[row][col]);
            case "white_bishop":
            case "black_bishop":
                return PieceMoves.getBishopMoves(boardState, row,col);
            case "white_rook":
            case "black_rook":
                return PieceMoves.getRookMoves(boardState,row,col);
            case "white_king":
            case "black_king":
                return PieceMoves.getKingMoves(boardState,row,col);
            case "white_queen":
            case "black_queen":
                return PieceMoves.getQueenMoves(boardState,row,col);
        }
        return null;
    }

    public String[][] getBoardState() {
        return boardState;
    }
}
