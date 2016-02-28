package superTTT.Game;


public class BasicTTTBoard extends TTTBoard<BasicSquare> {

	public BasicTTTBoard() {
		super(null);
		squares = new BasicSquare[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				squares[i][j] = new BasicSquare();
			}
		}
	}
	
	public BasicTTTBoard Clone(){
		BasicTTTBoard clone = new BasicTTTBoard();
		for(int i = 0; i < squares.length; i++){
			for(int j = 0; j < squares[i].length; j++){
				clone.squares[i][j] = squares[i][j].Clone();
			}
		}
		return clone;
	}
	
}
