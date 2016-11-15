package superTTT.OpeningBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;

import superTTT.AI.AnalyzeSTTTGame;
import superTTT.Game.SuperTTTGame;

public class OpeningGenerator {
	private int depth;
	private OpeningBook book;
	private Random rand;
	
	public OpeningGenerator(){
		this(8);
	}
	
	public OpeningGenerator(int depth){
		book = new OpeningBook();
		rand = new Random();
		this.depth = depth;
	}
	
	public OpeningGenerator(String path) throws FileNotFoundException, IOException{
		this(path,8);
	}
	
	public OpeningGenerator(String path, int depth) throws FileNotFoundException, IOException{
		book = new OpeningBook(path);
		rand = new Random();
		this.depth = depth;
	}
	
	public void workOnBook(){
		AnalyzeSTTTGame analyzer = new AnalyzeSTTTGame();
		int node = OpeningBook.ROOT_INDEX;
		List<Integer> children = book.getChildren(node);
		while(!children.isEmpty()){
			node = decideLine(children, analyzer.getTurn());
			analyzer.move(book.getLastMove(node));
			children = book.getChildren(node);
		}
		List<int[]> moves;
		if(node == OpeningBook.ROOT_INDEX){
			moves = new ArrayList<int[]>();
			moves.add(new int[] {0,0,0,0});
			moves.add(new int[] {0,0,0,1});
			moves.add(new int[] {0,0,0,2});
			moves.add(new int[] {0,0,1,1});
			moves.add(new int[] {0,0,1,2});
			moves.add(new int[] {0,0,2,2});
			moves.add(new int[] {0,1,0,0});
			moves.add(new int[] {0,1,0,1});
			moves.add(new int[] {0,1,1,0});
			moves.add(new int[] {0,1,1,1});
			moves.add(new int[] {0,1,2,0});
			moves.add(new int[] {0,1,2,1});
			moves.add(new int[] {1,1,0,0});
			moves.add(new int[] {1,1,0,1});
			moves.add(new int[] {1,1,1,1});
		} else{
			moves = analyzer.getLegalMoves();
		}
		for(int i =0; i < moves.size(); i++){
			analyzer.move(moves.get(i));
			int child = book.addChild(node, SuperTTTGame.convertMove(moves.get(i)), analyzer.AnalyzeDepth(depth).getValue());
			//ensure every line of the book ends with player 2 moving to offset bias in the analysis algorithm
			if(analyzer.getTurn()%2 == 1){
				List<int[]> childMoves = analyzer.getLegalMoves();
				for(int j = 0; j < childMoves.size(); j++){
					analyzer.move(childMoves.get(j));
					book.addChild(child, SuperTTTGame.convertMove(childMoves.get(j)), analyzer.AnalyzeDepth(depth).getValue());
					analyzer.previousMove();
				}
			}
			analyzer.previousMove();
		}
	}
	
	//TODO improve this
	private int decideLine(List<Integer> choices, int player){
		double sum = 0.0;
		List<Double> scores = new ArrayList<Double>();
		for(int i = 0; i < choices.size(); i++){
			double score = (player*book.getScore(choices.get(i))+1.0)/2.0;
			scores.add(score);
			sum += score;
		}
		double r = rand.nextDouble();
		double runningSum = 0.0;
		for(int i =0; i < scores.size(); i++){
			runningSum += scores.get(i);
			if(runningSum/sum >= r){
				return choices.get(i);
			}
		}
		return choices.get(rand.nextInt(choices.size()));
	}
	
	public void workOnBook(int steps){
		for(int i=0; i < steps; i++){
			workOnBook();
		}
	}
	
	public void loadFromFile(String path) throws FileNotFoundException, IOException{
		book.loadFromFile(path);
	}
	
	public void saveToFile(String path) throws FileNotFoundException, IOException{
		book.saveToFile(path);
	}
	
	public static void main(String[] args){
		try{
			OpeningGenerator g = new OpeningGenerator("openingbook_1_0.txt");
//			List<Integer> children = g.book.getChildren(0);
//			System.out.println(g.book.getScore(0));
//			for(int i = 0; i < children.size(); i++){
//				System.out.println(g.book.getScore(children.get(i)));
//			}
			int i = 0;
			while(true){
				g.workOnBook();
				g.saveToFile("openingbook_1_0.txt");
				System.out.println("finished iteration " + (i++));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
