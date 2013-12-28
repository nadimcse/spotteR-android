package com.butterfly.spotter.android.activities;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.fragments.GCMLoaderFragment;
import com.butterfly.spotter.android.util.Utility;
import com.turbomanage.httpclient.AsyncCallback;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.ParameterMap;
import com.turbomanage.httpclient.android.AndroidHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

/** 
*
* @author Nadim
* @since  Dec 18, 2013
*
*/

public class LoginActivity extends Activity {
	public static boolean loogedIn = false;
	public static String loginId;
	Button button;
	String gcmKey;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		addListenerOnButton();
	}
 
	public void addListenerOnButton() {
 
		final Context context = this;
 
		final EditText callerId = (EditText) findViewById(R.id.callerId);
		button = (Button) findViewById(R.id.loginButton);
 
		button.setOnClickListener(new OnClickListener() {
 
			///add fragment
			@Override
			public void onClick(View arg0) {
                
                AndroidHttpClient httpClient = new AndroidHttpClient(Utility.SERVERPATH);
                httpClient.setMaxRetries(2);
                ParameterMap params = httpClient.newParams()
                        .add("gcmKey", GCMLoaderFragment.gcmKey)
                        .add("password", "1234")
                        .add("callerId", callerId.getText().toString());
                httpClient.post("/spotter/login", params, new AsyncCallback() {
                   
                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
					@Override
					public void onComplete(HttpResponse httpResponse) {
						   System.out.println("$$$$$$$$$$$$$$$$$$$" + httpResponse.getBodyAsString());
					
						   ///check authentication
						   loogedIn = true;
						   loginId = callerId.getText().toString();
			                if (LoginActivity.loogedIn) {
			        			LoginActivity.loogedIn = true;	
			      			  Intent intent = new Intent(context, MainActivity.class);
			                      startActivity(intent);
			                      ///now activity is destroyed after 
			                      //completion of task. fragment will be used 
			                      ///for further code improvement.
			                    // finish();
			                }	
					}
                });
			}
 
		});
 
	}
}
