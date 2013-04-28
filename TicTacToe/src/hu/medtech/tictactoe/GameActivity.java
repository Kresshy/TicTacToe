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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GameActivity extends Activity {

	private final ImageAdapter imad = new ImageAdapter(this, this);
	private GridView gridview;

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// place object if field is blank
			if (imad.getElement(arg2) == R.drawable.field_blank) {
				// create message
				MessageContainer m = new MessageContainer();
				m.setMessage(((GlobalVariables) getApplication()).getSymbol());
				m.setCoords(arg2);

				try {

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos;
					oos = new ObjectOutputStream(baos);
					oos.writeObject(m);
					((GlobalVariables) getApplication()).getConnectionService()
							.write(baos.toByteArray());
					gridview.setOnItemClickListener(null);

				} catch (IOException e) {
					e.printStackTrace();
				}

				// add object to the list
				imad.addElement(m, arg2);
				// refresh gridview elements
				imad.notifyDataSetChanged();
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		((GlobalVariables) getApplication()).getConnectionService().setHandler(
				handler);

		gridview = (GridView) findViewById(R.id.gamegridview);
		gridview.setAdapter(imad);
		gridview.setOnItemClickListener(onItemClickListener);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ImageAdapter.REQUEST_GAME_OVER_O) {
			Toast.makeText(getApplicationContext(), "OOOOOO",
					Toast.LENGTH_SHORT).show();
			this.finish();
		}
		if (requestCode == ImageAdapter.REQUEST_GAME_OVER_X) {
			Toast.makeText(getApplicationContext(), "XXXXXX",
					Toast.LENGTH_SHORT).show();
			this.finish();
		}
	};

	@Override
	protected void onStart() {
		super.onStart();
		((GlobalVariables) getApplication()).getConnectionService().setHandler(
				handler);
	}

	@Override
	protected void onResume() {
		super.onResume();
		((GlobalVariables) getApplication()).getConnectionService().setHandler(
				handler);
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

					ByteArrayInputStream bais = new ByteArrayInputStream(
							readBuf);
					ObjectInputStream ois;
					ois = new ObjectInputStream(bais);

					MessageContainer readedMessage = (MessageContainer) ois
							.readObject();
					Log.i("GameActivity",
							"Message NUM: " + readedMessage.getMessage());

					switch (readedMessage.getMessage()) {

					case MessageContainer.MESSAGE_SYMBOL_O:

						addImageAdapter(readedMessage);
						imad.notifyDataSetChanged();
						gridview.setOnItemClickListener(onItemClickListener);

						break;

					case MessageContainer.MESSAGE_SYMBOL_X:

						addImageAdapter(readedMessage);
						imad.notifyDataSetChanged();
						gridview.setOnItemClickListener(onItemClickListener);

						break;

					case MessageContainer.MESSAGE_WIN:

						// GameOver

						break;

					case MessageContainer.MESSAGE_GAME_OVER:

						if (readedMessage.getCoords() == -1) {
							Toast.makeText(getApplicationContext(),
									"You opponent has quit the game",
									Toast.LENGTH_LONG).show();
							finish();
						}

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

	@Override
	public void onBackPressed() {

		try {

			MessageContainer m = new MessageContainer(
					MessageContainer.MESSAGE_GAME_OVER, -1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(baos);
			oos.writeObject(m);
			((GlobalVariables) getApplication()).getConnectionService().write(
					baos.toByteArray());

		} catch (IOException e) {

			e.printStackTrace();
		}

		super.onBackPressed();
	}

}
