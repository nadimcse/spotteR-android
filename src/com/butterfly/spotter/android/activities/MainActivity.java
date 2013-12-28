package com.butterfly.spotter.android.activities;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.adapter.TabsAdapter;
import com.butterfly.spotter.android.fragments.CallListFragment.OnCallListItemSelectedLisener;
import com.butterfly.spotter.android.fragments.ChatFragment;
import com.butterfly.spotter.android.fragments.FragmentHandler;
import com.butterfly.spotter.android.fragments.GCMLoaderFragment;
import com.butterfly.spotter.android.fragments.MapFragment;
import com.butterfly.spotter.android.service.MapHttpService;
import com.butterfly.spotter.android.service.MessageHttpService;
import com.butterfly.spotter.android.singleton.Singleton;
import com.butterfly.spotter.android.singleton.SingletonAccess;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/** 
 *
 * @author Nadim
 * @since  Dec 2, 2013
 *
*/

public class MainActivity extends FragmentActivity implements OnCallListItemSelectedLisener {

	private TabHost tabHost;
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	final Context context = this;
        super.onCreate(savedInstanceState);

        //initialize all singleton objects
        createAllSingletons(context);

       if (!LoginActivity.loogedIn) {
        	  Intent intent = new Intent(context, LoginActivity.class);
              startActivity(intent);   

        }

        //get GCM ID
        //register GCM a HeadLess fragment
        initializeGCMFragment();

        //render activities View
        setContentView(R.layout.activity_main);

        
        //render CallList fragment
      //  renderCallListFragment();
       
        
        // renderTabs;
        renderTabs(context, savedInstanceState);
        
        ///will be refactored
        Executor messageEXecutor = Executors.newSingleThreadExecutor();
        messageEXecutor.execute(new MessageHttpService());
        Executor mapEXecutor = Executors.newSingleThreadExecutor();
        mapEXecutor.execute(new MapHttpService());
   
      
       /* for (Fragment ff : getSupportFragmentManager().getFragments()) {
        		getSupportFragmentManager().beginTransaction()
        		.hide(ff).commit();
        }*/
        

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void createAllSingletons(Context context) {
 	   Singleton singleton = new Singleton(context);
 	   SingletonAccess.INSTANCE.setSingleton(singleton);
    }

    private void initializeGCMFragment() {
   	 getSupportFragmentManager().beginTransaction()
        .add(new GCMLoaderFragment(), "GCMFragment")
        .commit();
   }
    
  /*  public void renderCallListFragment() {
      	 getSupportFragmentManager().beginTransaction()
         .add(R.id.call_list_fragment)
         .commit();
    	
    }*/
   
   
   private void renderTabs(Context context, Bundle savedInstanceState) {
	   tabHost = (TabHost)findViewById(android.R.id.tabhost);	 
       tabHost.setup();
       
       viewPager = (ViewPager)findViewById(R.id.pager);
       tabsAdapter = new TabsAdapter((FragmentActivity) context, tabHost, viewPager);

       tabsAdapter.addTab(tabHost.newTabSpec("chat").setIndicator("Chat"), ChatFragment.class, null);
       tabsAdapter.addTab(tabHost.newTabSpec("map").setIndicator("Map"), MapFragment.class, null);

       
       if (savedInstanceState != null)
       {
           tabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
       }
   	
   }

    public void reLoadSelectedTabView() {
        Log.d("TAb tag...", "" + tabHost.getCurrentTabTag());
    	
    	FragmentHandler fragHandler = getFragmentBasedOnCurrentTab();
    	fragHandler.renderViewBasedOnEvent();
    }
    
    private FragmentHandler getFragmentBasedOnCurrentTab() {
    	  for (Fragment frag : getSupportFragmentManager().getFragments()) {
    		  
    		  if ((frag instanceof FragmentHandler) &&
    				  tabHost.getCurrentTabTag().equals(((FragmentHandler) frag).fragmentName())) {
    			  	return (FragmentHandler) frag;
    		  }
          }
    	  
    	  //check null???
    	  return null;
    }
}
