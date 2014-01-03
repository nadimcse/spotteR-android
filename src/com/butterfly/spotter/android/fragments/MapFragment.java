package com.butterfly.spotter.android.fragments;


import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.listener.SwitchFragmentListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/** 
*
* @author Nadim
* @since  Dec 2, 2013
*
*/
public class MapFragment extends Fragment implements FragmentHandler {
    private SwitchFragmentListener fragmentListener;
    private Button  chatBtn; 
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        toChatEditorToggler(view);
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
        renderViewBasedOnEvent();
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
    
	public String fragmentName() {
		return "map";
	}
	
	public void renderViewBasedOnEvent() {

		///apply logic
	}
	
	private void toChatEditorToggler(View view) {
		   chatBtn = (Button) view.findViewById(R.id.chatFragmentBtn);
		   chatBtn.setOnClickListener(new OnClickListener() {
	    		@Override
				public void onClick(View v) {
	    			fragmentListener.switchFragment(ChatFragment.class);
	    		}
	    	});
	}
}
