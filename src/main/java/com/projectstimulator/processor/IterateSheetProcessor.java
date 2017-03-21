package com.projectstimulator.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.projectstimulator.service.IterateSheetNamesService;

import com.projectstimulator.model.ResponseDTO;


public class IterateSheetProcessor implements Processor {
	@Override
	public void process(Exchange ex)
	{
		ResponseDTO baseResponse = new ResponseDTO();
		String ServiceName=(String)ex.getIn().getHeaders().get("CamelHttpUri");
		String file = null;
		if(ServiceName.contains("file")){
			file = (String) ex.getIn().getHeader("file");
		}
		IterateSheetNamesService iterateSheetNamesService = new IterateSheetNamesService();
		try{
			List<String> sheetNames= iterateSheetNamesService.getSheetNames(file);
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
