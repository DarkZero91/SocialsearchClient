package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public interface Provider {
	public ArrayList<HashMap<String, ?>> getData(JSONObject json);
}
