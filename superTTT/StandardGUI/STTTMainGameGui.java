package superTTT.StandardGUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class STTTMainGameGui extends JPanel {
	SuperTTTBoardGui board;
	
	public STTTMainGameGui(){
		board = new SuperTTTBoardGui(0, 0, 0, 0);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		board.setDimensions(getX(), getY(), getWidth(), getHeight());
		board.paint(g);
	}
	
	public void setChar(int x, int y, char val){
		board.setChar(x, y, val);
	}
	
	public void setInnerChar(int[] square, char val){
		board.setInnerChar(square, val);
		repaint();
	}
	
	public int[] getSquare(int x, int y){
		return board.getSquare(x, y);
	}

	public boolean isTaken(int x, int y) {
		return board.isTaken(x, y);
	}
}
