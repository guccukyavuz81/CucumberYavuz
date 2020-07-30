package com.hrms.practice;

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

import org.junit.Test;

public class TaskNationality {

	String dbUsername="syntax_hrm";
	String dbPassword="syntaxhrm123";
	//jdbc: driver type:hostname:port/db name
	String dbUrl = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql";
	
	@Test
	public void taskforce() throws SQLException {
		Connection conn=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select*from ohrm_nationality");
		ResultSetMetaData rsMetaData=rs.getMetaData(); 
		int colCount=rsMetaData.getColumnCount();
		
		List<Map<String,String>> list=new ArrayList<>();
		Map<String,String> map;
		
		while(rs.next()) {
			map=new LinkedHashMap<String, String>();
			for(int i=1; i<=colCount; i++) {
				map.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
			}
			list.add(map);
		}
		System.out.println(list);
		
		ResultSet rss=st.executeQuery("select*from ohrm_nationality");
		ResultSetMetaData rssMetaData=rss.getMetaData();
		
		List<Map<String,String>> li=new ArrayList<>();
		Map<String,String> lmap;
		while(rss.next()) {
			lmap=new LinkedHashMap<String, String>();
			lmap.put("nationality id", rss.getObject("id").toString());
			lmap.put("nationality", rss.getObject("name").toString());
			li.add(lmap);
		}
		System.out.println(li);
		rss.close();
		rs.close();
		st.close();
		conn.close();
	}
}
