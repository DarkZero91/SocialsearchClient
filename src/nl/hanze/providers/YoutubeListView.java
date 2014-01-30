package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.socialsearchclient.R;

import org.json.JSONObject;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class YoutubeListView extends ListView implements Provider {
	
	public YoutubeListView(Context context, JSONObject results) {
		super(context);
		
		SimpleAdapter adapter = new SimpleAdapter(context,
				getData(results),
				R.layout.youtube_row,
				new String[] {"video"},
				new int[] {R.id.youtubeVideo});
		setAdapter(adapter);
	}

	@Override
	public ArrayList<HashMap<String, ?>> getData(JSONObject json) {
		ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
		
		for(int i = 0; i < 200; i++) {
			HashMap<String, Object> row = new HashMap<String, Object>();
			row.put("video", Integer.toString(i));
			data.add(row);
		}
				
		return data;
	}
}
