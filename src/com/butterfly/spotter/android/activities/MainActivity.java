package com.butterfly.spotter.android.activities;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.butterfly.spotter.android.R;
import com.butterfly.spotter.android.fragments.CallListFragment;
import com.butterfly.spotter.android.fragments.ChatFragment;
import com.butterfly.spotter.android.fragments.GCMLoaderFragment;
import com.butterfly.spotter.android.fragments.LoginFragment;
import com.butterfly.spotter.android.fragments.MapFragment;
import com.butterfly.spotter.android.listener.SwitchFragmentListener;
import com.butterfly.spotter.android.service.MapHttpService;
import com.butterfly.spotter.android.service.MessageHttpService;
import com.butterfly.spotter.android.singleton.Singleton;
import com.butterfly.spotter.android.singleton.SingletonAccess;

import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

/** 
 *
 * @author Nadim
 * @since  Dec 2, 2013
 *
*/

public class MainActivity extends FragmentActivity implements SwitchFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
    	final Context context = this;
        super.onCreate(savedInstanceState);

        //initialize all singleton objects
        createAllSingletons(context);


        //get GCM ID
        //register GCM a HeadLess fragment
        initializeGCMFragment();

        //render activities View
        setContentView(R.layout.activity_main);

        initFragments();
       
/*        ///will be refactored
        Executor messageEXecutor = Executors.newSingleThreadExecutor();
        messageEXecutor.execute(new MessageHttpService());
        Executor mapEXecutor = Executors.newSingleThreadExecutor();
        mapEXecutor.execute(new MapHttpService());
*/
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
    
    private void initFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
	    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

	      fragmentTransaction
	      .add(R.id.activity_container, new CallListFragment())
	      .add(R.id.activity_container, new LoginFragment())
	      .add(R.id.activity_container, new ChatFragment())
	      .add(R.id.activity_container,  new MapFragment())
	      .commit();
   
    }
    
    public void switchFragment(Class<?> clazz) {
        FragmentManager fragmentManager = getSupportFragmentManager();
	    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    	for (Fragment fragment : getSupportFragmentManager().getFragments()) {
    		if (clazz.equals(fragment.getClass())) {
    			fragmentTransaction.show(fragment);
    		} else {
        		fragmentTransaction.hide(fragment);
    			
    		}
    	}
    	fragmentTransaction.commit();
    }
}
