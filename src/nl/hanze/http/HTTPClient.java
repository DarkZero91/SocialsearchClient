package nl.hanze.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import android.util.Log;

public class HTTPClient {
	private URL url;
	private HttpURLConnection connection;

	private CookieManager cookieManager;
	private HashMap<String, String> params;
	
	public HTTPClient() throws Exception {
		params = new HashMap<String, String>();
		
		cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
		CookieManager.setDefault(cookieManager);
	}
	
	public void setUrl(String url) throws MalformedURLException {
		this.url = new URL(url);
	}
	
	public void setParam(String key, String value) {
		params.put(key, value);
	}
	
	public String sendRequest() {
		String response = null;
		
		try {
			connect();
			send();
			response = readResponse();
		} catch(IOException ioe) {
			Log.e("HTTPClient", "IOException: " + ioe.getLocalizedMessage(), ioe);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			String line;
			String error = new String();
			try {
				while((line = reader.readLine()) != null) {
					error += line;
				}
			} catch (IOException e) {}
			
			Log.e("HTTPClient", error);
		}
		
		params = new HashMap<String, String>();
		disconnect();		
		return response;
	}
	
	private String readResponse() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
	
		String line;
		while((line = reader.readLine()) != null) {
			response.append(line);
		}
		
		reader.close();
		return response.toString();
	}
	
	private void connect() throws IOException {
		connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
	}
	
	private void disconnect() {
		connection.disconnect();
	}
	
	private void send() throws IOException {
		String params = new String();
		
		for(String key : this.params.keySet()) {
			if(!params.isEmpty())
				params += "&";
			params += URLEncoder.encode(key, "UTF-8") + "=";
			params += URLEncoder.encode(this.params.get(key), "UTF-8");			
		}
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		Log.i("Login", params);
		writer.write(params);
		writer.close();
	}
}
