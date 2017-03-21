package com.projectstimulator.BL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.projectstimulator.model.CommonDTO;


public class SoapConBL {
	static FileInputStream fin;
	String[] array ;
	String input;
	List<String> zoom = new ArrayList<>();
	String encoding ;
	@Test 
	public  void main() throws Exception {
		URL url = null;
		String SOAPUrl = "http://tmswstest2.dev.qintra.com/TicketManager.asmx?wsdl";
		url = new URL(SOAPUrl);
		URLConnection urlConnection = null;
		URLConnection connection = url.openConnection();
		CommonDTO commonDTO = new CommonDTO();
		urlConnection = url.openConnection();
		if(urlConnection.getContent() != null) {
			System.out.println("GOOD URL");
			ExcelAnalyzerBL excel =new ExcelAnalyzerBL();
			excel.getDBConnection("xml");
			String str =commonDTO.getDbUrl();
			byte[] b;
			b=str.getBytes();
			connection.setRequestProperty("Content-Length", String.valueOf(b.length));
			connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			((HttpURLConnection) connection).setRequestMethod("POST");
			connection.setDoOutput(true);
			// send the XML that was read in to b.
			OutputStream out = connection.getOutputStream();
			out.write(b);
			out.close();
			// Read the response.
			connection.connect();
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			int i=0;
			String inputLine;
			int count  = in.read();
			array = new String[count];
			while((inputLine = in.readLine()) != null){
				input=inputLine;
				System.out.println(inputLine);
			}
			excel.setvalues("xml", "", "<"+input);
			in.close();
		}

	}
}
