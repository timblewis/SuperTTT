package superTTT.StandardGUI;

import java.io.IOException;

import javax.swing.JFrame;

import superTTT.AI.AIPlayer;
import superTTT.AI.AnalyzeSTTTGame;
import superTTT.AI.OpeningBookAIPlayer;
import superTTT.Game.Player;
import superTTT.Game.SuperTTTGame;


public class STTTGuiController{
	private SuperTTTGame game;
	private Player[] players;
	private SuperTTTGui gui;
	
	public STTTGuiController(){
		gui = new SuperTTTGui();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		players = new Player[2];
		players[0] = gui;
		players[1] = gui;
		gui.setVisible(true);
	}
	
	public void setPlayer(int number, Player player){
		players[number] = player;
	}
	
	public void startNewGame(){
		game = new SuperTTTGame();
		int[] previousMove = null;
		while(game.getWinner() == 0 && game.getTurn() != 81){
			int[] move = null;
			while(!game.isLegalMove(move)){
				move = players[game.getTurn()%2].getMove(previousMove);
			}
			gui.setInnerChar(move, game.getTurn()%2 == 0 ? 'X' : 'O');
			game.move(move);
			if(game.getController(move[0], move[1]) != 0 && gui.isTaken(move[0], move[1])){
				gui.setChar(move[0], move[1], game.getController(move[0], move[1]) == 0 ? 'X' : 'Y');
			}
			previousMove = move;
		}
	}
	
	public static void main(String[] args){
		STTTGuiController control = new STTTGuiController();
		try{
			control.setPlayer(1, new AIPlayer());
			control.setPlayer(0, new OpeningBookAIPlayer("openingbook_1_0.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		control.startNewGame();
	}
}
