package ChessModel;

/** the cell of the chess board */
public class Cell {
	public String color;
	public boolean occupied;
	
	public Cell()
	{
		color = null;
		occupied = false;
	}
	
	public Cell(String c) {
		color = c;
		if(c != null)
			occupied= true;
		else
			occupied = false;
	}

}
