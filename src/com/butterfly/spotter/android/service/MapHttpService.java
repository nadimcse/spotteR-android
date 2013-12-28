package com.butterfly.spotter.android.service;

import java.util.concurrent.BlockingQueue;
import com.butterfly.spotter.android.singleton.SingletonAccess;

import android.util.Log;

/** 
*
* @author Nadim
* @since  Dec 13, 2013
*
*/

public class MapHttpService implements Runnable {
	
	private BlockingQueue<String> queue; 
	
	public MapHttpService() {
		queue = SingletonAccess.INSTANCE.getSingleton().getMapQueue();
	}
	
	@Override
	public void run() {
		String value;
		try {
			while((value =queue.take()) != null) {
		        Log.d("value in MaphttpService........", value);

				new ResourceHandler().get();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
}
