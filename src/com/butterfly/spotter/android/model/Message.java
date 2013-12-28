package com.butterfly.spotter.android.model;

/** 
*
* @author Nadim
* @since  Dec 10, 2013
*
*/

public class Message {
	private int id;
	private String phoneId;
	private String message;
	private String created;
	
	public Message() {
		
	}
	
	public Message(String phoneId, String message) {
		this.phoneId = phoneId;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneId() {
		return phoneId;
	}
	
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreated() {
		return created;
	}
	
	public void setCreated(String created) {
		this.created = created;
	}
}
