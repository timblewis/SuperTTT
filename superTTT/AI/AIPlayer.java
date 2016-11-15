package superTTT.AI;
import superTTT.Game.Player;
import superTTT.StandardGUI.StandardInputSTTTController;


public class AIPlayer implements Player {
	protected AnalyzeSTTTGame game;
	protected int depth;
	
	public static final int DEFAULT_DEPTH = 8;
	
	public AIPlayer(){
		game = new AnalyzeSTTTGame();
		depth = DEFAULT_DEPTH;
	}
	
	public AIPlayer(int depth){
		game = new AnalyzeSTTTGame();
		this.depth = depth;
	}

	@Override
	public int[] getMove(int[] previousMove) {
		if(previousMove != null){
			game.move(previousMove);
		}
		int realDepth = (int)(depth*Math.log(9)/Math.log(Math.max(9-game.getTurn()/9.0,2.0))); //TODO fix if number of turns gets low enough
		System.out.print(game.getTurn() + " " + realDepth + " ");
		int[] move = game.FindBestMove(realDepth);
		game.move(move);
		return move;
	}
	
	public static void main(String[] args){
		StandardInputSTTTController control = new StandardInputSTTTController(new AIPlayer(), new AIPlayer(8));
	}
}
