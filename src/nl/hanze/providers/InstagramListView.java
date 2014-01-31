package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.socialsearchclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class InstagramListView extends ListView implements Provider {
	
	public InstagramListView(Context context, JSONObject results) {
		super(context);
		
		SimpleAdapter adapter = new SimpleAdapter(context,
				getData(results),
				R.layout.instagram_row,
				new String[] {"name","desc"},
				new int[] {R.id.instaName, R.id.instaDesc});
		setAdapter(adapter);
	}

	@Override
	public ArrayList<HashMap<String, ?>> getData(JSONObject json) {
		ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
		try {
			JSONArray results = json.getJSONObject("result").getJSONObject("instagram").getJSONArray("items");
			for(int i = 0; i < results.length(); i++) {
				JSONObject object = results.getJSONObject(i);
				HashMap<String, Object> row = new HashMap<String, Object>();
				
				row.put("name", object.get("username"));
				row.put("desc", object.get("content"));
				
				data.add(row);
			}
		} catch (JSONException e) {
			data = new ArrayList<HashMap<String, ?>>();
		}
		
		return data;
	}
}
