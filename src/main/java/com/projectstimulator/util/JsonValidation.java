package com.projectstimulator.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonValidation {

	String responseStatus,message,field,jsonNonarray,objectValue;
	List<String> values= new ArrayList<>();
	String value = null;
	int reasonCode = 0;
	JSONObject jsonObject = new JSONObject();
	/*Map<String,JsonNode> fields = new HashMap<String,JsonNode>();

	public Map<String,JsonNode> jSONResponseVerification(String response) throws JsonProcessingException, IOException{
		JsonFactory factory = null;
		ObjectMapper  mapper =new ObjectMapper(factory) ;
		JsonNode rootNode = mapper.readTree(response);  
		Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {
			Map.Entry<String,JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
			fields.put(field.getKey(), field.getValue());
		}
		System.out.println( "The Expect and Actual"+fields);
		return fields;
	}*/

	public List<String> getResponseFields(String response, String arrayname, String Keyname) throws JSONException{
		jsonObject = new JSONObject(response);
		JSONArray jsondata = jsonObject.getJSONArray(arrayname);
		if(jsondata.length()==0){
			values=null;
		}
		else 
			System.out.println(jsondata.length());
		for(int k = 0; k < jsondata.length(); k++){
			JSONObject obj1 = jsondata.getJSONObject(k);
			value = obj1.getString(Keyname);
			values.add(value);
			System.out.println("afdnjklasdf" +value);	
		}
		return values;
	}

	public String getResponseField(String response, String arrayname, String Keyname) throws JSONException{
		jsonObject = new JSONObject(response);
		JSONArray jsondata = jsonObject.getJSONArray(arrayname);
		if(jsondata.length()==0){
			value=null;
		}
		else 
			for(int k = 0; k < jsondata.length(); k++){
				JSONObject obj1 = jsondata.getJSONObject(k);
				value = obj1.getString(Keyname);

				System.out.println("afdnjklasdf" +value);	
			}
		return value;
	}

	public String getBaseResponseStatus(String response , String Fieldname ) throws JSONException{
		jsonObject = new JSONObject(response);
		responseStatus= jsonObject.getJSONObject("baseResponse").getString(Fieldname);
		return responseStatus;

	}

	public int getBaseResponseCode(String response, String Fieldname) throws JSONException{
		jsonObject = new JSONObject(response);
		reasonCode= jsonObject.getJSONObject("baseResponse").getInt(Fieldname);
		return reasonCode;

	} 

	public String getfield(String response ,String fieldname) throws JSONException{
		jsonObject = new JSONObject(response);
		field= jsonObject.getString(fieldname);
		return field;

	}
	public String getResponseStatus(String response , String Object ,String Fieldname ) throws JSONException{
		jsonObject = new JSONObject(response);
		jsonNonarray= jsonObject.getJSONObject(Object).getString(Fieldname);
		return jsonNonarray;

	}
	public String getResponseStatusObject(String response, String arrayname, String Object,String Keyname) throws JSONException{
		jsonObject = new JSONObject(response);
		JSONArray jsondata = jsonObject.getJSONArray(arrayname);
		if(jsondata.length()==0){
			objectValue=null;
		}
		else 
			for(int k = 0; k < jsondata.length(); k++){
				JSONObject obj1 = jsondata.getJSONObject(k).getJSONObject(Object);
				objectValue = obj1.getString(Keyname);


				System.out.println("afdnjklasdf" +objectValue);	
			}
		return objectValue;
	}

}
