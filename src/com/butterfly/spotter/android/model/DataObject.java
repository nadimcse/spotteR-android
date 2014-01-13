package com.butterfly.spotter.android.model;

import java.util.Map;

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
	private String authKey;
	private Map<String, String> urlMap;
	
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

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
}
