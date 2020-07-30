package com.hrms.steps;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsForNameDB extends CommonMethods{

	
	public static List<Map<String,String>> listDB;
	
	@Then("verify table is displayed")
	public void verify_table_is_displayed() {
		boolean isTableDisplayed=viewEmployee.resultTable.isDisplayed();
		Assert.assertTrue(isTableDisplayed);
	}
	
	@Then("get first name from table")
	public void get_first_name_from_table() {
		System.out.println("LIST FROM CUCE  "+viewEmployee.getFirstNameFromTable());
	}

	@When("get first name from db")
	public void get_first_name_from_db() {
		DBUtils.getConnection();
		listDB=DBUtils.storeDataFromDB("select emp_firstname from hs_hr_employees where employee_id= "+GlobalVariables.empID);
		System.out.println("LIST FROM DB "+listDB);
		DBUtils.closeConnection();
	}

	@Then("validate first name from ui against db")
	public void validate_first_name_from_ui_against_db() {
	   Assert.assertEquals(listDB, viewEmployee.getFirstNameFromTable());
	}
}
