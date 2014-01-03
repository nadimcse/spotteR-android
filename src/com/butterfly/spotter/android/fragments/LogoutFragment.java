package com.butterfly.spotter.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.butterfly.spotter.android.R;

/** 
*
* @author Nadim
* @since  Jan 3, 2014
*
*/
public class LogoutFragment extends Fragment {
	   @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
	        View view = inflater.inflate(R.layout.logout_fragment, container, false);
	        return view;
	    }


}
