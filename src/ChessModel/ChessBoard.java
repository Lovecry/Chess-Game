package ChessModel;

import java.util.Vector;
import ChessPiecesLogic.Pair;
import ChessPiecesLogic.Piece;
import ChessView.Player;

/** The chessBoard Model in MVC*/
public class ChessBoard implements IBoard{

    public Player player1;
    public Player player2;

	private  final int width = 8;
	private final int height = 8;
	public Cell [][] grid = new Cell[width][height];
    public int mouseX, mouseY;
    public int turn;
    private Mover undo;

	
	public ChessBoard() {

        undo = new Mover();
        turn=0;
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				grid[i][j] = new Cell();
			}
		}
		
		player1 = new Player("white", grid);
		player2 = new Player("black", grid);
	
	}
	
	/** return the piece */
	public Piece getPieces(Player player, char name, int index)
	{
		Piece p;
		switch (name) {
        case 'P':
        		if(player == player1)
        			{p= player1.pawns[index];}
        		else
        			{p= player2.pawns[index];}      		
                break;
        case 'C':
    			if(player == player1)
    				{p= player1.castles[index];}
        		else
        			{p= player2.castles[index];} 
    			break;
        case 'H':
        		if(player == player1)
        			{p= player1.horses[index];}
        		else
        			{p= player2.horses[index];}
        		break;
        case 'B':
	    		if(player == player1)
					{p= player1.bishops[index];}
				else
					{p= player2.bishops[index];}      		
		        break;
        case 'Q':
        		if(player == player1)
        			{p= player1.queen;}
        		else
        			{p= player2.queen;}      		
        		break;
        case 'K':
    			if(player == player1)
    				{p= player1.king;}
    			else
    				{p= player2.king;}      		
    			break;
        default : 
        		p = null;
        		System.out.println("Invalid getPiece Selection");
        		break;
		}

		return p;
	}

	/** move selected piece to cell X Y */
    public void moveSelectedPiece(int newX, int newY)
    {
        Piece pieceSelected = selection();
        boolean moved = false;

        if(pieceSelected != null)
        {
            if(turn%2 == 0)
            {
                moved = movePiece(pieceSelected, newX, newY, turn);

                if(moved)
                {
                    grid[mouseX][mouseY].occupied= false;
                    grid[newX][newY].occupied= true;
                    grid[newX][newY].color = "white";

                    Piece eaten = player2.checkEaten(newX, newY);
                    undo.update(pieceSelected, eaten, "white",new Pair(mouseX, mouseY), new Pair(newX, newY) );
                    pieceSelected.selected = false;
                    turn++;
                }

            }
            else
            {
                moved = player2.movePiece(pieceSelected, newX, newY, turn, grid);
                if(moved)
                {
                    grid[mouseX][mouseY].occupied= false;
                    grid[newX][newY].occupied= true;
                    grid[newX][newY].color = "black";

                    Piece eaten = player1.checkEaten(newX, newY);
                    undo.update(pieceSelected, eaten,"black" ,new Pair(mouseX, mouseY), new Pair(newX, newY) );
                    pieceSelected.selected = false;
                    turn++;
                }
            }
            printGrid();
        }
    }

    /** return valid piece selected */
    private Piece selection()
    {
        if(turn%2==0)
            return player1.validPieceSelected(mouseX, mouseY);
        else
            return player2.validPieceSelected(mouseX, mouseY);
    }


    /** move selected piece */
    public boolean movePiece(Piece pSelected, int x, int y, int turn )
    {
        if(turn%2==0)
            return player1.movePiece(pSelected, x, y, turn, grid);
        else
            return player2.movePiece(pSelected, x, y, turn, grid);
    }
    
    /** return valid moves */
    public Vector<Pair> getValidMoves()
    {
       Vector<Pair> moves;

        int p;
        if(turn%2==0)
            p =0;
        else
            p = 1;

       Piece selected = selection();

        if(selected != null)
        {
           moves = selected.addMoves(p, grid);
           return moves;
        }

        return null;

    }

    /** undo move */
    public void undo()
    {
        if(turn ==0)
            return;

        undo.movePrev(grid);
        turn++;

    }

    /** exit the game */
    public void exitGame()
    {
    	System.exit(0);
    }

    /** print the grid */
	public void printGrid() 
	{
		System.out.println("Begin");
		for(int j = 0; j < height; j++)
		{
			for(int i =0; i < width;i++ )
			{
				if(grid[i][j].occupied)
				{
					if(grid[i][j].color.equals("white"))
						System.out.print( "W ");
					else
						System.out.print("B ");
				}
				else
					System.out.print(". ");
			}
			System.out.println();
		}
		System.out.println("End");
	}

	/** get width */
	public int getWidth()
	{	return width;	}

	/** get height */
	public int getHeight()
	{	return height;	}

}
