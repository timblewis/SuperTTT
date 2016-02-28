package superTTT.StandardGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import superTTT.Game.Player;


public class SuperTTTGui extends JFrame implements Player{
	STTTMainGameGui board;
	int[] move;
	
	public SuperTTTGui(){
		board = new STTTMainGameGui();
		board.addMouseListener(new BoardListener());
		this.add(board);
	}
	
	private class BoardListener extends MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent arg0){
			move = board.getSquare(arg0.getX(), arg0.getY());
		}
	}
	
	public void setInnerChar(int[] square, char val){
		board.setInnerChar(square, val);
	}
	
	public void setChar(int x, int y, char val){
		board.setChar(x, y, val);
	}

	@Override
	public int[] getMove(int[] previousMove) {
		while(move == null){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int[] theMove = move;
		move = null;
		return theMove;
	}

	public boolean isTaken(int x, int y) {
		return board.isTaken(x, y);
	}
}
