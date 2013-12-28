package com.butterfly.spotter.android.singleton;

import java.util.concurrent.BlockingQueue;

import android.content.Context;

import com.butterfly.spotter.android.database.DatabaseHelper;
import com.butterfly.spotter.android.service.MapQueueService;
import com.butterfly.spotter.android.service.MessageQueueService;
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
	private MessageQueueService messageQueueService;
	private MapQueueService mapQueueService;
	private ResponseDataService responseDataService;
	private DatabaseHelper databaseHelper;
	
	public Singleton(Context context) {
		messageQueueService = new MessageQueueService();
		mapQueueService = new MapQueueService();
		databaseHelper = new DatabaseHelper(context);
		responseDataService = new ResponseDataService(context);
	}
	
	public BlockingQueue<String> getMessageQueue() {
		return messageQueueService.getQueue();
	}
	
	public BlockingQueue<String> getMapQueue() {
		return mapQueueService.getQueue();
	}
	
	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
	
	public ResponseDataService getResponseDataService() {
		return responseDataService;
	}
}
