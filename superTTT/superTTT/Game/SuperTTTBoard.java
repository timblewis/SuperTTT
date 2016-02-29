package superTTT.Game;


public class SuperTTTBoard extends TTTBoard<BasicTTTBoard>{
	
	public SuperTTTBoard() {
		super(null);
		squares = new BasicTTTBoard[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				squares[i][j] = new BasicTTTBoard();
			}
		}
	}
	
	public SuperTTTBoard Clone(){
		SuperTTTBoard clone = new SuperTTTBoard();
		clone.winner = this.winner;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				clone.squares[i][j] = squares[i][j].Clone();
			}
		}
		return clone;
	}
}
