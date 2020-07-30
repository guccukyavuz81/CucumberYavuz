package com.hrms.practice;

import org.junit.Test;

import com.hrms.utils.DBUtils;

public class TestDBUtil {

	@Test
	public void test() {
		DBUtils.getConnection();
		System.out.println(DBUtils.storeDataFromDB("select emp_firstname from hs_hr_employees where employee_id=14688"));
		DBUtils.closeConnection();
	}
}
