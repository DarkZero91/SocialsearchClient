package nl.hanze.tasks;

import nl.hanze.http.APIClient;
import nl.hanze.socialsearchclient.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoginTask extends AsyncTask<Void, Integer, Void> {
	private ProgressDialog progressDialog;
	private Toast toast;
	private Context context;
	private APIClient client;
	private String result;
	private String username;
	private String password;
	
	public LoginTask(Context context, String username, String password) {
		this.context = context;
		
		try {
			client = new APIClient();
			this.username = username;
			this.password = password;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override  
    protected void onPreExecute()  
    {  
        progressDialog = ProgressDialog.show(context,"Authenticating...",  
                null, false, false);
    	progressDialog.show();      
    } 
    
    @Override
    protected Void doInBackground(Void... params) {
    	try {
    		synchronized (this) {
    			Log.i("Login", "Logging with "+username+" and "+password);
    			result = client.login(username, password);
    			Log.i("Login", "Result: " + result);
    		}
    	} catch (Exception e) {
			Log.e("Login", e.getMessage());
		}           
    	
    	return null;
    }
    
    @Override
    protected void onPostExecute(Void result) {
    	progressDialog.dismiss();
    	JSONObject json;
		try {
			json = new JSONObject(this.result);
	    	if(json.has("username")) {
	    		LoginActivity activity = (LoginActivity) context;
	    		activity.loginCallback();
	    	} else {
	            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	            toast.setText("Wrong email/password combination");
	        	toast.show();
	    	}
		} catch (JSONException e) {
			Log.i("Error JSON", e.toString());
		}
    }
}
