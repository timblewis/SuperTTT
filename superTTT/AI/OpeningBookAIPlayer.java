package superTTT.AI;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import superTTT.Game.Player;
import superTTT.Game.SuperTTTGame;
import superTTT.OpeningBook.OpeningBook;

public class OpeningBookAIPlayer extends AIPlayer{
	private OpeningBook book;
	private boolean inBook;
	private int node;
	
	public OpeningBookAIPlayer(OpeningBook book, int depth){
		super(depth);
		this.book = book;
		node = OpeningBook.ROOT_INDEX;
		inBook = true;
	}
	
	public OpeningBookAIPlayer(String path, int depth) throws FileNotFoundException, IOException{
		super(depth);
		book = new OpeningBook(path);
		node = OpeningBook.ROOT_INDEX;
		inBook = true;
	}
	
	public OpeningBookAIPlayer(OpeningBook book){
		this(book, DEFAULT_DEPTH);
	}
	
	public OpeningBookAIPlayer(String path) throws FileNotFoundException, IOException{
		this(path, DEFAULT_DEPTH);
	}
	
	@Override
	public int[] getMove(int[] previousMove) {
		List<Integer> children = book.getChildren(node);
		if(!inBook){
			return super.getMove(previousMove);
		}
		if(previousMove != null){
			boolean found = false;
			for(int i=0; i < book.getChildren(node).size() && !found;i++){
				int child = children.get(i);
				if(book.getLastMove(child) == SuperTTTGame.convertMove(previousMove)){
					node = child;
					children = book.getChildren(child);
					found = true;
				}
			}
			inBook = found;
		}
		if(children.isEmpty() || !inBook){
			return super.getMove(previousMove);
		} else{
			if(previousMove != null){
				game.move(previousMove);
			}
			int player = -2*(game.getTurn()%2) + 1;
			double bestScore = (player == -1)? Double.MAX_VALUE : -Double.MAX_VALUE;
			int bestMove = 0;
			for(int i = 0; i < children.size(); i++){
				int child = children.get(i);
				if(bestScore*player < book.getScore(child)*player){
					bestScore = book.getScore(child);
					bestMove = i;
				}
			}
			int[] move = SuperTTTGame.convertMove(book.getLastMove(children.get(bestMove)));
			game.move(move);
			return move;
		}
	}

}
