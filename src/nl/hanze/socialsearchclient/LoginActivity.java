package nl.hanze.socialsearchclient;

import java.net.MalformedURLException;

import org.json.JSONException;

import nl.hanze.http.APIClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private LoginTask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_form);
	}
	
	// Login Action
	public void login(View view) throws JSONException, MalformedURLException, Exception {
		EditText loginName = (EditText) findViewById(R.id.loginUsername);
		EditText loginPass = (EditText) findViewById(R.id.loginPassword);
		String username = loginName.getText().toString();
		String password = loginPass.getText().toString();
		task = new LoginTask(this, username, password);
		task.execute();
	}
}
