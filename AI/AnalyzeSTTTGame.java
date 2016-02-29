package superTTT.AI;

import java.util.ArrayList;

import superTTT.Game.SuperTTTGame;



public class AnalyzeSTTTGame extends SuperTTTGame{
	
	public Score AnalyzeDepth(int depth, Score maxScore){
		if(depth < 1 || board.getController() != 0){
			return new Score(board.getPercentControlled(),0);
		} else{
			int player = -2*(turn%2) + 1;
			ArrayList<int[]> legalMoves = this.getLegalMoves();
			if(!legalMoves.isEmpty()){
				Score bestScore = (player == -1)? new Score(1,0) : new Score(-1,0);
				for(int i = 0; i < legalMoves.size(); i++){
					move(legalMoves.get(i));
					Score newScore = AnalyzeDepth(depth - 1, bestScore);
					newScore.incrementDepth();
					if(newScore.compareTo(bestScore)*player > 0){
						bestScore = newScore;
					}
					previousMove();
					if(maxScore.compareTo(bestScore)*player >= 0){
						return bestScore;
					}
				}
				return bestScore;
			} else{
				return new Score(0,0);
			}
		}
	}
	
	public int[] FindBestMove(int depth){ //TODO make game pick longest forced win it finds
		int player = -2*(turn%2) + 1;
		ArrayList<int[]> legalMoves = this.getLegalMoves();
		int[] bestMove = new int[4];
		Score bestScore = (player == -1)? new Score(1,0) : new Score(-1,0);
		for(int i = 0; i < legalMoves.size(); i++){
			move(legalMoves.get(i));
			Score newScore = AnalyzeDepth(depth - 1, bestScore);
			if(newScore.compareTo(bestScore)*player > 0){
				bestMove = legalMoves.get(i);
				bestScore = newScore;
			}
			previousMove();
		}
		return bestMove;
	}
}
