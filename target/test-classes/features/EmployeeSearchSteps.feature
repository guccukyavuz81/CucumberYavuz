
 Feature: Employee Search
@smoke

Scenario: Search employee by id
Given user is logged with valid admin credentials
And user navigate to employee list page
When user enters valid employee id as "14593"
And click on search button
Then user see employee information is displayed

   #  "12676"  bununla method i parameterize ediyoruz