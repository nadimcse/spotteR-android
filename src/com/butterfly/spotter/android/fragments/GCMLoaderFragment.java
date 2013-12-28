package com.butterfly.spotter.android.fragments;



import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** 
*
* @author Nadim
* @since  Dec 2, 2013
*
*/

public class GCMLoaderFragment extends Fragment {
	static final String SENDER_ID = "942103772492";
	private String TAG = "** pushAndroidActivity **";
	public static String gcmKey;

	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
       // checkNotNull(SENDER_ID, "SENDER_ID");
        
        GCMRegistrar.checkDevice(this.getActivity());
        GCMRegistrar.checkManifest(this.getActivity());
        
        final String regId = GCMRegistrar.getRegistrationId(this.getActivity());
        Log.i(TAG, "registration id =====  "+regId);
         
        if (regId.equals("")) {
        GCMRegistrar.register(this.getActivity(), SENDER_ID);
        } else {
        Log.v(TAG, "Already registered");
        }
        
        if (regId != null && regId.length() > 0 ) {
        	storeRegistrationId(this.getActivity(), regId);
        	gcmKey = regId;
        }
         
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
    	return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
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
    
    private void storeRegistrationId(Context context, String regId) {
    	
    	Log.d("shared gsm key...,", regId);
    	//think about sharedpreference.Is this process ok!!
    	SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    	SharedPreferences.Editor editor = preference.edit();
        editor.putString("gcmKey", regId);
    }
}
