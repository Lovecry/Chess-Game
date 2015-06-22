package ChessPiecesLogic;

import ChessModel.Cell;

import java.util.Vector;

/** The Queen */
public class Queen extends Piece{

	@Override
	public void move(int x, int y) {
		positions.setCoords(x, y);	
	}

	@Override
	public boolean canMove(int x, int y , int player, Cell [][] g)
    {

        Bishop bis = new Bishop();
        bis.positions.setCoords(positions.getX(), positions.getY());
        boolean bishopMove = bis.canMove(x, y, player, g);
        if(!bishopMove)
        {
            Castle cas = new Castle();
            cas.positions.setCoords(positions.getX(), positions.getY());
            boolean castleMove = cas.canMove(x, y ,player, g);
            return castleMove;
        }
        else
            return true;
        
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
