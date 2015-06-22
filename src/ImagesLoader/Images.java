package ImagesLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** load all required images */
public class Images {

	public BufferedImage whitePawnImage;
	public BufferedImage whiteCastleImage;
	public BufferedImage whiteHorseImage;
	public BufferedImage whiteBishopImage;
	public BufferedImage whiteQueenImage;
	public BufferedImage whiteKingImage;
	public BufferedImage blackPawnImage;
	public BufferedImage blackCastleImage;
	public BufferedImage blackHorseImage;
	public BufferedImage blackBishopImage;
	public BufferedImage blackQueenImage;
	public BufferedImage blackKingImage;

	public Images() {
		
		try {
			whitePawnImage = ImageIO.read(new File("ChessPieces/White_pawn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			whiteCastleImage = ImageIO.read(new File("ChessPieces/White_castle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			whiteHorseImage = ImageIO.read(new File("ChessPieces/White_horse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			whiteBishopImage = ImageIO.read(new File("ChessPieces/White_bishop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			whiteQueenImage = ImageIO.read(new File("ChessPieces/White_queen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			whiteKingImage = ImageIO.read(new File("ChessPieces/White_king.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			blackPawnImage = ImageIO.read(new File("ChessPieces/Black_pawn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			blackCastleImage = ImageIO.read(new File("ChessPieces/Black_castle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			blackHorseImage = ImageIO.read(new File("ChessPieces/Black_horse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			blackBishopImage = ImageIO.read(new File("ChessPieces/Black_bishop.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			blackQueenImage = ImageIO.read(new File("ChessPieces/Black_queen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			blackKingImage = ImageIO.read(new File("ChessPieces/Black_king.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
