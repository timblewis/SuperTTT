package superTTT.android.twoplayer;

import android.graphics.*;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.animation.Animation;

public class TTTBoardGui extends Drawable{
	private int boardColor;
	private int opacity;
	protected char[][] squares;
	protected int x, y, width;
	
	public TTTBoardGui(int x, int y, int width){
		squares = new char[3][3];
		setDimensions(x, y, width);
		boardColor = Color.WHITE;
	}
	
	public void setDimensions(int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	public void setChar(int x, int y, char val){
		squares[x][y] = val;
	}
	
	public void setBoardColor(int color){
		boardColor = color;
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
		square[1] = (int)((this.y + width*0.95 - y)/(width*0.3));
		return (square[0] >= 0 && square[0] < 3 && square[1] >= 0 && square[1] < 3) ? square : null;
	}

	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAlpha(opacity);
		paint.setStrokeWidth(width/100);
		paint.setColor(boardColor);
		for(int i = 1; i < 3; i++){
			canvas.drawLine((int)(width*(i*0.3 + 0.05) + x), y + (int)(width*0.05), (int)(width*(i*0.3 + 0.05)) + x, y + (int)(width*0.95), paint);
			canvas.drawLine((int) (width*0.05) + x, (int) (width*(i*0.3 + 0.05)  + y), (int) (width*0.95) + x, (int) (width*(i*0.3 + 0.05)  + y), paint);
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(squares[i][j] == 'X'){
					paint.setColor(Color.BLUE);
				} else{
					paint.setColor(Color.RED);
				}
				canvas.drawText("" + squares[i][j], (int)(x + width*(0.2 + i*0.3)) - paint.measureText("" + squares[i][j])/2, (int)(y + width*(0.80 - j*0.3)) - paint.ascent()/2, paint);
			}
		}
	}

	@Override
	public int getOpacity() {
		return opacity;
	}

	@Override
	public void setAlpha(int alpha) {
		opacity = alpha;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		
	}
}
