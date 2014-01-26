package nl.hanze.socialsearchclient;

import java.net.MalformedURLException;
import java.util.Map;

import nl.hanze.http.APIClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SearchTask extends AsyncTask<Void, Integer, Void> {
	private ProgressDialog progressDialog;
	private Context context;
	private Activity activity;
	private APIClient client;
	private Map result;
	private String searchTerms;
	
	public SearchTask(Context context, Activity activity, String searchTerms) {
		this.context = context;
		this.activity = activity;
		
		try {
			client = new APIClient();
			this.searchTerms = searchTerms;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override  
    protected void onPreExecute()  
    {  
        progressDialog = ProgressDialog.show(context,"Loading...",  
                null, false, false);
    	progressDialog.show();      
    } 
    
    @Override
    protected Void doInBackground(Void... params) {
    	try {
    		synchronized (this) {
    			result = client.searchTwitter(searchTerms);
    		}
    	} catch (MalformedURLException e) {
			Log.e("SearchTask", e.getMessage());
		}           
    	
    	return null;
    }
    
    @Override
    protected void onPostExecute(Void result) {
    	progressDialog.dismiss();
    	
    	// TODO Show results in SourceListActivity.
    	if(this.result != null)
    		Log.i("SearchTask", this.result.toString());
    	else
    		Log.i("SearchTask", "No Result");
    }
}
