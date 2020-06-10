$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/AddEmpExcel.feature");
formatter.feature({
  "name": "Add employee using excel",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add employee using excel values",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Bu"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user logins with valid admin credentials",
  "keyword": "Given "
});
formatter.match({
  "location": "com.hrms.steps.AddEmployee.user_logins_with_valid_admin_credentials()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user adds data from excel file then all employees are added",
  "keyword": "When "
});
formatter.match({
  "location": "com.hrms.steps.AddEmployee.user_adds_data_from_excel_file_then_all_employees_are_added()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});