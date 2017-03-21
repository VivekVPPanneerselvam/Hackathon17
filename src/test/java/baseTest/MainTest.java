package baseTest;

import java.io.IOException;
import java.net.URISyntaxException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.projectstimulator.BL.ExcelAnalyzerBL;
import com.projectstimulator.BL.DbConnection;
import com.projectstimulator.BL.HttpServiceConBL;
import com.projectstimulator.BL.HttpsServiceConBL;
import com.projectstimulator.util.JsonValidation;
import com.projectstimulator.model.CommonDTO;


public class MainTest {

	static ExcelAnalyzerBL excel =new ExcelAnalyzerBL();
	JsonValidation json= new JsonValidation();
	static CommonDTO commonDTO = new CommonDTO();
	String Url, HttpMethod,Request, Response, Expected, Actual, CXGUsername, CXGPassword,GherkinSyntax;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void startReport()
	{
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/TestNG_TestResults.html", true);
		extent.addSystemInfo("Environment", "Test");
	}

	@DataProvider(name = "test1")
	public static Object[][] testresults() throws IOException {
		excel.testresults();
		Object[][] datas=excel.getflagavlues();
		return datas;
	}

	@Test(dataProvider = "test1")
	public  void Scenarios(String sheetname , String testcase) throws IOException, URISyntaxException {
		HttpServiceConBL httpURL = new HttpServiceConBL();
		HttpsServiceConBL httpsURL = new HttpsServiceConBL();
		test=extent.startTest(sheetname+"_"+testcase);
		Reporter.log(sheetname+"_"+testcase);
			excel.getvalues(testcase,sheetname);
			GherkinSyntax=excel.getGherkinSyntax();
			String []str=GherkinSyntax.split("When:");
			String Given =str[0];
			String []str1= str[1].split("Then:");
			String When= "When:"+str1[0];
			String Then= "Then:"+str1[1];
			test.log(LogStatus.INFO,"The Gherkin Syntax ");
			test.log(LogStatus.INFO,Given);
			test.log(LogStatus.INFO,When);
			test.log(LogStatus.INFO,Then);
			Url=excel.getUrl();
			test.log(LogStatus.INFO,"The CGX Request URL For the TestCase: " +testcase +" is ");
			test.log(LogStatus.INFO,Url);
			HttpMethod=excel.getHttpMethod();
			Request=excel.getJsonRequest();
			CXGUsername=excel.getCXGUsername();
			CXGPassword=excel.getCXGPassword();
			if(HttpMethod.equalsIgnoreCase("POST")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is ");
				test.log(LogStatus.INFO,Request);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restPostConnection(Url, Request,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				test.log(LogStatus.INFO,"The CGX Response after Executing is ");
				test.log(LogStatus.INFO,Response);
				excel.setvalues(testcase, sheetname, Response);
				test.log(LogStatus.INFO,"Storing the CGX Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("GET")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restGetConnection(Url,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				test.log(LogStatus.INFO,"The Response after Executing is ");
				test.log(LogStatus.INFO,Response);
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				excel.setvalues(testcase, sheetname, Response);
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("PUT")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is ");
				test.log(LogStatus.INFO,Request);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restPutConnection(Url, Request,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				test.log(LogStatus.INFO,"The Response after Executing is ");
				test.log(LogStatus.INFO,Response);
				excel.setvalues(testcase, sheetname, httpsURL.getResponse());
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}

		}
		//test.log(LogStatus.PASS, "Executed Successfully");
	
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getThrowable().toString());
		}
		extent.endTest(test);
	}

	@AfterTest
	public void endreport()
	{
		extent.flush();
		extent.close();
	}



}
