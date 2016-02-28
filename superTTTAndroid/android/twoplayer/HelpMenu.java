package superTTT.android.twoplayer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpMenu extends Activity{
	private TextView i;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = new TextView(this);
        i.setText("Help Menu");
        setContentView(i);
    }
}
