package com.projectstimulator.BL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelAnalyzerBL {

	String Url,HttpMethod,JsonRequest,JsonExpectedResponse,JsonActualResponse,CXGUsername,CXGPassword,GherkinSyntax,JsonArrayName, JsonArrayFieldName ,
	JsonFieldName;
	static String executionFlag;
	static String sheetname;
	static List<String> Sheetnames= new ArrayList<>();
	List<String> ScenarioNames= new ArrayList<>();
	List<String> Urls= new ArrayList<>();
	List<String> HttpMethods= new ArrayList<>();
	List<String> JsonRequests= new ArrayList<>();
	List<String> JsonExpectedResponses= new ArrayList<>();
	String DBUrl,Username,Password;
	String excelFilePath = System.getProperty("user.dir")+"\\src\\main\\resources\\TestInputs.xls";
	String excelFilePath1 = System.getProperty("user.dir")+"\\src\\main\\resources\\TestExecution.xls";
	static FileInputStream inputStream;
	FileInputStream inputStream1;
	FileOutputStream outputStream ;
	static HSSFWorkbook  workbook;
	static HSSFWorkbook workbook1;
	static Sheet requestSheet;
	Sheet dbSheet;
	static Sheet executionSheet;
	static Object[][] data=null;
	static String rowname;

	static ArrayList<String> rownames= new ArrayList<>();
	static ArrayList<String> totalrownames= new ArrayList<>();
	static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();		 
	static ArrayList<String> value = new ArrayList<String>();
	{
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			inputStream1=new FileInputStream(new File(excelFilePath1));

			try {
				workbook = new HSSFWorkbook(inputStream);
				workbook1 = new HSSFWorkbook(inputStream1);

			} catch (IOException e) {
				e.printStackTrace();
			}
			dbSheet= workbook.getSheetAt(0);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}



	public static void getexecution() throws IOException{
		executionSheet = workbook1.getSheetAt(0);
		int rowItr = executionSheet.getPhysicalNumberOfRows();
		for(int i=0; i<rowItr; i++){
			if(executionSheet.getRow(i).getCell(2).getStringCellValue()!=null){
				String keyword = executionSheet.getRow(i).getCell(2).getStringCellValue();
				if(keyword.equalsIgnoreCase("Yes")){
					sheetname=executionSheet.getRow(i).getCell(1).getStringCellValue();
					Sheetnames.add(sheetname);
				}

			}
		}


	}

	public void getScenarios() throws IOException{
		getexecution();
		for(String sheetname :Sheetnames)	 { 
			requestSheet = workbook.getSheet(sheetname);
			int rowItr = requestSheet.getPhysicalNumberOfRows();
			for(int i=0; i<rowItr; i++){
				if(requestSheet.getRow(i).getCell(0).getStringCellValue()!=null){
					String rowname = requestSheet.getRow(i).getCell(0).getStringCellValue();
					ScenarioNames.add(rowname);
				}

			}
		}

	}  

	public void getvalues(String Rowname ,String sheetname ) throws IOException{
		requestSheet = workbook.getSheet(sheetname);
		int rowItr = requestSheet.getPhysicalNumberOfRows();
		Url =null;
		HttpMethod=null;
		JsonRequest=null;
		JsonExpectedResponse=null;
		JsonActualResponse=null;
		CXGUsername=null;
		CXGPassword=null;
		JsonArrayName=null;
		JsonArrayFieldName=null;
		JsonFieldName=null;

		for(int i=0; i<rowItr; i++){
			if(requestSheet.getRow(i).getCell(1).getStringCellValue()!=null){
				String keyword = requestSheet.getRow(i).getCell(1).getStringCellValue();

				if(keyword.equalsIgnoreCase(Rowname)){
					if(!(requestSheet.getRow(i).getCell(2).getStringCellValue().isEmpty())){
						GherkinSyntax = requestSheet.getRow(i).getCell(2).getStringCellValue();
					}
					if(!(requestSheet.getRow(i).getCell(3).getStringCellValue().isEmpty())){
						Url = requestSheet.getRow(i).getCell(3).getStringCellValue();
					}
					if(!(requestSheet.getRow(i).getCell(4).getStringCellValue().isEmpty())){
						HttpMethod = requestSheet.getRow(i).getCell(4).getStringCellValue();
					}
					if(!(requestSheet.getRow(i).getCell(5).getStringCellValue().isEmpty())){
						JsonRequest = requestSheet.getRow(i).getCell(5).getStringCellValue();
					}
					if(!(requestSheet.getRow(i).getCell(6).getStringCellValue().isEmpty())){
						JsonExpectedResponse = requestSheet.getRow(i).getCell(6).getStringCellValue();
					}
				} 
			}
		}
	}

	public void setvalues(String Rowname ,String sheetname, String response ) throws IOException{
		requestSheet = workbook.getSheet(sheetname);
		int rowItr = requestSheet.getPhysicalNumberOfRows();
		for(int i=0; i<rowItr; i++){
			if(requestSheet.getRow(i).getCell(1).getStringCellValue()!=null){
				String keyword = requestSheet.getRow(i).getCell(1).getStringCellValue();
				if(keyword.equalsIgnoreCase(Rowname)){
					Row row=requestSheet.getRow(i);
					Cell cell=row.createCell(7);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(response);
				} 
			}
		}
	}
	
	public String getJsonActualResponse(String Rowname ,String sheetname) throws IOException{
		requestSheet = workbook.getSheet(sheetname);
		int rowItr = requestSheet.getPhysicalNumberOfRows();
		for(int i=0; i<rowItr; i++){
			if(requestSheet.getRow(i).getCell(1).getStringCellValue()!=null){
				String keyword = requestSheet.getRow(i).getCell(1).getStringCellValue();
				if(keyword.equalsIgnoreCase(Rowname)){
					JsonActualResponse = requestSheet.getRow(i).getCell(7).getStringCellValue();
				}
			}
		} return JsonActualResponse;

	}

	public String getJsonExpectedResponse(String Rowname ,String sheetname) throws IOException{
		requestSheet = workbook.getSheet(sheetname);
		int rowItr = requestSheet.getPhysicalNumberOfRows();
		for(int i=0; i<rowItr; i++){

			if(requestSheet.getRow(i).getCell(0).getStringCellValue()!=null){
				String keyword = requestSheet.getRow(i).getCell(0).getStringCellValue();
				if(keyword.equalsIgnoreCase(Rowname)){
					JsonExpectedResponse = requestSheet.getRow(i).getCell(5).getStringCellValue();

				}
			}
		}
		return JsonExpectedResponse;
	}

	public void getDBConnection(String DBname) throws IOException{

		int rowItr = dbSheet.getPhysicalNumberOfRows();
		for(int i=0; i<rowItr; i++){

			if(dbSheet.getRow(i).getCell(0).getStringCellValue()!=null){
				String keyword = dbSheet.getRow(i).getCell(0).getStringCellValue();
				if(keyword.equalsIgnoreCase(DBname)){
					DBUrl = dbSheet.getRow(i).getCell(1).getStringCellValue();
					System.out.println("DBURL is******** "+DBUrl);
					if(!(dbSheet.getRow(i).getCell(2).getStringCellValue().isEmpty())){
						Username = dbSheet.getRow(i).getCell(2).getStringCellValue();
						System.out.println("DB Username******"+Username);
					}
					if(!(dbSheet.getRow(i).getCell(3).getStringCellValue().isEmpty())){
						Password = dbSheet.getRow(i).getCell(3).getStringCellValue();
						System.out.println("DB Pwd is *****"+Password);
					}
				} 
			}
		}
	}

	

	public static Object[][] testresults() throws IOException {	 
		getexecution();
		int size= Sheetnames.size();
		for(int in=0;in<size;in++)	 { 
			String sheet = Sheetnames.get(in);
			requestSheet = workbook.getSheet(sheet);
			int rowItr = requestSheet.getPhysicalNumberOfRows();
			for(int i=1; i<rowItr; i++){
				if(requestSheet.getRow(i).getCell(0).getStringCellValue()!=null){
					executionFlag = requestSheet.getRow(i).getCell(0).getStringCellValue();
					if (executionFlag.equalsIgnoreCase("Yes")){
						rowname = requestSheet.getRow(i).getCell(1).getStringCellValue();
						rownames.add(rowname);
					}
				}
			}
			ArrayList<String> rownamess= new ArrayList<>();
			rownamess.addAll(rownames);
			map.put(sheet, rownamess);
			totalrownames.addAll(rownames);
			rownames.removeAll(rownames);
		}
		data= new Object[totalrownames.size()][2];
		int i =0;
		for (Map.Entry entry:map.entrySet()){
			System.out.println(entry.getKey());
			String str=entry.getKey().toString();
			value = (ArrayList<String>) entry.getValue();
			int si =value.size();
			for(int j=0;j<si;j++){
				data[i][0]=str;
				data[i][1] =value.get(j);
				i++;
			}
			value.removeAll(value);
		}
		return data;
	}

	public Map<String, ArrayList<String>> getScenarionames(String Sheetname) throws IOException{
		getexecution();
		requestSheet = workbook.getSheet(Sheetname);
		int rowItr = requestSheet.getPhysicalNumberOfRows();

		for(int i=1; i<rowItr; i++){
			if(requestSheet.getRow(i).getCell(0).getStringCellValue()!=null){
				rowname = requestSheet.getRow(i).getCell(0).getStringCellValue();
				rownames.add(rowname);
			}
		}
		ArrayList<String> rownamess= new ArrayList<>();
		rownamess.addAll(rownames);
		map.put(Sheetname, rownamess);
		totalrownames.addAll(rownames);
		rownames.removeAll(rownames);    	  
		return map;
	}
	
	public Object[][] getflagavlues(){
		return data;
	}
	
	public String getDBUrl() throws IOException{
		return DBUrl;

	}

	public String getUsername() throws IOException{

		return Username;
	}

	public String getPassword() throws IOException{
		return Password;

	}

	public void closeWorkbook() throws IOException{
		inputStream.close();
		workbook.close();

	}

	public List<String> getScenarioNames() throws IOException{
		return ScenarioNames;

	}
	public List<String> getSheetnames() throws IOException{
		return Sheetnames;

	}

	public List<String> getHttpMethods() throws IOException{

		return HttpMethods;
	}

	public List<String> getJsonRequests() throws IOException{

		return JsonRequests;
	}

	public String getGherkinSyntax() throws IOException{
		return GherkinSyntax;

	}

	public String getUrl() throws IOException{
		return Url;

	}

	public String getHttpMethod() throws IOException{

		return HttpMethod;
	}

	public String getJsonRequest() throws IOException{

		return JsonRequest;
	}
	public String getCXGUsername() throws IOException{
		return CXGUsername;

	}
	public String getCXGPassword() throws IOException{
		return CXGPassword;

	}
	public String getJsonArrayName() throws IOException{
		return JsonArrayName;

	}

	public String getJsonFieldName() throws IOException{
		return JsonFieldName;

	}


}
