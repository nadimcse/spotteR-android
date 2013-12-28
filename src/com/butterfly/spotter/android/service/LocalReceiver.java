package com.butterfly.spotter.android.service;

import com.butterfly.spotter.android.activities.IncomingCallScreenActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/** 
*
* @author Nadim
* @since  Dec 18, 2013
*
*/


public class LocalReceiver  extends BroadcastReceiver  {
	
	   @Override
	   public void onReceive(Context context, Intent intent) {
		   Intent i = new Intent(context, IncomingCallScreenActivity.class);
		   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);		   
		   context.startActivity(i);
	   }

}
