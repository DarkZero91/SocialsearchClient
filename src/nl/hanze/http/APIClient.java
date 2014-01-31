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
		setUrl(host + "/search.json/");
		return search(terms);
	}
	
	public String searchProvider(String terms, String provider) throws MalformedURLException, JSONException {
		//setUrl(host + "/search/twitter.json"); // .json extension wont work in my test environment...
		setUrl(host + "/search/" + provider); // This does work in my test environment...
		return search(terms);
	}
	
	public String login(String username, String password) throws MalformedURLException, JSONException {
		setUrl(host + "/users/sign_in.json");
		setParam("user[email]", username);
		setParam("user[password]", password);
		setParam("authenticity_token", "ZPYde072a9uLdBsuNYumraRjXesdGorwtV7vs+nzWW8=");
		setParam("commit", "Sign In");
		return sendRequest();
	}
	
	private String search(String terms) throws JSONException {
		setParam("search", terms);
		return sendRequest();
	}
}
