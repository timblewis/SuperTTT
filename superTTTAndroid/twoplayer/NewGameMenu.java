package superTTT.android.twoplayer;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewGameMenu extends Activity {
	private View i;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = new View(this);
        ArrayList<View> views = new ArrayList<View>();
        Button newGame = new Button(this);
        newGame.setText("New Game");
 
        views.add(newGame);
        i.addTouchables(views);
        setContentView(i);
    }
}
