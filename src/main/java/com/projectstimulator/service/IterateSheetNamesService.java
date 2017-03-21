package com.projectstimulator.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class IterateSheetNamesService {

	public List<String> getSheetNames(String file){    
		List<String> sheetNames = new ArrayList<String>();
		try{
			File myFile = new File("C:/Users/ViVeK/Desktop"+file);
			FileInputStream infile = new FileInputStream(myFile);
			HSSFWorkbook wb = new HSSFWorkbook(infile);
			for (int i=0; i<wb.getNumberOfSheets(); i++) {
				sheetNames.add( wb.getSheetName(i));
			}
			return sheetNames;
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return sheetNames;
	}
}
