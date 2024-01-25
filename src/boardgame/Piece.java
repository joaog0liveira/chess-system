package boardgame;

public class Piece {
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null; // uma nova peça criada não terá posição, ou seja, será uma posição nula
    }

    protected  Board getBoard() {
        return board;
    }

}
