package nl.hanze.socialsearchclient;

import nl.hanze.providers.GoogleListView;
import nl.hanze.providers.ProviderContent;
import nl.hanze.providers.TwitterListView;
import nl.hanze.providers.YoutubeListView;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Source detail screen. This fragment is
 * either contained in a {@link ProviderListActivity} in two-pane mode (on
 * tablets) or a {@link ProviderDetailActivity} on handsets.
 */
public class ProviderDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private ProviderContent.ProviderItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ProviderDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ProviderContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_provider_detail,
				container, false);

		JSONObject results = null;
		if (getArguments().containsKey(ProviderListActivity.SEARCH_RESULTS)) {
			try {
				results = new JSONObject(getArguments().getString(
						ProviderListActivity.SEARCH_RESULTS));
			} catch (JSONException e) {}
		}
		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.source_detail))
					.setText("No results found");
			if(mItem.provider.equals("Twitter")) {
				return new TwitterListView(getActivity(), results);
			} else if(mItem.provider.equals("Googleplus")) {
				return new GoogleListView(getActivity(), results);
			} else if(mItem.provider.equals("Youtube")) {
				return new YoutubeListView(getActivity(), results);
			}
		}

		return rootView;
	}
}
