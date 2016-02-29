package superTTT.StandardGUI;
import superTTT.AI.AIPlayer;
import superTTT.Game.Player;
import superTTT.Game.SuperTTTGame;


public class StandardInputSTTTController{
	SuperTTTGame game;
	Player[] players;
	
	public StandardInputSTTTController(Player player1, Player player2){
		players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		game = new SuperTTTGame();
		int[] previousMove = null;
		while(game.getWinner() == 0 && game.getTurn() != 81){
			String outString = "turn: " + game.getTurn() + " score: " + game.getPercentControlled();
			if(previousMove != null){
				outString += " last move " + previousMove[0] + ", " + previousMove[1] + ", " + previousMove[2] + ", " + previousMove[3] + ", ";
			}
			System.out.println(outString);
			int[] move = null;
			while(!game.isLegalMove(move)){
				move = players[game.getTurn()%2].getMove(previousMove);
				if(!game.isLegalMove(move)){
					System.out.println("Illegal move");
				}
			}
			game.move(move);
			previousMove = move;
			game.print();
		}
		System.out.println("winner: " + game.getWinner());
	}
	
	public static void main(String[] args){
		SuperTTTGui player1 = new SuperTTTGui();
		player1.setVisible(true);
		new StandardInputSTTTController(player1, new AIPlayer());
	}
}
