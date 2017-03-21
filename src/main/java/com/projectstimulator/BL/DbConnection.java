package com.projectstimulator.BL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
	static Connection connection = null;
	Statement stmt;
	ResultSet  rset;
	List<String> columnHeaders;
	List<List<String>> tableData;
	static int id;
	public static int autokey = -1;
	static String[] contactList;
	static ArrayList<String> senders = new ArrayList<String>();
	List<String> values  = new ArrayList<String>();

	public void getConnection(String Url, String Username , String Password){
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			connection= DriverManager.getConnection(Url, Username , Password);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void query( String  query ,String Url, String Username , String Password ){
		getConnection(Url,Username,Password);
		try {
			stmt = connection.createStatement();
			rset = stmt.executeQuery(query); 
			ResultSetMetaData md = rset.getMetaData();
			int count = md.getColumnCount();
			columnHeaders = new ArrayList<String>();
			tableData = new ArrayList<List<String>>();
			for (int i = 1; i <= count; i++) {
				columnHeaders.add(md.getColumnName(i));
			}
			while (rset.next()) {
				List<String> rowData = new ArrayList<String>();
				for (int i = 1; i <= count; i++) {
					rowData.add(rset.getObject(i).toString());
				}
				tableData.add(rowData);
			}
			System.out.println("Column is  "+columnHeaders);
			System.out.println("first  is  "+tableData.get(0));
			values= tableData.get(0);
			System.out.println("Hi value is" + values);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
