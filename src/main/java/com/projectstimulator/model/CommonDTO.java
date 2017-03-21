package com.projectstimulator.model;

import java.util.List;


public class CommonDTO {
	
	private static Object[][] flagValues;
	private static String dbUrl;
	private static String username;
	private static String password;
	private static List<String> scenarioNames;
	private static List<String> sheetnames;
	private static List<String> httpMethods;
	private static List<String> jsonRequests;
	private static String gherkinSyntax;
	private static String url;
	private static String httpMethod;
	private static String jsonRequest;
	private static String  cXGUsername;
	private static String cXGPassword;
	private static String jsonArrayName;
	private static String  jsonFieldName;
	public static Object[][] getFlagValues() {
		return flagValues;
	}
	public static void setFlagValues(Object[][] flagValues) {
		CommonDTO.flagValues = flagValues;
	}
	
	public static String getDbUrl() {
		return dbUrl;
	}
	public static void setDbUrl(String dbUrl) {
		CommonDTO.dbUrl = dbUrl;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		CommonDTO.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		CommonDTO.password = password;
	}
	public static List<String> getScenarioNames() {
		return scenarioNames;
	}
	public static void setScenarioNames(List<String> scenarioNames) {
		CommonDTO.scenarioNames = scenarioNames;
	}
	public static List<String> getSheetnames() {
		return sheetnames;
	}
	public static void setSheetnames(List<String> sheetnames) {
		CommonDTO.sheetnames = sheetnames;
	}
	public static List<String> getHttpMethods() {
		return httpMethods;
	}
	public static void setHttpMethods(List<String> httpMethods) {
		CommonDTO.httpMethods = httpMethods;
	}
	public static List<String> getJsonRequests() {
		return jsonRequests;
	}
	public static void setJsonRequests(List<String> jsonRequests) {
		CommonDTO.jsonRequests = jsonRequests;
	}
	public static String getGherkinSyntax() {
		return gherkinSyntax;
	}
	public static void setGherkinSyntax(String gherkinSyntax) {
		CommonDTO.gherkinSyntax = gherkinSyntax;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		CommonDTO.url = url;
	}
	public static String getHttpMethod() {
		return httpMethod;
	}
	public static void setHttpMethod(String httpMethod) {
		CommonDTO.httpMethod = httpMethod;
	}
	public static String getJsonRequest() {
		return jsonRequest;
	}
	public static void setJsonRequest(String jsonRequest) {
		CommonDTO.jsonRequest = jsonRequest;
	}
	public static String getcXGUsername() {
		return cXGUsername;
	}
	public static void setcXGUsername(String cXGUsername) {
		CommonDTO.cXGUsername = cXGUsername;
	}
	public static String getcXGPassword() {
		return cXGPassword;
	}
	public static void setcXGPassword(String cXGPassword) {
		CommonDTO.cXGPassword = cXGPassword;
	}
	public static String getJsonArrayName() {
		return jsonArrayName;
	}
	public static void setJsonArrayName(String jsonArrayName) {
		CommonDTO.jsonArrayName = jsonArrayName;
	}
	public static String getJsonFieldName() {
		return jsonFieldName;
	}
	public static void setJsonFieldName(String jsonFieldName) {
		CommonDTO.jsonFieldName = jsonFieldName;
	}
	
	
	

}
