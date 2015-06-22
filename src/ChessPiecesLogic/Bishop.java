package ChessPiecesLogic;

import ChessModel.Cell;
import ChessView.Player;

import java.util.Vector;

/** the Bishop */
public class Bishop extends Piece{

	@Override
	public void move(int x, int y) {
		positions.setCoords(x, y);	
	}

	@Override
	public boolean canMove(int x, int y , int player, Cell [][] g) 
	{
		if(positions.getX() == x || positions.getY() == y)
			return false;

		int direction = getDirection(x, y);
		Pair end = new Pair(x, y);
		Pair start = new Pair(positions.getX(), positions.getY());
		
		if(player%2 == 0) //white bishop
		{ 
			return recurseCheck(start, end,"black", direction, g, 0);
		}
		else if(player%2 != 0) //black bishop
		{
			return recurseCheck(start, end, "white", direction, g, 0);
		}
		
		return true;
	}
	
	private int getDirection(int x, int y) 
	{
		if(x > positions.getX())
		{	
			if(y >positions.getY()) 
			{
				return 2; //down right
			}	
			else 
			{
				return 1; //up right
			}	
		}
		else
		{	
			if(y >positions.getY()) 
			{
				return 3; //down left
			}	
			else 
			{
				return 4; //up left
			}	
		}
	
	}

	private boolean recurseCheck(Pair s, Pair e, String opponent, int direction, Cell [][] g, int count)
	{
		
		if(s.equal(e) && !g[e.getX()][e.getY()].occupied) // s=e and e not occupied
			return true;
		else if(s.equal(e) && g[e.getX()][e.getY()].occupied && g[e.getX()][e.getY()].color.equals(opponent)) // s=e e occupied by opponent
			return true;
		else if(g[s.getX()][s.getY()].occupied && count > 0) // if path occupied (not start pair)
			return false;
		else
		{
			if(direction == 1)
			{
				s.setCoords(s.getX()+1, s.getY()-1);
				if(s.getX() > e.getX() || s.getY() < e.getY())
					return false;
				return recurseCheck(s, e, opponent, direction, g, 1);
			}
			else if(direction == 2)
			{
				s.setCoords(s.getX()+1, s.getY()+1);
				if(s.getX() > e.getX() || s.getY() > e.getY())
					return false;
				return recurseCheck(s, e, opponent, direction, g, 1);
			}
			else if(direction == 3)
			{
				s.setCoords(s.getX()-1, s.getY()+1);
				if(s.getX() < e.getX() || s.getY() > e.getY())
					return false;
				return recurseCheck(s, e, opponent, direction, g, 1);
			}
			else
			{
				s.setCoords(s.getX()-1, s.getY()-1);
				if(s.getX() < e.getX() || s.getY() < e.getY())
					return false;
				return recurseCheck(s, e, opponent, direction, g, 1);
			}
		}
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
