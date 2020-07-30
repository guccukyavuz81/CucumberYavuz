package com.hrms.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.Base;

public class ViewEmloyeePage {

	@FindBy (id="empsearch_employee_name_empName")
	public WebElement EmpName;
	
	@FindBy (id="empsearch_id")
	public WebElement EmpID;
	
	@FindBy(id = "searchBtn")
	public WebElement searchBtn;
	
	@FindBy(xpath="//*[@id='resultTable']")
	public WebElement resultTable;
	
	
	@FindBy(xpath="//*[@id='resultTable']/tbody/tr/td[3]/a")
	public List<WebElement> tableFirstname; 
	
	
	public List<Map<String,String>> getFirstNameFromTable(){
		List<Map<String,String>> nameUI=new ArrayList<>();
		for(WebElement row:tableFirstname) {
			Map<String,String> storeUINmaes=new LinkedHashMap<String, String>();
			String tableName=row.getText();
			storeUINmaes.put("emp_firstname", tableName);
			nameUI.add(storeUINmaes);
		}
		return nameUI;
	}
	
	public ViewEmloyeePage() {
		PageFactory.initElements(Base.driver, this);
	}
}
