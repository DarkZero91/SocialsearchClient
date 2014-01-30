package nl.hanze.http;

import java.net.MalformedURLException;

import nl.hanze.socialsearchclient.Config;

import org.json.JSONException;

public class APIClient extends HTTPClient {
	private String host;
	
	public APIClient() throws Exception {
		super();
		host = "http://" + Config.host + ":" + Config.port;
	}
	
	public String searchAll(String terms) throws MalformedURLException, JSONException {
		setUrl(host + "/search/");
		return search(terms);
	}
	
	public String searchTwitter(String terms) throws MalformedURLException, JSONException {
		// setUrl(host + "/search/twitter/");
		setUrl(host + "/search/twitter/kippetje.php");
		return search(terms);
	}
	
	// TODO add search methods for all the other providers. (Hint: look at searchTwitter() :P)
	
	private String search(String terms) throws JSONException {
		setParam("search", terms);
		return sendRequest();
	}
}
