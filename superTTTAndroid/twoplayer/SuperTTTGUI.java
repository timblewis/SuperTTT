package superTTT.android.twoplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import superTTT.Game.*;
import superTTT.StandardGUI.*;
import superTTT.AI.*;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import superTTT.scala.ScalaAIPlayer;

public class SuperTTTGUI extends Activity implements Player{
	private static final int NEW_GAME_ID = 0;
	private static final int HELP_ID = 1;
	private SuperTTTBoardGui gui;
	private ImageView i;
	private int[] move;
	private Game game;
	
	public SuperTTTGUI(){
		super();
        gui = new SuperTTTBoardGui(0,0,0,0);
        game = new Game(this, new AIPlayer(5));
        //game = new Game(this, new ScalaAIPlayer());
        //game = new Game(this, this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = new ImageView(this);
        setContentView(i);
        i.setImageDrawable(gui);
        game.start();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState){
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0, NEW_GAME_ID, 0, "New Game");
    	menu.add(0,HELP_ID,0,"Help");
    	return true;
    }
    
    /*public boolean onOptionsItemSelected (MenuItem item){
    	switch (item.getItemId()){
    	case NEW_GAME_ID: Intent i = new Intent(this,NewGameMenu.class); startActivity(i); break;
    	case HELP_ID: Intent in = new Intent(this,HelpMenu.class); startActivity(in); break;
    	}
		return true;
    }*/
    
    private class Game extends Thread{
    	Player[] players;
    	SuperTTTGame game;
    	
    	public Game(Player player1, Player player2){
    		super();
    		game = new SuperTTTGame();
	    	players = new Player[2];
	    	players[0] = player1;
	    	players[1] = player2;
    	}
    	
    	public void run(){
	    	int[] previousMove = null;
			while(game.getWinner() == 0 && game.getTurn() != 81){
				int[] move = null;
				while(!game.isLegalMove(move)){
					move = players[game.getTurn()%2].getMove(previousMove);
					System.out.println(game.getTurn());
				}
				game.move(move);
				TTTBoardGui innerBoard = gui.getInnerBoard(move[0], move[1]);
				innerBoard.setChar(move[2], move[3], game.getTurn()%2 == 1 ? 'X' : 'O');
				innerBoard.setBoardColor(getAssociatedColor(game.getController(move[0], move[1])));
				i.postInvalidate();
				previousMove = move;
			}
			gui.setBoardColor(getAssociatedColor(game.getWinner()));
			i.postInvalidate();
    	}
    }
    
    private int getAssociatedColor(int player){
    	switch(player){
    	case 1: return Color.BLUE;
    	case -1: return Color.RED;
    	default: return Color.WHITE;
    	}
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event){
    	move = gui.getSquare((int)event.getX(), (int)event.getY() - 19);
    	return true;
    }

	@Override
	public int[] getMove(int[] previousMove) {
		move = null;
		while(move == null){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return move;
	}
}