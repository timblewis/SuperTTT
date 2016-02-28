package superTTT.AI;

import java.util.ArrayList;

import superTTT.Game.SuperTTTGame;



public class AnalyzeSTTTGame extends SuperTTTGame{
	
	public double AnalyzeDepth(int depth, double maxScore){
		if(depth < 1 || board.getController() != 0){
			return board.getPercentControlled();
		} else{
			int player = -2*(turn%2) + 1;
			ArrayList<int[]> legalMoves = this.getLegalMoves();
			if(!legalMoves.isEmpty()){
				double bestScore = (player == -1)? Double.MAX_VALUE : Double.MIN_VALUE;
				for(int i = 0; i < legalMoves.size() && bestScore*player != 1; i++){
					move(legalMoves.get(i));
					double newScore = AnalyzeDepth(depth - 1, bestScore);
					if(bestScore*player < newScore*player){
						bestScore = newScore;
					}
					previousMove();
					if(maxScore*player <= bestScore*player){
						return bestScore;
					}
				}
				return bestScore;
			} else{
				return 0;
			}
		}
	}
	
	public int[] FindBestMove(int depth){ //TODO make game pick longest forced win it finds
		int player = -2*(turn%2) + 1;
		ArrayList<int[]> legalMoves = this.getLegalMoves();
		int[] bestMove = new int[4];
		double bestScore = (player == -1)? Double.MAX_VALUE : Double.MIN_VALUE;
		for(int i = 0; i < legalMoves.size(); i++){
			move(legalMoves.get(i));
			double newScore = AnalyzeDepth(depth - 1, bestScore);
			if(bestScore*player < newScore*player){
				bestMove = legalMoves.get(i);
				bestScore = newScore;
			}
			previousMove();
		}
		System.out.println(bestScore);
		return bestMove;
	}
}
