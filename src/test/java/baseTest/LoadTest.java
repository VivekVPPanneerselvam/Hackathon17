package baseTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class LoadTest {
	String sheetname="Load_BidMaster";

	Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	ExtentReports extent;
	ExtentTest test;
	String Response,Expected,Url,HttpMethod,Request,CXGUsername,CXGPassword;

	static ExcelAnalyzerBL excel =new ExcelAnalyzerBL();
	@BeforeTest
	public void startReport()
	{
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/LoadTestResults.html", true);
		extent.addSystemInfo("Environment", "Test");
	}

	@Test(threadPoolSize =200, invocationCount =300, alwaysRun=true)

	public  void ServiceLoadTesting() throws IOException, InterruptedException {
		HttpServiceConBL httpURL = new HttpServiceConBL();
		HttpsServiceConBL httpsURL = new HttpsServiceConBL();
		long threadId = Thread.currentThread().getId();
		map=excel.getScenarionames(sheetname);
		for (Map.Entry entry:map.entrySet()){
			String Sheet=entry.getKey().toString();
			ArrayList<String> value = (ArrayList<String>) entry.getValue();
			test=extent.startTest(" ServiceLoadTesting");
			int si =value.size();
			System.out.println("threadId "+threadId);
			for(int j=0;j<si;j++){
				Reporter.log(Sheet+"_"+value.get(j).toString());
				Reporter.log("Getting the values from the sheet ");	
				test.log(LogStatus.INFO,"Getting the values from the sheet ");
				excel.getvalues(value.get(j),Sheet);
				Url=excel.getUrl();
				Reporter.log("The Request URL For the TestCase: " +value.get(j) +" is "+ "\n" +Url);  
				test.log(LogStatus.INFO,"The Request URL For the TestCase: "+threadId +value.get(j) +" is "+ "\n" +Url);
				HttpMethod=excel.getHttpMethod();
				Request=excel.getJsonRequest();
				CXGUsername=excel.getCXGUsername();
				CXGPassword=excel.getCXGPassword();
				Thread.sleep(1000);
				httpURL.restGetConnection(Url);
				System.out.println(System.currentTimeMillis() + "Testing thread Get Url  "+threadId);
				Response =httpURL.getResponse();
				Reporter.log("The Response after Executing is" +Response);
				test.log(LogStatus.INFO,"The Response after Executing is "+threadId +Response);
				excel.setvalues(value.get(j), Sheet, Response);
				Expected= excel.getJsonExpectedResponse(value.get(j), Sheet);
				test.log(LogStatus.PASS, "Executed Successfully");
			}
		}


	}

	@AfterTest
	public void endreport()
	{
		extent.flush();
		extent.close();
	}
}
