package ChessController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import ChessModel.ChessBoard;
import ChessPiecesLogic.*;
import ChessView.GameViewer;

import java.util.Vector;

/** The Controller in MVC */
public class GameController {

    static GameViewer viewer; 
    public static ChessBoard board;

    GameController()
    {
        board = new ChessBoard();
    }
    
    /**	main */
    public static void main(String[] args) throws InterruptedException
    {
        GameController c = new GameController();
        viewer = new GameViewer(c);
    }
    
    /** MenuBar action management */
    public static JMenuBar getMenu() {
        JMenuBar menuBar;
        JMenu menuGame;
        JMenu menuActions;

        ActionListener actionlistener = new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                char command =s.charAt(0);
                switch(command)
                {
                    case 'N':
                        board = new ChessBoard();
                        viewer.pause = false;
                        viewer.repaint();
                        break;
                    case 'R':
                        board = new ChessBoard();
                        viewer.pause = false;
                        viewer.repaint();
                        break;
                    case 'P':
                        viewer.pause = !viewer.pause;
                        break;
                    case 'U':
                        board.undo();
                        viewer.repaint();
                        System.out.println("Undo !!!!"); 
                        break;
                    case 'Q':
                        board.exitGame();
                        System.out.println("Quit!!");
                        break;

                }
            }
        };
       
        menuBar = new JMenuBar(); 
   
        menuGame = new JMenu("Board"); 

        JMenuItem menuitem = new JMenuItem("New Game");
        menuitem.setActionCommand("N");
        menuitem.addActionListener(actionlistener);
        menuGame.add(menuitem);

        menuitem = new JMenuItem("Restart");
        menuitem.setActionCommand("R");
        menuitem.addActionListener(actionlistener);
        menuGame.add(menuitem);

        JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("Pause");
        cbMenuItem.setActionCommand("P");
        cbMenuItem.addActionListener(actionlistener);
        menuGame.add(cbMenuItem);

        menuitem = new JMenuItem("Quit");
        menuitem.setActionCommand("Q");
        menuitem.addActionListener(actionlistener);
        menuGame.add(menuitem);
        
        menuBar.add(menuGame);

        menuActions = new JMenu("Actions");  
        JMenuItem actionitem = new JMenuItem("Undo");
        actionitem.setActionCommand("U");
        actionitem.addActionListener(actionlistener);
        menuActions.add(actionitem);
        
        menuBar.add(menuActions);

        return menuBar;
    }
    
    /** Mouse Click management */
    static boolean movePiece = false;
    public static MouseListener getMouse() {
        MouseListener mouselistener = new MouseListener(){
            public void mouseClicked(MouseEvent e) {

                if(!viewer.pause)
                {
                    int newx = (int) ((board.getWidth() * e.getX()) / viewer.getWidth() ) ;
                    int newy = (int) ((board.getHeight() * (e.getY()-40)) / viewer.getHeight() );
       
                    if(movePiece)
                    {
                        board.moveSelectedPiece(newx, newy);
                        movePiece = false;
                    }
                    else
                    {
                        viewer.mouseX=newx;
                        viewer.mouseY=newy;
                        board.mouseX=newx;
                        board.mouseY=newy;
                        Vector<Pair> moves = board.getValidMoves();
                        viewer.addMoves(moves);
                        viewer.repaint();
                        movePiece = true;
                    }
                }
                else
                {
                    viewer.pause = true;
                }

            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        };
        movePiece = false;
        return mouselistener;

    }

    public Piece getPieces(int player, char name, int index )
    {
        if(player ==1)
            return board.getPieces(board.player1, name, index);
        else
            return board.getPieces(board.player2, name, index);
    }

    /** return board width */
    public int getW()
    { return board.getWidth(); }

    /** return board height */
    public int getH()
    { return board.getHeight(); }

    /** check gameOver */
    public int gameOver()
    {
        if(board.player1.king.isEaten)
        {
            return 1;
        }
        else if(board.player2.king.isEaten)
        {
            return 2;
        }
        else
            return -1;
    }
}
