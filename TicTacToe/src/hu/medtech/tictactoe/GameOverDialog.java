package hu.medtech.tictactoe;

import hu.medtech.tictactoe.datastorage.ScoreDbLoader;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverDialog extends Activity {

	TextView time;
	TextView title;
	EditText name;
	Button backbtn;

	String winnerCode;
	boolean thisuserwon = false;

	public void saveResultToDatabase() {
		String timestr;
		String scorestr;

		// bejegyzes letrehozasa
		ScoreDbLoader dbLoader = new ScoreDbLoader(getApplicationContext());
		dbLoader.open();
		dbLoader.createScore(new Score(name.getText().toString(), time
				.getText().toString(), "0"));
		dbLoader.close();
	}

	public void close() {
		// visszaterunk a fomenube
		Intent resultIntent = new Intent();
		resultIntent.putExtra(ImageAdapter.MYGAMEOVERRESULT, "OK");
		setResult(MainActivity.RESULT_OK, resultIntent);
		finish();
	}

	public void saveAndClose() {
		saveResultToDatabase();
		close();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		// get the winner
		title = (TextView) findViewById(R.id.gameovertitle);
		winnerCode = getIntent().getStringExtra("winplayer");
		if (winnerCode.equals("O")) {
			title.setText("Player O won!");
		}
		if (winnerCode.equals("X")) {
			title.setText("Player X won!");
		}

		// i am X and i won
		if (((GlobalVariables) getApplication()).getSymbol() == MessageContainer.MESSAGE_SYMBOL_X
				&& winnerCode.equals("X")) {
			thisuserwon = true;
		}

		// i am O and i won
		if (((GlobalVariables) getApplication()).getSymbol() == MessageContainer.MESSAGE_SYMBOL_O
				&& winnerCode.equals("O")) {
			thisuserwon = true;
		}

		name = (EditText) findViewById(R.id.gameover_submit_value);
		if (thisuserwon) {
			name.setEnabled(true);
			// sp-bol a usernev lekerese
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getBaseContext());
			String userName = prefs.getString("editTextName", "");
			name.setText(userName);
			name.setSelectAllOnFocus(true);
		} else {
			name.setText("You didn't win");
			name.setEnabled(false);
		}

		time = (TextView) findViewById(R.id.gameover_time_value);
		time.setText(GameActivity.timertxt.getText().subSequence(6,
				GameActivity.timertxt.getText().length()));

		backbtn = (Button) findViewById(R.id.gameover_btn_back);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (thisuserwon) {
					saveAndClose();
				} else {
					close();
				}
			}
		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (thisuserwon) {
			saveAndClose();
		} else {
			close();
		}

	}

}
