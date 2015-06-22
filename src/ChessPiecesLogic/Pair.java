package ChessPiecesLogic;

/** defines the Pair */
public class Pair {
	
	private int xPos, yPos;	
	
	public Pair(int x, int y) { 
		xPos = x;
		yPos = y;
	}	
	
	public void setCoords(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public boolean equal(Pair p)
	{
		if(xPos == p.getX() && yPos == p.getY())
			return true;
		else
			return false;	
	}	
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
}
