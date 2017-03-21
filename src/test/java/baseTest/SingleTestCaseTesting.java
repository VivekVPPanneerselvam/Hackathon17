package baseTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.projectstimulator.BL.ExcelAnalyzerBL;
import com.projectstimulator.BL.HttpServiceConBL;
import com.projectstimulator.BL.HttpsServiceConBL;
import com.projectstimulator.util.JsonValidation;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class SingleTestCaseTesting {

	String sheetname ="Fetch_Repair_History";
	String testcase ="Verifying_fetch repair_telephone number_sample1_TC01";
	HttpServiceConBL httpURL = new HttpServiceConBL();
	HttpsServiceConBL httpsURL = new HttpsServiceConBL();
	static ExcelAnalyzerBL excel =new ExcelAnalyzerBL();
	JsonValidation json= new JsonValidation();
	String Url, HttpMethod,Request, Response, Expected, Actual, CXGUsername, CXGPassword;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void startReport()
	{
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/TestNG_SingleTCResult.html", true);
		extent.addSystemInfo("Environment", "QA");
	}

	@Test
	public  void TestCaseTesting () throws IOException {

		test=extent.startTest("TestCaseTesting for "+sheetname+"_"+testcase);
		if(!(testcase.startsWith("CXG"))){
			Reporter.log("Getting the values from the sheet ");	
			test.log(LogStatus.INFO,"Getting the values from the sheet ");
			excel.getvalues(testcase,sheetname);
			Url=excel.getUrl();
			Reporter.log("The Request URL For the TestCase: " +testcase +" is "+ "\n" +Url);  
			test.log(LogStatus.INFO,"The Request URL For the TestCase: " +testcase +" is "+ "\n" +Url);
			HttpMethod=excel.getHttpMethod();
			Request=excel.getJsonRequest();
			if(HttpMethod.equalsIgnoreCase("POST")){
				Reporter.log("The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				Reporter.log("The Request for the TestCase:  " +testcase +" is " +Request);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is " +Request);
				Reporter.log("Executing the Service by "+HttpMethod+ " Method");
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpURL.restPostConnection(Url, Request);
				Response =httpURL.getResponse();
				Reporter.log("The Response after Executing is "+Response);
				test.log(LogStatus.INFO,"The Response after Executing is "+Response);
				excel.setvalues(testcase, sheetname, httpURL.getResponse());
				Reporter.log("Storing the Response in the Sheet");
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
				Reporter.log("Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("GET")){
				Reporter.log("The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				Reporter.log("Executing the Service by "+HttpMethod+ " Method");
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpURL.restGetConnection(Url);
				Response =httpURL.getResponse();
				Reporter.log("The Response after Executing is "+Response);
				test.log(LogStatus.INFO,"The Response after Executing is "+Response);
				Reporter.log("Storing the Response in the Sheet");
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				excel.setvalues(testcase, sheetname, Response);
				System.out.println("The Response is "+Response);
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				Reporter.log("Verifying the response");
				test.log(LogStatus.INFO,"Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("PUT")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is " +Request);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpURL.restPutConnection(Url, Request);
				Response =httpURL.getResponse();
				test.log(LogStatus.INFO,"The Response after Executing is "+Response);
				excel.setvalues(testcase, sheetname, httpURL.getResponse());
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
		}else {
			test.log(LogStatus.INFO,"Getting the values from the sheet ");
			excel.getvalues(testcase,sheetname);
			Url=excel.getUrl();
			test.log(LogStatus.INFO,"The CGX Request URL For the TestCase: " +testcase +" is "+ "\n" +Url);
			HttpMethod=excel.getHttpMethod();
			Request=excel.getJsonRequest();
			CXGUsername=excel.getCXGUsername();
			CXGPassword=excel.getCXGPassword();
			if(HttpMethod.equalsIgnoreCase("POST")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is " +Request);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restPostConnection(Url, Request,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				test.log(LogStatus.INFO,"The CGX Response after Executing is "+Response);
				excel.setvalues(testcase, sheetname, httpURL.getResponse());
				test.log(LogStatus.INFO,"Storing the CGX Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("GET")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restGetConnection(Url,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				System.out.println(Response);
				test.log(LogStatus.INFO,"The Response after Executing is "+Response);
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				excel.setvalues(testcase, sheetname, Response);
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
			else if(HttpMethod.equalsIgnoreCase("PUT")){
				test.log(LogStatus.INFO,"The Method for the TestCase: " +testcase +" is " +HttpMethod);
				test.log(LogStatus.INFO,"The Request for the TestCase:  " +testcase +" is " +Request);
				test.log(LogStatus.INFO,"Executing the Service by "+HttpMethod+ " Method");
				httpsURL.restPutConnection(Url, Request,CXGUsername,CXGPassword);
				Response =httpsURL.getResponse();
				test.log(LogStatus.INFO,"The Response after Executing is "+Response);
				excel.setvalues(testcase, sheetname, httpsURL.getResponse());
				test.log(LogStatus.INFO,"Storing the Response in the Sheet");
				Expected= excel.getJsonExpectedResponse(testcase, sheetname);
				test.log(LogStatus.INFO,"Verifying the response");
			}
		}

	}
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
