package hu.medtech.tictactoe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private List<Integer> mThumbIds = new ArrayList<Integer>();
	private int[][] gameTableX = new int[10][14];
	private int[][] gameTableO = new int[10][14];
	
	private WeakReference<Activity> activityRef;

	private final int REQUEST_GAME_OVER_X = 101;
	private final int REQUEST_GAME_OVER_O = 102;
	public static final String MYGAMEOVERRESULT = "GAMEOVER";

	public ImageAdapter(Context c, Activity a) {
		mContext = c;
		this.activityRef = new WeakReference<Activity>(a);
		for (int i = 0; i < 140; i++) {
			mThumbIds.add(R.drawable.field_blank);
		}
	}

	public void addElement(MessageContainer m, int pos) {
		int x_pos = pos % 10;
		int y_pos = pos / 10;

		switch (m.getMessage()) {
		case MessageContainer.MESSAGE_SYMBOL_X:
			mThumbIds.set(pos, R.drawable.field_x);
			gameTableX[x_pos][y_pos] = 1;
			break;
		case MessageContainer.MESSAGE_SYMBOL_O:
			mThumbIds.set(pos, R.drawable.field_o);
			gameTableO[x_pos][y_pos] = 1;
			break;
		}

		checkWinO();
		checkWinX();

	}

	public void theWinnerIsO() {
		// winning handling
		// Toast.makeText(mContext, "O WINWINWIN", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(mContext, GameOverDialog.class);
		activityRef.get().startActivityForResult(intent, REQUEST_GAME_OVER_O);
	}

	public void theWinnerIsX() {
		// winning handling
		// Toast.makeText(mContext, "X WINWINWIN", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(mContext, GameOverDialog.class);
		activityRef.get().startActivityForResult(intent, REQUEST_GAME_OVER_X);
	}

	public void checkWinO() {
		// in a row
		for (int j = 0; j < 14; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				if (gameTableO[i][j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}

		// in a column
		for (int i = 0; i < 10; i++) {
			int sum = 0;
			for (int j = 0; j < 14; j++) {
				if (gameTableO[i][j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}

		// diagonal angle:\
		for (int j = 0; j < 9; j++) {
			int sum = 0;
			for (int i = 0; i < 10 - j; i++) {
				if (gameTableO[i + j][i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 1; j < 5; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				if (gameTableO[i][i + j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 5, k = 9; j < 13; j++, k--) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				if (gameTableO[i][j + i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}

		// diagonal angle:/
		for (int j = 0; j < 9; j++) {
			int sum = 0;
			for (int i = 0; i < 10 - j; i++) {
				if (gameTableO[9 - i - j][i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 1; j < 5; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				if (gameTableO[9 - i][i + j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 5, k = 9; j < 13; j++, k--) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				if (gameTableO[9 - i][j + i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsO();
					sum = 0;
					return;
				}
			}
		}

	}

	public void checkWinX() {
		// in a row
		for (int j = 0; j < 14; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				if (gameTableX[i][j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}

		// in a column
		for (int i = 0; i < 10; i++) {
			int sum = 0;
			for (int j = 0; j < 14; j++) {
				if (gameTableX[i][j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}

		// diagonal angle:\
		for (int j = 0; j < 9; j++) {
			int sum = 0;
			for (int i = 0; i < 10 - j; i++) {
				if (gameTableX[i + j][i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 1; j < 5; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {

				if (gameTableX[i][i + j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 5, k = 9; j < 13; j++, k--) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				if (gameTableX[i][j + i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}

		// diagonal angle:/
		for (int j = 0; j < 9; j++) {
			int sum = 0;
			for (int i = 0; i < 10 - j; i++) {
				if (gameTableX[9 - i - j][i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 1; j < 5; j++) {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				if (gameTableX[9 - i][i + j] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}
		for (int j = 5, k = 9; j < 13; j++, k--) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				if (gameTableX[9 - i][j + i] == 1) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					theWinnerIsX();
					sum = 0;
					return;
				}
			}
		}

	}

	public int getElement(int pos) {
		return mThumbIds.get(pos);
	}

	@Override
	public int getCount() {
		return mThumbIds.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			GridView.LayoutParams params = new GridView.LayoutParams(
					GridView.LayoutParams.FILL_PARENT,
					GridView.LayoutParams.WRAP_CONTENT);
			imageView.setLayoutParams(new GridView.LayoutParams(params));
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView.setAdjustViewBounds(true);
			imageView.setPadding(0, 0, 0, 0);

		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds.get(position));
		return imageView;
	}

}
