Feature: Name validation against DB

Scenario: First name validation against BD

Given user is logged with valid admin credentials
And user navigate to employee list page
When user enters valid employee id as "14593"
And click on search button
Then verify table is displayed
And get first name from table
When get first name from db
Then validate first name from ui against db

