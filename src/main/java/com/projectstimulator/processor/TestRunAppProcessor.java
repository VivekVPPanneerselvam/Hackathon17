package com.projectstimulator.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.projectstimulator.model.ResponseDTO;
import com.projectstimulator.service.TestRunAppService;

public class TestRunAppProcessor implements Processor {
	@Override
	public void process(Exchange ex) throws Exception 
	{
		ResponseDTO baseResponse = new ResponseDTO();
		String ServiceName=(String)ex.getIn().getHeaders().get("CamelHttpUri");
		String file = null;
		String testType = null;
		List<String> sheetNames = new ArrayList<String>();
		if(ServiceName.contains("file") && ServiceName.contains("testType") && ServiceName.contains("sheetNames")){
			file = (String) ex.getIn().getHeader("file");
			testType = (String) ex.getIn().getHeader("testType");
			sheetNames = (List<String>) ex.getIn().getHeader("sheetNames");
		}
		TestRunAppService testRunAppService = new TestRunAppService();
		try{
			testRunAppService.runTestApp(file,testType,sheetNames);
			if(sheetNames!=null||sheetNames.size()>0)
			{
				baseResponse.setStatusDesc("Success");
				baseResponse.setStatusCode(1);
				baseResponse.setSheetName(sheetNames);
			}
			else{
				baseResponse.setStatusCode(0);
			}
		}catch(Exception e){
			baseResponse.setStatusCode(-1);
		}
		 ex.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
		 ex.getOut().setBody(baseResponse,ResponseDTO.class);
	}
	}
