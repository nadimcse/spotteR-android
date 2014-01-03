package com.butterfly.spotter.android.singleton;

import java.util.concurrent.BlockingQueue;

import android.content.Context;

import com.butterfly.spotter.android.database.DatabaseHelper;
import com.butterfly.spotter.android.service.ResponseDataService;

/** 
*
* @author Nadim
* @since  Dec 17, 2013
*
*/

/*
* Here all singleton objects are defined.
* Better to use custom annotation here but will be used in future :).
* */
public class Singleton {
	private ResponseDataService responseDataService;
	private DatabaseHelper databaseHelper;
	
	public Singleton(Context context) {
		databaseHelper = new DatabaseHelper(context);
		responseDataService = new ResponseDataService(context);
	}
	
	
	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
	
	public ResponseDataService getResponseDataService() {
		return responseDataService;
	}
}
