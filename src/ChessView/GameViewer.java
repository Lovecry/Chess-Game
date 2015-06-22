package ChessView;

import java.awt.*;
import javax.swing.*;
import ChessController.GameController;
import ChessPiecesLogic.Pair;
import ChessPiecesLogic.Piece;
import ImagesLoader.Images;
import java.util.Vector;

/** The Viewer in MVC */
public class GameViewer extends JPanel {

    public int mouseX, mouseY;
    public boolean pause;
    private Images pieces;
    private int xDimensions, yDimensions;
    private GameController controller;
    private static JFrame frame; 
    private Vector<Pair>moves;


    public GameViewer(GameController c)
    {
        initializeBoard();
        controller = c;        
        setupFrame();
     }
    
    /**	init chessboard */
    public void initializeBoard()
    {
        pieces = new Images();
        pause = false;
    }

    /** setup the frame */
    private void setupFrame()
    {
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(550,550);
        frame.setJMenuBar(controller.getMenu());
        frame.addMouseListener(controller.getMouse());
        frame.setVisible(true);
    }
    
    /** paint board */
    public void paint(Graphics g)
    {
        boardBackground(g);
        highlightMoves(g);
        displayPieces(g);
        repaint();
    }
    
    /** set background */
    private void boardBackground(Graphics g)
    {
        xDimensions=( getWidth() / controller.getW());
        yDimensions=( getHeight()/ controller.getH());

        int count = 0;
        for(int i=0; i< controller.getW();i++)
        {
            for (int j=0; j<controller.getH(); j++)
            {
                if(count%2==0)
                {
                    g.setColor(new Color(255,204,153));
                    g.fillRect(i * xDimensions, j * yDimensions, xDimensions, yDimensions);
                }
                else
                {
                    g.setColor(new Color(153,76,0));
                    g.fillRect(i * xDimensions, j * yDimensions, xDimensions, yDimensions);
                }
                count++;
            }
            count++;
        }

        g.setColor(Color.black);
        g.drawRect(mouseX * xDimensions, mouseY * yDimensions, xDimensions, yDimensions);
    }
    
    /** display pieces */
    private void displayPieces(Graphics g)
    {
        getPieces(g, 1, 'P', 8);
        getPieces(g, 1, 'C', 2);
        getPieces(g, 1, 'B', 2);
        getPieces(g, 1, 'H', 2);
        getPieces(g, 1, 'K', 1);
        getPieces(g, 1, 'Q', 1);

        getPieces(g, 2, 'P', 8);
        getPieces(g, 2, 'C', 2);
        getPieces(g, 2, 'B', 2);
        getPieces(g, 2, 'H', 2);
        getPieces(g, 2, 'K', 1);
        getPieces(g, 2, 'Q', 1);


        if(pause)
        {
            g.setColor( new Color(255, 55, 0) );
            Font p3= new Font("Algerian",Font.PLAIN,100);
            g.setFont(p3);
            g.drawString("Paused", getWidth()/3 - 100,getHeight()/2);
        }

    }

    /** set piece image*/
    private void getPieces(Graphics g, int player, char name, int numElements)
    {
        Image img;
        switch (name) {
            case 'P':
                if(player == 1)
                {img = pieces.whitePawnImage;}
                else
                {img = pieces.blackPawnImage;}
                break;
            case 'C':
                if(player ==1)
                    img = pieces.whiteCastleImage;
                else
                    img = pieces.blackCastleImage;
                break;
            case 'H':
                if(player == 1)
                    img = pieces.whiteHorseImage;
                else
                    img = pieces.blackHorseImage;
                break;
            case 'B':
                if(player == 1)
                    img = pieces.whiteBishopImage;
                else
                    img = pieces.blackBishopImage;
                break;
            case 'Q':
                if(player == 1)
                    img = pieces.whiteQueenImage;
                else
                    img = pieces.blackQueenImage;
                break;
            case 'K':
                if(player == 1)
                    img = pieces.whiteKingImage;
                else
                    img = pieces.blackKingImage;
                break;
            default :
                img = pieces.whitePawnImage;
                System.out.println("Invalid Image Selection");
                break;
        }
        for(int index = 0; index < numElements; index++)
        {
            Piece p = controller.getPieces(player, name, index);
            if(p != null && !p.isEaten )
                g.drawImage(img, xDimensions*p.positions.getX()+7, yDimensions*p.positions.getY(), null);

        }

    }

    /** add move */
    public void addMoves(Vector<Pair> moves){
        this.moves = moves;
    }
    
    /**	highlight moves */
    public void highlightMoves(Graphics g)
    {
        if(moves != null)
        {
            for(int i = 0; i < moves.size(); i++)
            {
                Pair p = moves.elementAt(i);
                g.setColor(Color.green);
                g.fillRect(p.getX() * xDimensions, p.getY() * yDimensions, xDimensions, yDimensions);
            }
        }
    }
}

