package com.projectstimulator.route;

import org.apache.camel.builder.RouteBuilder;

import com.projectstimulator.processor.IterateSheetProcessor;
import com.projectstimulator.processor.TestRunAppProcessor;

public class ProjStimulateRouter extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
	 
		 rest("/")
	 	.consumes("application/json").produces("application/json")
	 	.get("/iterateSheet")
	 	.route()
	 	.process(new IterateSheetProcessor())
	 	.end();
		 
		 rest("/")
		 .consumes("application/json").produces("application/json")
		 .get("/testRun")
		 .route()
		 .process(new TestRunAppProcessor())
		 .end();
	 	
}

}
