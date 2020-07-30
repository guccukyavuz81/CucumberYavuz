package com.hrms.API.utils;

import org.json.JSONObject;

import com.hrms.APIStepsPractice.HardcodedExamples;

public class PayloadConstants {

	public static String createEmployeeBody = "{\r\n" + "  \"emp_firstname\": \"Abuzer\",\r\n"
			+ "  \"emp_lastname\": \"Killi\",\r\n" + "  \"emp_middle_name\": \"string\",\r\n"
			+ "  \"emp_gender\": \"M\",\r\n" + "  \"emp_birthday\": \"2000-01-01\",\r\n"
			+ "  \"emp_status\": \"Employee\",\r\n" + "  \"emp_job_title\": \"Cloud Architect\"\r\n" + "}";

	public static String updateBody = "{\r\n" + "            \"employee_id\":\"" + HardcodedExamples.employeeID
			+ "\",\r\n" + "            \"emp_firstname\": \"Abuzerkiewich\",\r\n"
			+ "            \"emp_middle_name\": \"Zerovichkowski\",\r\n"
			+ "            \"emp_lastname\": \"Killikow\",\r\n" + "            \"emp_birthday\": \"1981-01-01\",\r\n"
			+ "            \"emp_gender\": \"M\",\r\n" + "            \"emp_job_title\": \"IT Support Manager\",\r\n"
			+ "            \"emp_status\": \"Employee\"\r\n" + "        }";

	public static String createEmployeePayload() {
		JSONObject obj = new JSONObject();
		obj.put("emp_firstname", "Abuzer");
		obj.put("emp_lastname", "Killi");
		obj.put("emp_middle_name", "string");
		obj.put("emp_gender", "M");
		obj.put("emp_birthday", "2000-01-01");
		obj.put("emp_status", "Employee");
		obj.put("emp_job_title", "Cloud Architect");

		return obj.toString();
	}
}
