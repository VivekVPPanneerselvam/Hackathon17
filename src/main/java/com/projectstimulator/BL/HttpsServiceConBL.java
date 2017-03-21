package com.projectstimulator.BL;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpsServiceConBL {
	DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
	HttpResponse response;
	String strServerResponse;

	public  void restPostConnection(String RequestURL ,String JsonPath , String Username, String Password){

		try {
			strServerResponse=null;
			System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir")+"\\src\\main\\resources\\testTrustStore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "changeme");
			HttpPost localHttpPost = new HttpPost(RequestURL);
			byte[] decodedBytes = Base64.decodeBase64(Password);
			System.out.println("decodedBytes " + new String(decodedBytes));
			String auth = Username + ":" + new String(decodedBytes);
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
			String authHeader = "Basic " + new String(encodedAuth);
			StringEntity params =new StringEntity(JsonPath);
			localHttpPost.addHeader("content-type", "application/json");
			localHttpPost.addHeader("Accept","application/json");
			localHttpPost.setEntity(params);
			localHttpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			response = localDefaultHttpClient.execute(localHttpPost);
			strServerResponse = EntityUtils.toString(response.getEntity());
			System.out.println("the reposne is ---------"+strServerResponse);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getResponse(){

		return strServerResponse;
	}

	public  void restGetConnection(String RequestURL,String Username, String Password) {
		try
		{
			strServerResponse=null;
			System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir")+"\\src\\main\\resources\\testTrustStore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "changeme");
			HttpGet localHttpPost = new HttpGet(RequestURL);
			byte[] decodedBytes = Base64.decodeBase64(Password);
			System.out.println("decodedBytes " + new String(decodedBytes));
			String auth = Username + ":" + new String(decodedBytes);
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
			String authHeader = "Basic " + new String(encodedAuth);
			localHttpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			response = localDefaultHttpClient.execute(localHttpPost);
			strServerResponse = EntityUtils.toString(response.getEntity());
			System.out.println("the reponse code is ************"+response.getStatusLine().getStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public  void restPutConnection(String RequestURL ,String JsonPath,String Username, String Password){

		try {
			strServerResponse=null;
			System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir")+"\\src\\main\\resources\\testTrustStore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "changeme");

			HttpPut localHttpPut = new HttpPut(RequestURL); 
			String auth = Username + ":" + Password;
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
			String authHeader = "Basic " + new String(encodedAuth);
			StringEntity params =new StringEntity(JsonPath);
			localHttpPut.addHeader("content-type", "application/json");
			localHttpPut.addHeader("Accept","application/json");
			localHttpPut.setEntity(params);
			localHttpPut.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

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
