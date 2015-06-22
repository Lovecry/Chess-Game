package ChessPiecesLogic;

import java.util.Vector;

import ChessModel.Cell;
import ChessView.Player;

/** abstract class for a General Piece */

public abstract class Piece {
	
	public Pair positions;
	public boolean isEaten;
	public boolean selected;

	public Piece() {		
		isEaten = false;
		selected = false;
		positions = new Pair(0,0);
	}
	
	public abstract void move(int x, int y);
	
	public abstract boolean canMove(int x, int y, int player, Cell [][] g);

    public abstract void specialMove(String c);

    public abstract Vector<Pair> addMoves(int player, Cell[][] g);

}
