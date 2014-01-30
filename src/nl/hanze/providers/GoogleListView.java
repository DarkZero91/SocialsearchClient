package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.socialsearchclient.R;

import org.json.JSONObject;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GoogleListView extends ListView implements Provider {
	
	public GoogleListView(Context context, JSONObject results) {
		super(context);
		
		SimpleAdapter adapter = new SimpleAdapter(context,
				getData(results),
				R.layout.google_row,
				new String[] {"name","content"},
				new int[] {R.id.googleName, R.id.googleContent});
		setAdapter(adapter);
	}

	@Override
	public ArrayList<HashMap<String, ?>> getData(JSONObject json) {
		ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();
		
		for(int i = 0; i < 200; i++) {
			HashMap<String, Object> row = new HashMap<String, Object>();
			row.put("name", Integer.toString(i));
			row.put("content", "test tweet (<- it's not really a tweet :P)");
			data.add(row);
		}
				
		return data;
	}
}
