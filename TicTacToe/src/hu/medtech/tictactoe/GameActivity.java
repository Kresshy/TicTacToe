package hu.medtech.tictactoe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GameActivity extends Activity {

	final ImageAdapter imad = new ImageAdapter(this);
	private static final int MESSAGE_ADD_ITEM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		final GridView gridview = (GridView) findViewById(R.id.gamegridview);
		gridview.setAdapter(imad);

		((GlobalVariables) getApplication()).getConnectionService().setHandler(handler);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

				// Toast.makeText(GameActivity.this,
				// "pos:" + position + " count:" + imad.getCount(),
				// Toast.LENGTH_SHORT).show();

				// place object if field is blank
				if (imad.getElement(position) == R.drawable.field_blank) {
					// create message
					MessageContainer m = new MessageContainer();
					// m.setMessage(position % 2 + 2);
					m.setMessage(((GlobalVariables)getApplication()).getSymbol());
					m.setCoords(position);

					try {

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos;
						oos = new ObjectOutputStream(baos);
						oos.writeObject(m);
						((GlobalVariables) getApplication()).getConnectionService().write(baos.toByteArray());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// add object to the list
					imad.addElement(m, position);
					// refresh gridview elements
					imad.notifyDataSetChanged();
				}

			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		((GlobalVariables) getApplication()).getConnectionService().setHandler(handler);
	}

	@Override
	protected void onResume() {
		super.onResume();
		((GlobalVariables) getApplication()).getConnectionService().setHandler(handler);
	}

	private final Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Log.i("GameActivity", "Incoming Message");

			switch (msg.what) {

			case MainActivity.MESSAGE_READ:

				try {

					byte[] readBuf = (byte[]) msg.obj;
					int paramInt = msg.arg1;

					ByteArrayInputStream bais = new ByteArrayInputStream(readBuf);
					ObjectInputStream ois;
					ois = new ObjectInputStream(bais);

					MessageContainer readedMessage = (MessageContainer) ois.readObject();
					Log.i("GameActivity", "Message NUM: " + readedMessage.getMessage());

					switch (readedMessage.getMessage()) {

					case MessageContainer.MESSAGE_SYMBOL_O:

						addImageAdapter(readedMessage);
						imad.notifyDataSetChanged();

						break;

					case MessageContainer.MESSAGE_SYMBOL_X:

						addImageAdapter(readedMessage);
						imad.notifyDataSetChanged();

						break;

					case MessageContainer.MESSAGE_WIN:

						// GameOver

						break;

					default:

						break;
					}

				} catch (StreamCorruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			default:

				break;

			}

		}

	};

	public void addImageAdapter(MessageContainer m) {
		imad.addElement(m, m.getCoords());
	}

}
