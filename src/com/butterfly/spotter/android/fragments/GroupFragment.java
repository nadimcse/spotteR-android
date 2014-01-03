package com.butterfly.spotter.android.fragments;

import com.butterfly.spotter.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** 
*
* @author Nadim
* @since  Jan 3, 2014
*
*/

public class GroupFragment extends Fragment {
	   @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
	        View view = inflater.inflate(R.layout.group_fragment, container, false);
	        return view;
	    }


}
