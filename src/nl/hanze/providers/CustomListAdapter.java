package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.tasks.LoadImageTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	private Context context;
	private CustomListAdapter adapter;
	private ArrayList<HashMap<String, ?>> data;
	private int resource;
	private String[] from;
	private int[] to;

	public CustomListAdapter(Context context, ArrayList<HashMap<String, ?>> data,
			int resource, String[] from, int[] to) {
		this.context = context;
		this.data = data;
		this.resource = resource;
		this.from = from;
		this.to = to;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			View rootView = convertView;

			if(convertView == null)
				rootView = LayoutInflater.from(context)
				.inflate(resource, parent, false);

			for(int i = 0; i < from.length; i++) {
				View view = rootView.findViewById(to[i]);

				if(view instanceof TextView) {
					Log.i("CustomListAdapter", "Just text...");
					TextView tView = (TextView) view;
					tView.setText((String) data.get(position).get(from[i]));
				}
				else if(view instanceof ImageView) {
					// Download the sucker.
					Log.i("CustomListAdapter", "Download that sucker");
					LoadImageTask task = new LoadImageTask(
							null,
							this,
							(ImageView) view,
							(String) data.get(position).get(from[i]));
					task.execute();
				}
			}

			return rootView;
		} catch(Exception e) {
			Log.e("CustomListAdapter", e.getMessage());
			return null;
		}
	}
}
