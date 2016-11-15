package superTTT.Game;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;



public class SuperTTTGame{
	protected int turn;
	protected ArrayList<int[]> moveList;
	protected SuperTTTBoard board;
	
	public SuperTTTGame(){
		turn = 0;
		moveList = new ArrayList<int[]>();
		board = new SuperTTTBoard();
	}
	
	public boolean isLegalMove(int[] move){
		return (move != null) && board.winner == 0 && ((turn == 0) || (move[0] == moveList.get(turn - 1)[2] && move[1] == moveList.get(turn - 1)[3])
				|| !board.getSquare(moveList.get(turn - 1)[2], moveList.get(turn - 1)[3]).hasOpen()) &&
				(board.getSquare(move[0], move[1]).getSquare(move[2], move[3]).getController() == 0);
	}
	
	public void move(int[] move){
		if(isLegalMove(move)){
			int player = -2*(turn%2) + 1;
			BasicTTTBoard innerBoard = board.getSquare(move[0], move[1]);
			innerBoard.getSquare(move[2], move[3]).setController(player);
			if(innerBoard.getDiagonals(move[2], move[3]) == player && innerBoard.getController() == 0){
				innerBoard.setController(player);
				if(board.getDiagonals(move[0], move[1]) == player){
					board.setController(player);
				}
			}
			moveList.add(turn, move);
			turn++;
			while(moveList.size() > turn){
				moveList.remove(moveList.size() - 1);
			}
		}
	}
	
	public ArrayList<int[]> getLegalMoves(){
		ArrayList<int[]> legalMoves = new ArrayList<int[]>();
		if(board.winner == 0){
			if(turn == 0 || !board
					.getSquare(moveList.get(turn - 1)[2], moveList.get(turn - 1)[3])
					.hasOpen()){
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						ArrayList<int[]> movesInSquare = board.getSquare(i, j).getOpenSquares();
						for(int[] innerMove: movesInSquare){
							int[] move = {i, j, innerMove[0], innerMove[1]};
							legalMoves.add(move);
						}
					}
				}
			} else{
				ArrayList<int[]> movesInSquare = board.getSquare(moveList.get(turn - 1)[2], moveList.get(turn - 1)[3]).getOpenSquares();
				for(int[] innerMove: movesInSquare){
					int[] move = {moveList.get(turn-1)[2], moveList.get(turn-1)[3], innerMove[0], innerMove[1]};
					legalMoves.add(move);
				}
			}
		}
		return legalMoves;
	}
	
	public int getWinner(){
		return board.getController();
	}
	
	public void previousMove(){
		if(turn > 0){
			int[] undoMove = moveList.get(turn - 1);
			BasicTTTBoard innerSquare = board.getSquare(undoMove[0], undoMove[1]);
			innerSquare.getSquare(undoMove[2], undoMove[3]).setController(0);
			if(innerSquare.getAllDiagonals() == 0){
				innerSquare.setController(0);
				if(board.getAllDiagonals() == 0){
					board.setController(0);
				}
			}
			turn--;
		}
	}
	
	public int getController(int x, int y){
		return board.getSquare(x, y).getController();
	}
	
	public double getPercentControlled(){
		return board.getPercentControlled();
	}
	
	public int getTurn(){
		return turn;
	}

	public void print() {
		for(int i = 8; i >= 0; i--){
			for(int j = 0; j < 9; j++){
				int controller = board.getSquare(j/3, i/3).getSquare(j%3, i%3).getController();
				if(controller == 1){
					System.out.print('X');
				} else if(controller == -1){
					System.out.print('O');
				} else{
					System.out.print("E");
				}
				if(j%3 == 2){
					System.out.print(" ");
				}
			}
			System.out.println();
			if(i%3 == 0){
				System.out.println();
			}
		}
	}
	
	public void move(int move){
		move(convertMove(move));
	}
	
	public static int convertMove(int[] move){
		return move[0]*27 + move[1]*9 + move[2]*3 + move[3];
	}
	
	public static int[] convertMove(int move){
		return new int[] {move/27, (move/9)%3, (move/3)%3, move%3};
	}
}
