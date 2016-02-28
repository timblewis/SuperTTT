package superTTT.Game;

import java.util.ArrayList;


public class BasicSquare implements InnerSquare{
	protected int controller;
	
	@Override
	public int getController() {
		return controller;
	}
	public void setController(int player){
		controller = player;
	}
	
	public BasicSquare Clone(){
		BasicSquare clone = new BasicSquare();
		clone.controller = controller;
		return clone;
	}
	@Override
	public double getPercentControlled() {
		return controller;
	}
}
