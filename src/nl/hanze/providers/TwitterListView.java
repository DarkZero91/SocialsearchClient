package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.socialsearchclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TwitterListView extends ListView implements Provider {
	
	public TwitterListView(Context context, JSONObject results) {
		super(context);
		
		//SimpleAdapter adapter = new SimpleAdapter(context,
		CustomListAdapter adapter = new CustomListAdapter(context,
				getData(results),
				R.layout.twitter_row,
//				new String[] {"name","content", "image_url"},
//				new int[] {R.id.textView1, R.id.textView2, R.id.imageView1});
				new String[] {"name","content"},
				new int[] {R.id.textView1, R.id.textView2});
		setAdapter(adapter);
	}

	@Override
	public ArrayList<HashMap<String, ?>> getData(JSONObject json) {
		ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
		try {
			JSONArray results = json.getJSONObject("result").getJSONObject("twitter").getJSONArray("items");
		
			for(int i = 0; i < results.length(); i++) {
				JSONObject object = results.getJSONObject(i);
				HashMap<String, Object> row = new HashMap<String, Object>();
				
				row.put("name", object.get("username"));
				row.put("content", object.get("content"));
				
				JSONObject image = object.getJSONObject("image");
				row.put("image_url", "http://" + image.getString("host") + image.getString("path"));
				
				data.add(row);
			}
		} catch (JSONException e) {
			data = new ArrayList<HashMap<String, ?>>();
		}
		
		return data;
	}
}
