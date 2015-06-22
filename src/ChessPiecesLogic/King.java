package ChessPiecesLogic;

import ChessModel.Cell;
import ChessView.Player;

import java.util.Vector;

/** The king */
public class King extends Piece{

	@Override
	public void move(int x, int y) {
		positions.setCoords(x, y);	
	}

	@Override
	public boolean canMove(int x, int y , int player, Cell [][] g) {
        String opponent = player%2==0 ? "black" : "white";

        if(positions.getX()-1 ==x && positions.getY() == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX()+1 ==x && positions.getY() == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX() ==x && positions.getY()+1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX() ==x && positions.getY()-1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX()+1 ==x && positions.getY()+1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX()-1 ==x && positions.getY()+1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX()+1 ==x && positions.getY()-1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
            return true;
        else if(positions.getX()-1 ==x && positions.getY()-1 == y && (!g[x][y].occupied || g[x][y].color.equals(opponent)) )
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
