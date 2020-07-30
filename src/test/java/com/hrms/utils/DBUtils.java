package com.hrms.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static List<Map<String,String>> listData;

	public static void getConnection() {
		ConfigReader.confread(Paths.filePath);

		try {
			conn=DriverManager.getConnection(ConfigReader.getProp("dbUrl"), ConfigReader.getProp("dbUsername"), ConfigReader.getProp("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<String,String>> storeDataFromDB(String sqlQuery){
	try {
		st=conn.createStatement(); 
		rs=st.executeQuery(sqlQuery);
		ResultSetMetaData rsMetaData=rs.getMetaData();
		listData=new ArrayList<Map<String,String>>();
		
		while(rs.next()) {
			Map<String,String>	mapData=new LinkedHashMap<String, String>();
			for(int i=1; i<=rsMetaData.getColumnCount();i++) {
				mapData.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
			}
			listData.add(mapData);
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	return listData;
	}
	
	
	public static void closeConnection() {
		try {
			if(rs!=null) {
				rs.close();	
			}
			if(st!=null) {
				st.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
