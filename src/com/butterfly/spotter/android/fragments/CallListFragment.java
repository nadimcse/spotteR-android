package com.butterfly.spotter.android.fragments;

import java.util.ArrayList;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.listener.SwitchFragmentListener;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** 
*
* @author Nadim
* @since  Dec 2, 2013
*
*/
public class CallListFragment extends ListFragment implements FragmentHandler {
    public static String currentPeer;
	private ArrayList<String> itemsArray = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private SwitchFragmentListener fragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("createing..." , "new cretaed");
        itemsArray.add("0191734346");
        itemsArray.add("0191731111");
        itemsArray.add("0191733454");
        itemsArray.add("0191000000");
        itemsArray.add("0191731111");
        itemsArray.add("0191799988");
        itemsArray.add("0191734234");
        itemsArray.add("0191733333");
        itemsArray.add("0191734278");
        itemsArray.add("0191724555");
        
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.call_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Populates list with our static array
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemsArray);
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast t = Toast.makeText(getActivity(), "call Message" + "-" + adapter.getItem(position),
        Toast.LENGTH_SHORT);
        t.show();
        currentPeer = adapter.getItem(position);
        fragmentListener.switchFragment(ChatFragment.class);

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
    
	public String fragmentName() {
		return "call";
	}
	
	public void renderViewBasedOnEvent() {

		///apply logic
	}

}
