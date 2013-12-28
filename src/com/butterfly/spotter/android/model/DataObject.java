package com.butterfly.spotter.android.model;

/** 
*
* @author Nadim
* @since  Dec 15, 2013
*
*/

public class DataObject {

	private String status;
	private String groupId;
	private String senderId;
	private String message;
	private String xCoordinate;
	private String yCoordinate;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSenderId() {
		return senderId;
	}
	
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getXCoordinate() {
		return xCoordinate;
	}
	
	public void setXCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public String getYCoordinate() {
		return yCoordinate;
	}
	
	public void setYCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
