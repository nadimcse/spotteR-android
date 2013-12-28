package com.butterfly.spotter.android.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.butterfly.spotter.android.singleton.SingletonAccess;
import com.google.android.gcm.GCMBaseIntentService;

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

public class GCMIntentService extends GCMBaseIntentService {

	static final String SENDER_ID = "942103772492";
	private ResponseDataService responseDataService;
	public GCMIntentService() {
		super(SENDER_ID);
			this.responseDataService = SingletonAccess.INSTANCE.getSingleton().getResponseDataService();
		}
		 
		private static final String TAG = "===GCMIntentService===";
		 
		 
		@Override
		protected void onRegistered(Context arg0, String registrationId) {
		Log.i(TAG, "Device registered: regId = " + registrationId);
		}
		 
		@Override
		protected void onUnregistered(Context arg0, String arg1) {
		Log.i(TAG, "unregistered = "+arg1);
		}
		 
		@Override
		protected void onMessage(Context arg0, Intent arg1) {
		Log.i(TAG, "new message= " + arg1.getExtras().getString("message"));
			responseDataService.handleResponseData(arg1.getExtras().getString("message"));
	
			
		}
		 
		@Override
		protected void onError(Context arg0, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
		}
		 
		@Override
		protected boolean onRecoverableError(Context context, String errorId) {
		return super.onRecoverableError(context, errorId);
		}

}
