package nl.hanze.http;

import java.util.Map;

import android.util.Log;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class JSONTransformer {
	private JSONTransformer() {}
	
	public static Map parseJson(String json) {
		json = "{\"result\": \"Hello world\"}";
		
		try {
			if(json == null || json.length() == 0) {
				Log.i("JSONTransformer", "JSON string is null or empty");
				return null;
			}
			
			JsonParserFactory factory=JsonParserFactory.getInstance();
			JSONParser parser = factory.newJsonParser();
			return parser.parseJson(json);
		} catch(Exception e) {
			Log.e("JSONTransformer", e.getMessage());
		}
		
		return null;
	}
}
