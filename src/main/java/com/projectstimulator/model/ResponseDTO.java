package com.projectstimulator.model;

import java.util.List;

public class ResponseDTO {
	private static List<String> sheetName;
	private static int statusCode;
	private static String statusDesc;
	public static List<String> getSheetName() {
		return sheetName;
	}
	public static void setSheetName(List<String> sheetName) {
		ResponseDTO.sheetName = sheetName;
	}
	public static int getStatusCode() {
		return statusCode;
	}
	public static void setStatusCode(int statusCode) {
		ResponseDTO.statusCode = statusCode;
	}
	public static String getStatusDesc() {
		return statusDesc;
	}
	public static void setStatusDesc(String statusDesc) {
		ResponseDTO.statusDesc = statusDesc;
	}
	
}
