package nl.hanze.tasks;

import nl.hanze.http.ImageLoader;
import nl.hanze.providers.CustomListAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class LoadImageTask extends AsyncTask<Void, Integer, Void> {
	private Context context;
	private CustomListAdapter adapter;
	private ImageView image;
	private String url;
	
	public LoadImageTask(Context context, CustomListAdapter adapter, ImageView image, String url) {
		this.context = context;
		this.adapter = adapter;
		this.image = image;
		this.url = url;
	}

	@Override  
	protected void onPreExecute()  
	{  
		// Let's do.... absolutely nothing here.  
	} 

	@Override
	protected Void doInBackground(Void... params) {
		try {
			synchronized (this) {
				// Busdriver! LOAD... THAT... IMAGE!!!
				Log.i("LoadImageTask", "Busdriver! LOAD... THAT... IMAGE!!! (" + url + ")");
				Bitmap bitmap = ImageLoader.load(url);				
				adapter.setImage(image, bitmap);
			}
		} catch (Exception e) {
			Log.e("LoadImageTask", e.getMessage());
		}           

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// Again... do nothing. 
		Log.i("LoadImageTask", "Done!");
	}
}
