package superTTT.AI;

import java.util.ArrayList;

import superTTT.Game.SuperTTTGame;



public class AnalyzeSTTTGame extends SuperTTTGame{
	
	public STTTPosScore  AnalyzeDepth(int depth){
		int player = -2*(turn%2) + 1;
		STTTPosScore maxScore = (player == -1)? new STTTPosScore(1,0) : new STTTPosScore(-1,0);
		return AnalyzeDepth(depth, maxScore);
	}
	
	public STTTPosScore AnalyzeDepth(int depth, STTTPosScore maxScore){
		if(depth < 1 || board.getController() != 0){
			return new STTTPosScore(board.getPercentControlled(),0);
		} else{
			int player = -2*(turn%2) + 1;
			ArrayList<int[]> legalMoves = this.getLegalMoves();
			if(!legalMoves.isEmpty()){
				STTTPosScore bestScore = (player == -1)? new STTTPosScore(1,0) : new STTTPosScore(-1,0);
				for(int i = 0; i < legalMoves.size(); i++){
					move(legalMoves.get(i));
					STTTPosScore newScore = AnalyzeDepth(depth - 1, bestScore);
					newScore.incrementDepth();
					if(newScore.compareTo(bestScore)*player > 0){
						bestScore = newScore;
					}
					previousMove();
					if(bestScore.compareTo(maxScore)*player >= 0){
						return bestScore;
					}
				}
				return bestScore;
			} else{
				return new STTTPosScore(0,0);
			}
		}
	}
	
	public int[] FindBestMove(int depth){
		int player = -2*(turn%2) + 1;
		ArrayList<int[]> legalMoves = this.getLegalMoves();
		int[] bestMove = new int[4];
		STTTPosScore bestScore = (player == -1)? new STTTPosScore(1,0) : new STTTPosScore(-1,0);
		for(int i = 0; i < legalMoves.size(); i++){
			move(legalMoves.get(i));
			STTTPosScore newScore = AnalyzeDepth(depth - 1, bestScore);
			if(newScore.compareTo(bestScore)*player > 0){
				bestMove = legalMoves.get(i);
				bestScore = newScore;
			}
			previousMove();
		}
		System.out.println(bestScore.getValue() + " " + bestScore.getDepth());
		return bestMove;
	}
}
