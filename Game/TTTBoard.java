package superTTT.Game;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class TTTBoard<E extends InnerSquare> implements InnerSquare{
	protected int winner;
	protected E[][] squares;
	private static final int SQ_NUM = 3;
	
	public TTTBoard(E[][] squares){
		this.squares = squares;
	}
	
	@Override
	public int getController(){
		return winner;
	}
	
	public void setController(int controller){
		winner = controller;
	}
	
	public TTTBoard<E> Clone(){
		TTTBoard<E> clone;
		E[][] squaresClone = squares.clone();
		for(int i = 0; i < squares.length; i++){
			for(int j = 0; j < squares[i].length; j++){
				squaresClone[i][j] = (E) squares[i][j].Clone();
			}
		}
		clone = new TTTBoard<E>(squaresClone);
		return clone;
	}
	
	public boolean hasOpen(){
		for(int i = 0; i < SQ_NUM; i++){
			for(int j = 0; j < SQ_NUM; j++){
				if(squares[i][j].getController() == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public int getAllDiagonals(){
		int total = 0;
		for(int i = 0; i < SQ_NUM; i++){
			total = total|getDiagonalScore(0, i, 1, 0)|getDiagonalScore(i, 0, 0, 1);
		}
		total = total|getDiagonalScore(0,0,1,1)|getDiagonalScore(0, SQ_NUM - 1, 1, -1);
		return total;
	}
	
	public int getDiagonals(int x, int y){
		return getDiagonalScore(x,y,1,0)|getDiagonalScore(x,y,0,1)|getDiagonalScore(x,y,1,1)|getDiagonalScore(x,y,1,-1);
	}
	
	public int getDiagonalScore(int x, int y, int dx, int dy){
		int total = 0;
		for(int i = 0; x + i*dx < SQ_NUM && y + i*dy < SQ_NUM && x + i*dx >= 0 && y + i*dy >= 0; i++){
			total += squares[x + i*dx][y + i*dy].getController();
		}
		for(int i = 1; x - i*dx < SQ_NUM && y - i*dy < SQ_NUM && x - i*dx >= 0 && y - i*dy >= 0; i++){
			total += squares[x - i*dx][y - i*dy].getController();
		}
		if(Math.abs(total) == SQ_NUM){
			return total/SQ_NUM;
		}
		return 0;
	}
	
	@Override
	public double getPercentControlled() { //TODO change so that different number of squares owned give different values
		if(winner == 0){
			double total = 0;
			for(int i = 0; i < SQ_NUM; i++){
				total += getDiagonalPercent(0, i, 1, 0) + getDiagonalPercent(i, 0, 0, 1);
			}
			total += getDiagonalPercent(0, 0, 1, 1) + getDiagonalPercent(0, SQ_NUM - 1, 1, -1);
			return total/8;
		} else{
			return winner;
		}
	}
	
	public double getDiagonalPercent(int x, int y, int dx, int dy){
		double total = 0;
		int reference = 0;
		for(int i = 0; x + i*dx < SQ_NUM && y + i*dy < SQ_NUM && x + i*dx >= 0 && y + i*dy >= 0; i++){
			double value = squares[x + i*dx][y + i*dy].getPercentControlled();
			if(Math.abs(value) == 1){
				if(reference == 0){
					reference = (int) value;
				} else if(reference != value){
					return 0;
				}
			} 
			total += value;
		}
		for(int i = 1; x - i*dx < SQ_NUM && y - i*dy < SQ_NUM && x - i*dx >= 0 && y - i*dy >= 0; i++){
			double value = squares[x - i*dx][y - i*dy].getPercentControlled();
			if(Math.abs(value) == 1){
				if(reference == 0){
					reference = (int) value;
				} else if(reference != value){
					return 0;
				}
			} 
			total += value;
		}
		return total/SQ_NUM;
	}
	
	public ArrayList<int[]> getOpenSquares(){
		ArrayList<int[]> openSquares = new ArrayList<int[]>();
		for(int i = 0; i < SQ_NUM; i++){
			for(int j = 0; j< SQ_NUM; j++){
				if(squares[i][j].getController() == 0){
					int[] array = {i, j};
					openSquares.add(array);
				}
			}
		}
		return openSquares;
	}
	
	public E getSquare(int x, int y){
		return squares[x][y];
	}
}
