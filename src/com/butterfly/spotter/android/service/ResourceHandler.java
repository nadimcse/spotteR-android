package com.butterfly.spotter.android.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;


import android.util.Log;

/** 
*
* @author Nadim
* @since  Dec 13, 2013
*
*/

public class ResourceHandler {
	HttpResponse response;
	HttpGet httpGet; 
	HttpClient client;
	HttpPost httpPost;
	HttpEntity entity;
	public String post(String message) {
		httpPost = new HttpPost("url.....");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("IDToken1", "username"));
        nvps.add(new BasicNameValuePair("IDToken2", "password"));
        
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
	        response = client.execute(httpPost);
	        entity = response.getEntity();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return String.valueOf(entity.getContentLength());
	}
	
	public String get() {
		client = new DefaultHttpClient();
		httpGet = new HttpGet("http://www.google.com");
		ResponseHandler<String> handler = new BasicResponseHandler();

		String body = null;
		try {
			response = client.execute(httpGet);
			body = handler.handleResponse(response);
			int code = response.getStatusLine().getStatusCode();
	        Log.d("code........", body + "----" + code);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body;
	} 
}
