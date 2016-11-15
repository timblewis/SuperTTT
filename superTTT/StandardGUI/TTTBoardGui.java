package superTTT.StandardGUI;

import java.awt.Font;
import java.awt.Graphics;

public class TTTBoardGui{
	private char[][] squares;
	private int x, y, width, height;
	
	public TTTBoardGui(int x, int y, int width, int height){
		squares = new char[3][3];
		setDimensions(x, y, width, height);
	}
	
	public void setDimensions(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setChar(int x, int y, char val){
		squares[x][y] = val;
	}
	
	public void setChar(int[] square, char val){
		squares[square[0]][square[1]] = val;
	}
	
	public boolean isTaken(int x, int y){
		return !(squares[x][y] == 0);
	}
	
	public int[] getSquare(int x, int y){
		int[] square = new int[2];
		square[0] = (int)((x - this.x - width*0.05)/(width*0.3));
		square[1] = (int)((this.y + height*0.95 - y)/(height*0.3));
		return (square[0] >= 0 && square[0] < 3 && square[1] >= 0 && square[1] < 3) ? square : null;
	}
	
	public void paint(Graphics g){
		for(int i = 1; i < 3; i++){
			g.drawLine((int)(width*(i*0.3 + 0.05) + x), y + (int)(height*0.05), (int)(width*(i*0.3 + 0.05)) + x, y + (int)(height*0.95));
			g.drawLine((int) (width*0.05) + x, (int) (height*(i*0.3 + 0.05)  + y), (int) (width*0.95) + x, (int) (height*(i*0.3 + 0.05)  + y));
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				g.drawString("" + squares[i][j], (int)(x + width*(0.2 + i*0.3) - g.getFontMetrics().charWidth(squares[i][j])/2), (int)(y + height*(0.80 - j*0.3)) + g.getFontMetrics().getHeight()/2);
			}
		}
	}
}
