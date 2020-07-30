package com.hrms.APIStepsPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hrms.API.utils.PayloadConstants;

//import org.apache.hc.core5.http.ContentType;

//this executes methods in alphabetical order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardcodedExamples {

	/**
	 * Rest Assured
	 * given-prepare your request
	 * when-you are making the call to the endpoint
	 * then validating
	 */
	
	static String baseURI=RestAssured.baseURI="http://18.232.148.34/syntaxapi/api";
	static String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU5NDk0MTcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTk5MjYxNywidXNlcklkIjoiNzExIn0.2fkOtVjpMse052HFQ5fEjhiJ07guzhAc1HtCm03rzzs";
	public static String employeeID;


	//@Test
	public void sampleTestNotes() {
		//base uri for all calls
		RestAssured.baseURI="http://18.232.148.34/syntaxapi/api";
		//JWT required for all calls
		String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU4NjM3MDQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTkwNjkwNCwidXNlcklkIjoiNzExIn0.R45VtvJa52wPUGi-uMo8Oa2jv75mBYCNpQlxya9B5pc";
		//preparing getOneEmployeeRequest
		RequestSpecification getOneEmployeeRequest=given().header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id", "10697A");//.log().all();
		
		Response getOneEmployeeResponse=getOneEmployeeRequest.when().log().all().get("/getOneEmployee.php");
		
		getOneEmployeeResponse.prettyPrint();
		//the other way is    String response =getOneEmployeeResponse.body().asString(); then syso but above does printing 
		
		//verifying response status code is 200
		getOneEmployeeResponse.then().assertThat().statusCode(200);
	}
	
	@Test
	public void aPOSTCreateEmployee() {     //lowercase c was needed to run this first and the other second but now @FixMethodOrder(MethodSorters.NAME_ASCENDING) makes it run in alphabetical  order
	RequestSpecification createEmployeeRequest = given().header("Content-Type","application/json").header("Authorization",token).body("{\r\n" + 
			"  \"emp_firstname\": \"Abuzer\",\r\n" + 
			"  \"emp_lastname\": \"Killi\",\r\n" + 
			"  \"emp_middle_name\": \"string\",\r\n" + 
			"  \"emp_gender\": \"M\",\r\n" + 
			"  \"emp_birthday\": \"2000-01-01\",\r\n" + 
			"  \"emp_status\": \"Employee\",\r\n" + 
			"  \"emp_job_title\": \"Cloud Architect\"\r\n" + 
			"}").log().all();
	
	Response createEmployeeResponse=createEmployeeRequest.when().post("/createEmployee.php");
	
	createEmployeeResponse.prettyPrint();
	
	createEmployeeResponse.then().assertThat().statusCode(201);
	
	//jsonPath() to view response body which lets us get the employee ID
	
	// we created a global variable above so that we can use empID in the other calls
	employeeID=createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
	System.out.println("here is the employeeID "+employeeID);
	
	//verifying message using equalTo
	createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
	
	createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Abuzer"));
	
	createEmployeeResponse.then().assertThat().header("Server",  equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));
	
	createEmployeeResponse.then().assertThat().header("Access-Control-Allow-Methods",  equalTo("POST"));
	}
	
	@Test
	public void bGETcreatedEmployee() {
		RequestSpecification getCreatedEmployeeRequest=given().header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id", employeeID).log().all();
		
		Response getCreatedEmployeeResponse=getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
		
		String response = getCreatedEmployeeResponse.prettyPrint();
		
		String empID=getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		
		boolean verifyingEmployeeIDsMatch=empID.equalsIgnoreCase(employeeID);
		
		Assert.assertTrue(verifyingEmployeeIDsMatch);
		
		System.out.println("do the ids match? "+verifyingEmployeeIDsMatch);
		
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);
		
		JsonPath js=new JsonPath(response);
		
		String emplID = js.getString("employee[0].employee_id");
		String firstName = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String birthday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String empStatus = js.getString("employee[0].emp_status");
		
		System.out.println(emplID);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(jobTitle);
		
		Assert.assertTrue(emplID.contentEquals(employeeID));
		Assert.assertEquals(employeeID, emplID);
		Assert.assertEquals("Abuzer", firstName);
		Assert.assertEquals("Cloud Architect", jobTitle);
		Assert.assertTrue(gender.contentEquals("Male"));
	
	}
	@Test
	public void cGETallEmployees() {
		
		RequestSpecification getAllEmployeesRequest=given().header("Content-Type","application/json").header("Authorization",token);
		
		Response getAllEmployeesResponse=getAllEmployeesRequest.when().get("/getAllEmployees.php");
		
		//getAllEmployeesResponse.prettyPrint(); too long we dont need this
		
		String allEmployees = getAllEmployeesResponse.body().asString();
		
		Assert.assertTrue(allEmployees.contains(employeeID));
		
		JsonPath js=new JsonPath(allEmployees);
		
		int sizeOfList=js.getInt("Employees.size()");
		
		System.out.println("number of employees: "+sizeOfList);
		
//		for(int i=0;i<=sizeOfList;i++) {
//			String allEmployeeIDs=js.getString(("Employees["+i+"].employee_id"));
//			//System.out.println(allEmployeeIDs); no need too long
//		
//			if(allEmployeeIDs.contains(employeeID)) { //content equals da ayni calisiyor ikisi de cok yavas
//				
//				System.out.println("Employee ID: "+employeeID+" is present in body");
//				String employeeFirstName = js.getString("Employees["+i+"].emp_firstname");
//				System.out.println(employeeFirstName);
//				
//				break;
//			}
//		}
	}
	@Test
	public void dPUTupdateCreatedEmployee() {
		
	RequestSpecification updateCreatedEmployeeRequest =	given().header("Content-Type","application/json").header("Authorization",token).body(PayloadConstants.updateBody);
		
	Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");
	
	//String response = updateCreatedEmployeeResponse.prettyPrint();
	
	updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
	
	String updatedEmployee=updateCreatedEmployeeResponse.body().asString();
	
	Assert.assertTrue(updatedEmployee.contains("Entry updated"));
	
	System.out.println("does it contain Entry updated ? "+updatedEmployee.contains("Entry updated"));
	
	String empID=updateCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
	
	System.out.println("employee ID is: "+empID);
	
	Assert.assertTrue(empID.contentEquals(employeeID));
	
	} 
	@Test
	public void eGETupdatedEmployee() {
		
		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id", employeeID);
		
		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");
		
		getUpdatedEmployeeResponse.prettyPrint();
		
		String updatedFirstName = getUpdatedEmployeeResponse.body().jsonPath().getString("employee[0].emp_firstname");
		
		System.out.println(updatedFirstName);
		
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("Abuzerkiewich"));
		
		String updatedEmployeeData=getUpdatedEmployeeResponse.asString();
		
		Assert.assertTrue(updatedEmployeeData.contains("Zerovichkowski"));
	}
	@Test
	public void fPATCHpartiallyUpdateEmployee() {
		
		RequestSpecification partiallyUpdateEmployeeRequest = given().header("Content-Type","application/json").header("Authorization",token).body("{\r\n" + 
				"            \"employee_id\": \""+employeeID+"\",\r\n" + 
				"            \"emp_firstname\": \"Abuzer\",\r\n" + 
				"            \"emp_middle_name\": \"Zerow\",\r\n" + 
				"            \"emp_lastname\": \"Kil\"\r\n" + 
				"        }");
		Response partiallyUpdateEmployeeResponse = partiallyUpdateEmployeeRequest.when().patch("/updatePartialEmplyeesDetails.php");
		
		partiallyUpdateEmployeeResponse.prettyPrint();
		
		partiallyUpdateEmployeeResponse.then().assertThat().body("employee[0].emp_lastname",equalTo("Kil"));
		
		partiallyUpdateEmployeeResponse.then().assertThat().statusCode(201);
		
		partiallyUpdateEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
		
		String patchedEmployeeData=partiallyUpdateEmployeeResponse.body().asString();
		
		Assert.assertTrue(patchedEmployeeData.contains("Zerow"));
		
	}
	@Test
	public void gDELETEemployee() {
		
	RequestSpecification deleteEmployeeRequest = given().header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id", employeeID);
	
	Response deleteEmployeeResponse = deleteEmployeeRequest.when().delete("/deleteEmployee.php");
	
	deleteEmployeeResponse.prettyPrint();
	
	deleteEmployeeResponse.then().body("message", equalTo("Entry deleted"));
	}
}
