package hu.medtech.tictactoe;

import hu.medtech.tictactoe.datastorage.ScoreDbLoader;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

public class HighScoreActivity extends Activity {

	ScoreDbLoader dbLoader;
	ScoreAdapter scoreAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores_layout);

		ListView lv = (ListView) findViewById(R.id.list);
		// adatbazis megnyitasa
		dbLoader = new ScoreDbLoader(getApplicationContext());
		dbLoader.open();
		Cursor c = dbLoader.fetchAll();

		c.moveToFirst();

		scoreAdapter = new ScoreAdapter(getApplicationContext(), c) {
			@Override
			public boolean isEnabled(int position) {
				return false;
			}
		};

		lv.setCacheColorHint(Color.TRANSPARENT);
		lv.requestFocus(0);
		lv.setAdapter(scoreAdapter);

	}

}
