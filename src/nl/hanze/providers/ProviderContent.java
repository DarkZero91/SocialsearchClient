package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProviderContent {

	/**
	 * An array of sample (provider) items.
	 */
	public static List<ProviderItem> ITEMS = new ArrayList<ProviderItem>();

	/**
	 * A map of sample (provider) items, by ID.
	 */
	public static Map<String, ProviderItem> ITEM_MAP = new HashMap<String, ProviderItem>();

	public static void setProviders(JSONObject json) {
		try {
			JSONObject results = json.getJSONObject("result");
			Iterator<?> providers = results.keys();
			
			int i = 1;
			while(providers.hasNext()) {
				String provider = (String) providers.next();
				addItem(new ProviderItem(Integer.toString(i), provider));
				i++;
			}
		} catch (JSONException e) {}
	}
	
	private static void addItem(ProviderItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class ProviderItem {
		public String id;
		public String provider;

		public ProviderItem(String id, String provider) {
			this.id = id;
			this.provider = provider.substring(0, 1).toUpperCase() + provider.substring(1);
		}
		
		@Override
		public String toString() {
			return provider;
		}
	}
}
