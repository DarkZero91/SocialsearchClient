package nl.hanze.socialsearchclient;

import nl.hanze.http.APIClient;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SearchTask extends AsyncTask<Void, Integer, Void> {
	private ProgressDialog progressDialog;
	private Context context;
	private APIClient client;
	private JSONObject result;
	private String searchTerms;
	
	public SearchTask(Context context, String searchTerms) {
		this.context = context;
		
		try {
			client = new APIClient();
			this.searchTerms = searchTerms;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject getResult() {
		return result;
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
    	} catch (Exception e) {
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
