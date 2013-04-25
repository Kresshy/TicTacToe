package hu.medtech.tictactoe;

import hu.medtech.tictactoe.datastorage.ScoreDbLoader;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HighScoreActivity extends Activity {

	ScoreDbLoader dbLoader;
	ScoreAdapter scoreAdapter;

	Button bezarbtn;
	TextView noitemTV;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscores_layout);

		ListView lv = (ListView) findViewById(R.id.list);
		bezarbtn = (Button) findViewById(R.id.highscores_btn);
		noitemTV = (TextView) findViewById(R.id.highscores_noitem);

		// adatbazis megnyitasa
		dbLoader = new ScoreDbLoader(getApplicationContext());
		dbLoader.open();
		Cursor c = dbLoader.fetchAll();

		// ha üres a tábla
		if (c.getCount() == 0) {
			noitemTV.setText("There are no results yet!");
		} else {
			noitemTV.setText("(Name,Score,Time)");
		}

		// a listanak beallitjuk az adaptert
		c.moveToFirst();
		scoreAdapter = new ScoreAdapter(getApplicationContext(), c);
		lv.setAdapter(scoreAdapter);

		// a bezaras gomb esemenykezeloje
		bezarbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dbLoader.close();
				finish();
			}
		});

	}

}
