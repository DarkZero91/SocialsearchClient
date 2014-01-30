package nl.hanze.socialsearchclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Sources. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ProviderDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ProviderListFragment} and the item details (if present) is a
 * {@link ProviderDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link ProviderListFragment.Callbacks} interface to listen for item selections.
 */
public class ProviderListActivity extends FragmentActivity implements
		ProviderListFragment.Callbacks {

	public static final String SEARCH_RESULTS = "nl.hanze.socialsearchclient.SEARCH_RESULTS";
	
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private SearchTask searchTask;
	private String results;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provider_list);

		if (findViewById(R.id.source_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ProviderListFragment) getSupportFragmentManager().findFragmentById(
					R.id.source_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
		searchTask = new SearchTask(this, "kippetje");
		searchTask.execute();
	}

	/**
	 * Callback method from {@link ProviderListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(SEARCH_RESULTS, this.results);
			arguments.putString(ProviderDetailFragment.ARG_ITEM_ID, id);
			ProviderDetailFragment fragment = new ProviderDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.source_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ProviderDetailActivity.class);
			detailIntent.putExtra(SEARCH_RESULTS, this.results);
			detailIntent.putExtra(ProviderDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	
	public void setResults(String results) {
		this.results = results;
	}
}
