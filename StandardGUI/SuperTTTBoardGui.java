package superTTT.StandardGUI;

import java.awt.Graphics;

public class SuperTTTBoardGui extends TTTBoardGui {
	private TTTBoardGui[][] innerBoards;

	public SuperTTTBoardGui(int x, int y, int width, int height) {
		super(x, y, width, height);
		innerBoards = new TTTBoardGui[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				innerBoards[i][j] = new TTTBoardGui(0,0,0,0);
			}
		}
		setDimensions(x,y,width,height);
	}
	
	public void setInnerChar(int[] square, char val){
		innerBoards[square[0]][square[1]].setChar(square[2], square[3], val);
	}
	
	@Override
	public void setDimensions(int x, int y, int width, int height){
		super.setDimensions(x, y, width, height);
		if(innerBoards != null){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					innerBoards[i][j].setDimensions(x + (int)(width*(0.05 + i*0.3)), y + (int)(height*(0.65 - j*0.3)), (int)(width*0.3), (int)(height*0.3));
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics g){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				innerBoards[i][j].paint(g);
			}
		}
		super.paint(g);
	}
	
	@Override
	public int[] getSquare(int x, int y){
		int[] square = new int[4];
		int[] outer = super.getSquare(x, y);
		if(outer != null){
			square[0] = outer[0];
			square[1] = outer[1];
			int[] inner = innerBoards[outer[0]][outer[1]].getSquare(x, y);
			if(inner != null){
				square[2] = inner[0];
				square[3] = inner[1];
				return square;
			}
		}
		return null;
	}
}
