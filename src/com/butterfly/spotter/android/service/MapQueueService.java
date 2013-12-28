package com.butterfly.spotter.android.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
*
* @author Nadim
* @since  Dec 13, 2013
*
*/

public class MapQueueService {
	private BlockingQueue<String> queue;
	
	public MapQueueService() {
		queue = new ArrayBlockingQueue<String>(10);
	}

	public BlockingQueue<String> getQueue() {
		return queue;
	}

	
}
