package nl.hanze.http;

import java.net.MalformedURLException;
import java.util.Map;

import nl.hanze.socialsearchclient.Config;
import android.util.Log;

public class APIClient extends HTTPClient {
	private String host;
	
	public APIClient() throws Exception {
		super();
		host = "http://" + Config.host + ":" + Config.port;
	}
	
	public Map searchAll(String terms) throws MalformedURLException {
		setUrl(host + "/search/");
		return search(terms);
	}
	
	public Map searchTwitter(String terms) throws MalformedURLException {
		//setUrl(host + "/search/twitter.json"); // .json extension wont work in my test environment...
		setUrl(host + "/search/twitter"); // This does work in my test environment...
		return search(terms);
	}
	
	// TODO add search methods for all the other providers. (Hint: look at searchTwitter() :P)
	
	private Map search(String terms) {
		setParam("search", terms);
		String response = sendRequest();
		Log.i("APIClient", response);
		return JSONTransformer.parseJson(response);
	}
}
