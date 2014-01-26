package nl.hanze.socialsearchclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends Activity {
	public static final String SEARCH_TERMS = "nl.hanze.socialsearchclient.SEARCH_TERMS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public void search(View view) {
		Intent intent = new Intent(this, SourceListActivity.class);
		EditText searchField = (EditText) findViewById(R.id.searchTerms);
		String terms = searchField.getText().toString();
		intent.putExtra(SEARCH_TERMS, terms);
		startActivity(intent);
	}
}
