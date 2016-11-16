package superTTT.AI;

import superTTT.Game.Player;

public class P1PerfectAIPlayer implements Player{

	int move;
	int[] lastSquare;
	int[] oppositeLastSquare;
	boolean[][] playedIn;
	
	public P1PerfectAIPlayer(){
		move = 0;
		playedIn = new boolean[3][3];
	}
	
	@Override
	public int[] getMove(int[] previousMove) {
		move++;
		if(move == 1){
			return new int[] {1,1,1,1};
		} else if(move < 9){
			return new int[] {previousMove[2], previousMove[3], 1, 1};
		} else if(move == 9){
			lastSquare = new int[] {previousMove[2], previousMove[3]};
			oppositeLastSquare = new int[]{2-lastSquare[0], 2-lastSquare[1]};
			return new int[] {previousMove[2], previousMove[3], previousMove[2], previousMove[3]};
		} else if(previousMove[2] == 1 && previousMove[3] == 1){
			if(!playedIn[oppositeLastSquare[0]][oppositeLastSquare[1]]){
				playedIn[oppositeLastSquare[0]][oppositeLastSquare[1]] = true;
				return new int[] {oppositeLastSquare[0], oppositeLastSquare[1], lastSquare[0], lastSquare[1]};
			} else{
				return new int[] {oppositeLastSquare[0], oppositeLastSquare[1], oppositeLastSquare[0], oppositeLastSquare[1]};
			}
		} else{
			if(!playedIn[previousMove[2]][previousMove[3]]){
				playedIn[previousMove[2]][previousMove[3]] = true;
				return new int[] {previousMove[2], previousMove[3], lastSquare[0], lastSquare[1]};
			} else{
				return new int[] {previousMove[2], previousMove[3], oppositeLastSquare[0], oppositeLastSquare[1]};
			}
		}
	}

}
