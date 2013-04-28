package hu.medtech.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameOverDialog extends Activity {

	// TextView time;
	// TextView score;
	// EditText name;
	//
	// Button backbtn;
	// Button newbtn;
	//
	public void saveResultToDatabase() {
		// String timestr;
		// String scorestr;
		//
		// //time lekerese
		// if (MainActivity.currentSec<10){timestr=MainActivity.currentMin +
		// ":0" + MainActivity.currentSec;}
		// else{timestr=MainActivity.currentMin + ":" +
		// MainActivity.currentSec;}
		//
		// //pont lekerese (a helyes sorrend miatt kell a feltetel)
		// if(MainActivity.score<10 &&
		// MainActivity.score>0){scorestr="0"+MainActivity.score;}
		// else{scorestr=""+MainActivity.score;}
		//
		// //bejegyzes letrehozasa
		// ScoreDbLoader dbLoader = new ScoreDbLoader(getApplicationContext());
		// dbLoader.open();
		// dbLoader.createScore(new
		// Score(name.getText().toString(),timestr,scorestr));
		// dbLoader.close();
	}

	public void saveAndClose() {
		// //mentunk az adatbazisba
		// saveResultToDatabase();
		//
		// //lenullazzuk a jatekot
		// MainActivity.fel_db=0;
		// MainActivity.felid0=0;
		// MainActivity.felid1=0;
		// MainActivity.felpict0=0;
		// MainActivity.felpict1=0;
		// MainActivity.megtalalt=0;
		// MainActivity.proba=0;
		// MainActivity.score=0;
		// MainActivity.currentMin=0;
		// MainActivity.currentSec=0;
		// MainActivity.vege=0;
		//
		// //visszaterunk a fomenube
		// Intent resultIntent = new Intent();
		// resultIntent.putExtra(MainActivity.MYGAMEOVERRESULT, "OK");
		// setResult(MainActivity.RESULT_OK, resultIntent);
		// finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		// //sp-bol a usernev lekerese
		// SharedPreferences prefs =
		// PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		// String userName = prefs.getString("editTextName", "");
		//
		// //a dialog ertekeinek beallitasa
		// name=(EditText)findViewById(R.id.sumbit_value);
		// name.setText(userName);
		// name.setSelectAllOnFocus(true);
		// time=(TextView)findViewById(R.id.time_value);
		// score=(TextView)findViewById(R.id.score_value);
		// score.setText("  "+MainActivity.score);
		// if (MainActivity.currentSec<10){
		// time.setText("  " + MainActivity.currentMin + ":0" +
		// MainActivity.currentSec);
		// }
		// else{
		// time.setText("  " + MainActivity.currentMin + ":" +
		// MainActivity.currentSec);
		// }
		//
		// //gombok
		// backbtn=(Button)findViewById(R.id.btnBack);
		// newbtn=(Button)findViewById(R.id.btnNewGame);
		//
		// //gombok esemenykezeloi
		// backbtn.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// //ment es bezar
		// saveAndClose();
		// }
		// });
		//
		// newbtn.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// //ment es bezar
		// saveAndClose();
		//
		// //ujra elinditjuk a jatekot
		// Intent intent=new Intent(GameOverDialog.this,MainActivity.class);
		// startActivity(intent);
		// }
		// });

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// ment es bezar
		// saveAndClose();
	}

}
