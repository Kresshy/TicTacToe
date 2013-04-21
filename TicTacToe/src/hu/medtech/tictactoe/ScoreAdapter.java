package hu.medtech.tictactoe;

import hu.medtech.tictactoe.datastorage.ScoreDbLoader;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ScoreAdapter extends CursorAdapter {

	public ScoreAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView nameTV = (TextView) view.findViewById(R.id.highscore_name);
		TextView idoTV = (TextView) view.findViewById(R.id.highscore_time);
		TextView pontTV = (TextView) view.findViewById(R.id.highscore_score);

		Score score = ScoreDbLoader.getScoreByCursor(cursor);

		String temppont = Integer.toString(Integer.parseInt(score.getPont()));
		nameTV.setText(score.getNev());
		idoTV.setText(score.getIdo());
		pontTV.setText(temppont);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		View row = inflater.inflate(R.layout.highscore_row, null);
		bindView(row, context, cursor);
		return row;
	}

}
