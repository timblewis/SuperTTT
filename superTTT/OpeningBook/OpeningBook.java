package superTTT.OpeningBook;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class OpeningBook {
	public static final int ROOT_INDEX = 0;
	public static final int NO_PARENT = -1;
	private int nodes;
	private List<List<Integer>> children;
	private List<Integer> parent;
	private List<Integer> lastMove;
	private List<Double> scores;
	private List<Boolean> isP1sTurn;
	
	public OpeningBook(){
		nodes = 1;
		children = new ArrayList<>();
		children.add(new ArrayList<Integer>());
		parent = new ArrayList<>();
		parent.add(NO_PARENT);
		lastMove = new ArrayList<>();
		lastMove.add(-1);
		scores = new ArrayList<>();
		scores.add(0.0);
		isP1sTurn = new ArrayList<>();
		isP1sTurn.add(true);
	}
	
	public OpeningBook(String path) throws FileNotFoundException, IOException{
		loadFromFile(path);
	}
	
	public int getLastMove(int node){
		if(node >= 0 && node < nodes){
			return lastMove.get(node);
		} else{
			throw new InvalidParameterException();
		}
	}
	
	public List<Integer> getChildren(int node){
		if(node >= 0 && node < nodes){
			return children.get(node);
		} else{
			throw new InvalidParameterException();
		}
	}
	
	public double getScore(int node){
		if(node >= 0 && node < nodes){
			return scores.get(node);
		} else{
			throw new InvalidParameterException();
		}
	}
	
	public int addChild(int node, int move, double score){
		children.get(node).add(nodes);
		children.add(new ArrayList<Integer>());
		parent.add(node);
		lastMove.add(move);
		scores.add(score);
		isP1sTurn.add(!isP1sTurn.get(node));
		int i = node;
		boolean improved = true;
		while(improved && i != NO_PARENT){
			double max = isP1sTurn.get(i) ? -Double.MAX_VALUE : Double.MAX_VALUE;
			for(int j = 0; j < children.get(i).size(); j++){
				int child = children.get(i).get(j);
				double v = scores.get(child);
				if((isP1sTurn.get(i) && v > max) || (!isP1sTurn.get(i) && v < max)){
					max = v;
				}
			}
			improved = max != scores.get(i);
			scores.set(i, max);
			i = parent.get(i);
		}
		nodes++;
		return nodes-1;
	}
	
	public void saveToFile(String path) throws FileNotFoundException, IOException{
		DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
		out.writeInt(nodes);
		for(int i =0; i < nodes; i++){
			out.writeInt(children.get(i).size());
			out.writeInt(lastMove.get(i));
			out.writeDouble(scores.get(i));
			for(int j=0; j < children.get(i).size(); j++){
				out.writeInt(children.get(i).get(j));
			}
		}
		out.close();
	}
	
	public void loadFromFile(String path) throws FileNotFoundException, IOException{
		DataInputStream in = new DataInputStream(new FileInputStream(path));
		nodes = in.readInt();
		children = new ArrayList<>(nodes);
		parent = new ArrayList<>(nodes);
		lastMove = new ArrayList<>(nodes);
		scores = new ArrayList<>(nodes);
		isP1sTurn = new ArrayList<>(nodes);
		for(int i=0; i < nodes; i++){
			parent.add(NO_PARENT);
			isP1sTurn.add(true);
		}
		for(int i = 0; i < nodes; i++){
			int numChildren = in.readInt();
			lastMove.add(in.readInt());
			scores.add(in.readDouble());
			children.add(new ArrayList<Integer>(numChildren));
			for(int j =0; j < numChildren; j++){
				int child = in.readInt();
				children.get(i).add(child);
				parent.set(child, i);
				isP1sTurn.set(child,!isP1sTurn.get(j));
			}
		}
		in.close();
		
	}
	
}
