package com.butterfly.spotter.android.service;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;

/** 
*
* @author Nadim
* @since  Dec 12, 2013
*
*/

/*
 * THis GCM service is deprecated and will be replaced
 *  by latest GoogleCloudMessaging class. 
 * */


public class GCMReceiver extends GCMBroadcastReceiver {
	
	@Override
	protected String getGCMIntentServiceClassName(Context context) { 
		return "com.butterfly.spotter.android.service.GCMIntentService"; 
	}
}
