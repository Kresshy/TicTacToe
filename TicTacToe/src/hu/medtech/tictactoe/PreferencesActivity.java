package hu.medtech.tictactoe;

import hu.medtech.tictactoe.datastorage.ScoreDbLoader;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class PreferencesActivity extends PreferenceActivity {
	
	AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		Preference btn = getPreferenceScreen().findPreference("databaseDelete");
		
		//adatbazis torlese gombnal kerdezunk
		btn.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				alert.show();
				return false;
			}
		});

		//megerosito dialog
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        switch (which){
    	        case DialogInterface.BUTTON_POSITIVE:
	    	        	ScoreDbLoader dbLoader = new ScoreDbLoader(getApplicationContext());
	    				dbLoader.open();
	    				dbLoader.deleteAll();
	    				dbLoader.close();
	    				Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
    	            break;

    	        case DialogInterface.BUTTON_NEGATIVE:
    	            	dialog.cancel();
    	            break;
    	        }
    	    }
    	};
        
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
		alt_bld.setMessage("Are you sure to clear the whole database? It cannot be undone!")
		.setCancelable(false)
		.setPositiveButton("Yes",dialogClickListener)
		.setNegativeButton("No", dialogClickListener);
		alert = alt_bld.create();
		alert.setTitle("Delete highscores");
	}
}
