package com.hrms.practice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Recap {

	String dbUsername="syntax_hrm";
	String dbPassword="syntaxhrm123";
	//jdbc: driver type:hostname:port/db name
	String dbUrl = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql";
	
	@Test
	public void getConnected()throws SQLException{
		Connection conn= DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		DatabaseMetaData dbMetadata=conn.getMetaData();
		System.out.println(dbMetadata.getDatabaseProductName());
		System.out.println(dbMetadata.getDatabaseProductVersion());
		System.out.println(dbMetadata.getDriverName()); 
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from ohrm_skill");
		ResultSetMetaData rsMetaData=rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();
		for(int i=1; i<=colCount; i++) {
			System.out.println(rsMetaData.getColumnName(i));
		}

		
		List<String> list=new ArrayList<>();
		while(rs.next()) {
		list.add(rs.getObject("name").toString());	
		}
		System.out.println(list);
		
		ResultSet rst=st.executeQuery("select * from ohrm_skill");
		ResultSetMetaData rstMetaData=rst.getMetaData();
		while(rst.next()) {
			String id=rst.getObject("id").toString();
			String skill=rst.getObject("name").toString();
			System.out.println(id+": "+skill);
		}
		
		
		System.out.println("----------------------------------------------------------------------------------------");
		
		ResultSet rss=st.executeQuery("select * from ohrm_skill");
		ResultSetMetaData rssMetaData=rss.getMetaData();
		List<Map<String,String>> li=new ArrayList<>();
		Map<String,String> map;
	
		while(rss.next()) {
			map=new LinkedHashMap<String, String>();
			for(int i=1; i<=colCount; i++) {
				String key=rssMetaData.getColumnName(i);
				String value=rss.getObject(i).toString();
				map.put(key, value);
			}
			li.add(map);
		}
		System.out.println(li);
		
		rs.close();
		rss.close();
		rst.close();
		st.close();
		conn.close();   

	}
}
