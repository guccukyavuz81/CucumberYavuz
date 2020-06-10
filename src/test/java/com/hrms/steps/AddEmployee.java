package com.hrms.steps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddEmployee extends CommonMethods{

	@Given("user logins with valid admin credentials")
	public void user_logins_with_valid_admin_credentials() throws InterruptedException {
		loginpage.username.sendKeys(ConfigReader.getProp("username"));	
		loginpage.password.sendKeys(ConfigReader.getProp("password"));
		loginpage.btnLogin.click();
        Thread.sleep(2000);	
	}

	
	
	@Given("user navigates to AddEmployeePage")
	public void user_navigates_to_AddEmployeePage() throws InterruptedException {
		
		dash.pim.click();
		Thread.sleep(1000);
		dash.addEmployee.click();
		Thread.sleep(1000);
	}
	@When("user enters employees first name and last name")
	public void user_enters_employees_first_name_and_last_name() {
		sendText(addemployee.firstName, "Abuziddin");
		sendText(addemployee.lastName, "Kilkuyruk");
	}
	@When("user clicks save button")
	public void user_clicks_save_button() throws InterruptedException {
		addemployee.save.click();
		Thread.sleep(3000);
	}
	@Then("employee is added successfully")
	public void employee_is_added_successfully() throws InterruptedException {
		Thread.sleep(3000);
		String actual = personalDetails.profilePic.getText();
		String expected = "Abuziddin Kilkuyruk";
		Assert.assertEquals(expected, actual);
		//takesScreenshot("AddEmployee");   bunu bul
	}
	@When("user deletes employee id")
	public void user_deletes_employee_id() {
		addemployee.employeeId.clear();
	}
	@When("user clicks on create login checkbox")
	public void user_clicks_on_create_login_checkbox() {
		addemployee.checklogin.click();
	}
	@When("user enters login credentials")
	public void user_enters_login_credentials() {
		sendText(addemployee.userName, ConfigReader.getProp("empUserName"));
		sendText(addemployee.password, ConfigReader.getProp("empPassword"));
		sendText(addemployee.confirmpassword, ConfigReader.getProp("empPassword"));
		
	}
	
	@When("user enters employees {string} and {string}")
	public void user_enters_employees_and(String name, String lastname) throws InterruptedException {
		
		
		addemployee.firstName.sendKeys(name); 
		addemployee.lastName.sendKeys(lastname);
	}

	@When("user click on save button")
	public void user_click_on_save_button() {
		addemployee.save.click();  
	}

	@Then("{string}  {string} is added successfully")
	public void and_is_added_successfully(String name, String lastname) {
		String actual=personalDetails.profilePic.getText();
		String expected = name+" "+lastname;
		Assert.assertEquals("Test Case is Failed", expected, actual);
	}
	
	@When("user enters firstname and lastname and clicks on save button then employee is added")
	public void user_enters_firstname_and_lastname_and_clicks_on_save_button_then_employee_is_added(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		List<Map<String,String>> addEmployeeList=dataTable.asMaps();  
		
		for (Map<String,String> map: addEmployeeList) {
			String fname=map.get("FirstName");
			String lname=map.get("LastName");
			
			sendText(addemployee.firstName, map.get("FirstName"));
			sendText(addemployee.lastName, map.get("LastName"));
			addemployee.save.click();
			String actual=personalDetails.profilePic.getText();
			String expected=fname+" "+lname;
			Assert.assertEquals("Employee is not addedd successfully", expected, actual);
			dash.pim.click();
			Thread.sleep(1000);
			dash.addEmployee.click();
			Thread.sleep(1000);
			
		}
		
	}
	@When("user adds data from excel file then all employees are added")
	public void user_adds_data_from_excel_file_then_all_employees_are_added() throws IOException, InterruptedException {
		List<Map<String,String>> lmap=excelToList();  
		
		for(Map<String,String> map:lmap) {
			dash.pim.click();
			Thread.sleep(1000);
			
			dash.addEmployee.click();
			Thread.sleep(1000);

			addemployee.checklogin.click();
			addemployee.firstName.sendKeys(map.get("Name"));
			addemployee.lastName.sendKeys(map.get("LastName"));
			
			String expectedEmpId=addemployee.employeeId.getAttribute("value");
			
			addemployee.userName.sendKeys(map.get("Username"));
			addemployee.password.sendKeys(map.get("Password"));
			addemployee.confirmpassword.sendKeys(map.get("Password"));
			addemployee.save.click();
			Thread.sleep(10000);
			
			String actualEmpId = personalDetails.employeeId.getAttribute("value");
			Assert.assertEquals("ID mismatch", expectedEmpId, actualEmpId);
			dash.pim.click();
			Thread.sleep(1000);
			dash.addEmployee.click();
			Thread.sleep(1000);
		}
		
	}
	public static List<Map<String,String>> excelToList() throws IOException{
		
		List<Map<String,String>> listmap=new ArrayList<Map<String,String>>();
		
		
		String filepath="C:\\Users\\Carbon\\eclipse-workspace\\CuceHrmsYavuz\\src\\test\\resources\\testData\\addEmp.xlsx";
		FileInputStream fis=new FileInputStream(filepath);	
		Workbook book=new XSSFWorkbook(fis);
		Sheet sheet=book.getSheet("Sheet1");
		int rowcount=sheet.getPhysicalNumberOfRows();
		int colcount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int x=1; x<rowcount;x++) {
			Map<String,String> map=new LinkedHashMap<String, String>();
			for(int y=0; y<colcount;y++) {
			map.put(sheet.getRow(0).getCell(y).toString(), sheet.getRow(x).getCell(y).toString());		
			}
			listmap.add(map);
		}
		System.out.println(listmap);

		return listmap;
	}
}
