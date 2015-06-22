package ChessModel;

import ChessPiecesLogic.Piece;
import ChessView.Player;

/**	specifie the interface of the chess board */

public interface IBoard {

    public Piece getPieces(Player player, char name, int index);

    public void moveSelectedPiece(int newX, int newY);

    public void undo();

    public void exitGame();

    public void printGrid();


}
