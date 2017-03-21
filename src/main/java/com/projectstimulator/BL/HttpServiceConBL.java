package com.projectstimulator.BL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpServiceConBL {
	DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
	HttpResponse response;
	String strServerResponse;

	public  void restPostConnection(String RequestURL ,String JsonPath){

		try {
			strServerResponse=null;
			HttpPost localHttpPost = new HttpPost(RequestURL);
			StringEntity params =new StringEntity(JsonPath);
			localHttpPost.addHeader("content-type", "application/json");
			localHttpPost.addHeader("Accept","application/json");
			localHttpPost.setEntity(params);

			response = localDefaultHttpClient.execute(localHttpPost);
			strServerResponse = EntityUtils.toString(response.getEntity());
			System.out.println("the reposne is ---------"+strServerResponse);
			System.out.println("the reponse code is ************"+response.getStatusLine().getStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getResponse(){

		return strServerResponse;
	}

	public  void restGetConnection(String RequestURL) {
		try
		{
			strServerResponse=null;
			HttpGet localHttpPost = new HttpGet(RequestURL);
			response = localDefaultHttpClient.execute(localHttpPost);
			strServerResponse = EntityUtils.toString(response.getEntity());
			//	     System.out.println("the reposne is ---------"+strServerResponse);
			System.out.println("the reponse code is ************"+response.getStatusLine().getStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public  void restPutConnection(String RequestURL ,String JsonPath){

		try {
			strServerResponse=null;
			HttpPut localHttpPut = new HttpPut(RequestURL);     
			StringEntity params =new StringEntity(JsonPath);
			localHttpPut.addHeader("content-type", "application/json");
			localHttpPut.addHeader("Accept","application/json");
			localHttpPut.setEntity(params);
			response = localDefaultHttpClient.execute(localHttpPut);
			strServerResponse = EntityUtils.toString(response.getEntity());
			System.out.println("the reposne is ---------"+strServerResponse);
			System.out.println("the reponse code is ************"+response.getStatusLine().getStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(){
		localDefaultHttpClient.getConnectionManager().shutdown();
	}

}
