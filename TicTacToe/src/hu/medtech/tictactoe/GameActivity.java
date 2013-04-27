package hu.medtech.tictactoe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
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
	private GridView gridview;

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			// place object if field is blank
			if (imad.getElement(arg2) == R.drawable.field_blank) {
				// create message
				MessageContainer m = new MessageContainer();
				// m.setMessage(position % 2 + 2);
				m.setMessage(((GlobalVariables) getApplication()).getSymbol());
				m.setCoords(arg2);

				try {

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos;
					oos = new ObjectOutputStream(baos);
					oos.writeObject(m);
					((GlobalVariables) getApplication()).getConnectionService().write(baos.toByteArray());
					gridview.setOnItemClickListener(null);

				} catch (IOException e) {
					// TODO Auto-generated catch block
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

		((GlobalVariables) getApplication()).getConnectionService().setHandler(handler);

		gridview = (GridView) findViewById(R.id.gamegridview);
		gridview.setAdapter(imad);
		gridview.setOnItemClickListener(onItemClickListener);

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

						// the map is full

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
