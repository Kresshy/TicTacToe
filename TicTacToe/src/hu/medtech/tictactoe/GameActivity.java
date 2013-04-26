package hu.medtech.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		final ImageAdapter imad = new ImageAdapter(this);

		final GridView gridview = (GridView) findViewById(R.id.gamegridview);
		gridview.setAdapter(imad);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Toast.makeText(GameActivity.this,
						"pos:" + position + " count:" + imad.getCount(),
						Toast.LENGTH_SHORT).show();

				// create message
				MessageContainer m = new MessageContainer();
				m.setMessage(position % 2 + 2);
				// refresh gridview elements
				imad.addElement(m, position);
				imad.notifyDataSetChanged();
			}
		});

	}

}
