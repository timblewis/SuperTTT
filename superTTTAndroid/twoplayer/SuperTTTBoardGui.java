package superTTT.android.twoplayer;

import android.graphics.*;

public class SuperTTTBoardGui extends TTTBoardGui{
	private TTTBoardGui[][] innerBoards;
	public static final int TOOL_HEIGHT = 19; //38 on phone

	public SuperTTTBoardGui(int x, int y, int width, int height) {
		super(x, y, width);
		innerBoards = new TTTBoardGui[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				innerBoards[i][j] = new TTTBoardGui(0,0,0);
			}
		}
		setDimensions(x,y,width);
	}
	
	public TTTBoardGui getInnerBoard(int x, int y){
		return innerBoards[x][y];
	}
	
	@Override
	public void setDimensions(int x, int y, int width){
		super.setDimensions(x, y, width);
		if(innerBoards != null){
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					innerBoards[i][j].setDimensions(x + (int)(width*(0.05 + i*0.3)), y + (int)(width*(0.65 - j*0.3)), (int)(width*0.3));
				}
			}
		}
	}
	
	@Override
	public void draw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		int width = (canvas.getWidth() < canvas.getHeight() - TOOL_HEIGHT) ? canvas.getWidth() : canvas.getHeight() - TOOL_HEIGHT;
		int x = width < canvas.getWidth() ? (canvas.getWidth() - width)/2 : 0;
		int y = width < canvas.getHeight() - TOOL_HEIGHT ? (canvas.getHeight() - width)/2 - TOOL_HEIGHT: 0;
		setDimensions(x ,y ,width);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				innerBoards[i][j].draw(canvas);
			}
		}
		super.draw(canvas);
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
