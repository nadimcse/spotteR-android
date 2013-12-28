package com.butterfly.spotter.android.service;

import com.butterfly.spotter.android.database.DatabaseHelper;
import com.butterfly.spotter.android.model.DataObject;
import com.butterfly.spotter.android.model.Message;
import com.butterfly.spotter.android.model.StatusCode;
import com.butterfly.spotter.android.singleton.SingletonAccess;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/** 
*
* @author Nadim
* @since  Dec 15, 2013
*
*/

public class ResponseDataService {
	private Context context;
	public ResponseDataService(Context context) {
		this.context = context;
	}
	
	public void handleResponseData(String message) {
		///can be moved????
		Gson gson = new Gson();
		DataObject data = gson.fromJson(message, DataObject.class);
		
		if (StatusCode.SEND_MESSAGE_REQUEST.name().equals(data.getStatus())) {
	        Log.d("true .......", data.getMessage() + "---" + data.getSenderId());
			processMessageRequest(data);
		}
		
		if (StatusCode.PEER_CONFORMATION_REQUEST.name().equals(data.getStatus())) {
	        Log.d("true11 .......", data.getMessage() + "---" + data.getSenderId());
			processPeerConformationRequest(context);

		}
		
	}
	
	public void processMessageRequest(DataObject data) {
		//inset into database
		DatabaseHelper databaseHelper = SingletonAccess.INSTANCE.getSingleton().getDatabaseHelper();		
		databaseHelper.addMessage(new Message(data.getSenderId(), data.getMessage()));
		///notify app
	}
	
	public void processPeerConformationRequest(Context context) {
		Intent intent = new Intent();
		intent.setAction("com.butterfly.spotter.android.CUSTOM_INTENT");
		context.sendBroadcast(intent); 	
	}
}
