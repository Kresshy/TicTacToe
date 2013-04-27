package hu.medtech.tictactoe;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private List<Integer> mThumbIds = new ArrayList<Integer>();

	public ImageAdapter(Context c) {
		mContext = c;
		for (int i = 0; i < 140; i++) {
			mThumbIds.add(R.drawable.field_blank);
		}
	}

	public void addElement(MessageContainer m, int pos) {
		switch (m.getMessage()) {
		case MessageContainer.MESSAGE_SYMBOL_X:
			mThumbIds.set(pos, R.drawable.field_x);
			break;
		case MessageContainer.MESSAGE_SYMBOL_O:
			mThumbIds.set(pos, R.drawable.field_y);
			break;
		}

	}

	public int getCount() {
		return mThumbIds.size();
	}

	public Object getItem(int position) {
		return null;
	}

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
