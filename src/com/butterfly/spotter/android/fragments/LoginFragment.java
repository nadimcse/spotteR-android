package com.butterfly.spotter.android.fragments;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.activities.LoginActivity;
import com.butterfly.spotter.android.activities.MainActivity;
import com.butterfly.spotter.android.listener.SwitchFragmentListener;
import com.butterfly.spotter.android.util.Utility;
import com.turbomanage.httpclient.AsyncCallback;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.ParameterMap;
import com.turbomanage.httpclient.android.AndroidHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    private SwitchFragmentListener fragmentListener;
	public static boolean loogedIn = false;
	public static String loginId;
	private Button loginBtn;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
		handleLoginFunctionality(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            fragmentListener = (SwitchFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SwitchFragmentListener");
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    
	public void handleLoginFunctionality(View view) {
		 
		final EditText callerId = (EditText) view.findViewById(R.id.callerId);
		loginBtn = (Button) view.findViewById(R.id.loginButton);
 		loginBtn.setOnClickListener(new OnClickListener() {
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
                    	
                    	//show login validation error
                        e.printStackTrace();
                    }
					@Override
					public void onComplete(HttpResponse httpResponse) {
						   ///check authentication
						   loogedIn = true;
						   loginId = callerId.getText().toString(); //??? i forget what is this for.
						   fragmentListener.switchFragment(CallListFragment.class);
					}
                });
			}
 
		});
	}
}
