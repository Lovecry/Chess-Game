package ChessPiecesLogic;

import ChessModel.Cell;
import ChessView.Player;

import java.util.Vector;

/** The Horse */
public class Horse extends Piece{

	@Override
	public void move(int x, int y) {
		positions.setCoords(x, y);	
	}

	@Override
	public boolean canMove(int x, int y, int player, Cell [][] g)
    {
        String opponent = player%2==0 ? "black" : "white";

        Pair end = new Pair(x, y);
        Pair start = new Pair(positions.getX()-1, positions.getY()-2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()+1, positions.getY()-2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()+2, positions.getY()-1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()+2, positions.getY()+1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()+1, positions.getY()+2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()-1, positions.getY()+2);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()-2, positions.getY()-1);
        if( helperMove(start, end, opponent, g) )
            return true;

        start.setCoords(positions.getX()-2, positions.getY()+1);
        if( helperMove(start, end, opponent, g) )
            return true;
        else
            return false;


	}

    private boolean helperMove(Pair s, Pair e, String opponent, Cell [][] g)
    {
        if( s.getX() == e.getX() && s.getY() == e.getY() && !g[e.getX()][e.getY()].occupied )
            return true;
        else if( s.getX() == e.getX() && s.getY() == e.getY() && g[e.getX()][e.getY()].occupied && g[e.getX()][e.getY()].color.equals(opponent) )
            return true;
        else
            return false;
    }

    @Override
    public void specialMove(String c)
    {
        return;
    }

    @Override
    public Vector<Pair> addMoves(int player, Cell[][] g) {
        Vector<Pair> values = new Vector<Pair>();

        for(int i = 0; i < 8;i++ )
        {
            for(int j = 0; j < 8;j++)
            {
                if( canMove(i, j, player, g) )
                    values.add(new Pair(i, j) );
            }
        }
        
        return values;
    }
}
