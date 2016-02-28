package superTTT.StandardGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import superTTT.Game.Player;



public class StandardInputPlayer implements Player{

	@Override
	public int[] getMove(int[] previousMove) {
		int[] move = new int[4];
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 4; i++){
			int choice = - 1;
			while(choice < 0 || choice > 2){
				try {
					System.out.println("Please pick 0-2 for dim " + i);
					System.out.print(">>");
					choice = Integer.parseInt(stdin.readLine());
				} catch (Exception e) {
					System.out.println("Incorrect Choice");
				}
			}
			move[i] = choice;
		}
		return move;
	}
	
	public static void main(String[] args){
		Player[] players = new Player[2];
		players[0] = new StandardInputPlayer();
		players[1] = new StandardInputPlayer();
		StandardInputSTTTController game = new StandardInputSTTTController(players[0], players[1]);
	}
}
