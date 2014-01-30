package nl.hanze.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	static {
		// Add 3 sample items.
		addItem(new ProviderItem("1", "Twitter", "Item 1"));
		addItem(new ProviderItem("2", "Youtube", "Item 2"));
		addItem(new ProviderItem("3", "Instagram", "Item 3"));
		addItem(new ProviderItem("4", "Google", "Item 4"));
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
		public String content;

		public ProviderItem(String id, String provider, String content) {
			this.id = id;
			this.provider = provider;
			this.content = content;
		}
		
		@Override
		public String toString() {
			return provider;
		}
	}
}
