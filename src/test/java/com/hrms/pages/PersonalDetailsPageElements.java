package com.hrms.pages;

import java.util.List;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.Base;

public class PersonalDetailsPageElements {

	@FindBy(id = "personal_cmbNation")
	public WebElement nationalityDD;

	@FindBy(name = "personal[optGender]")
	public List<WebElement> genderRadioGroup;

	@FindBy(xpath = "//div[@id='pdMainContainer']/div[1]/h1")
	public WebElement lblPersonalDetails;

	@FindBy(id = "personal_txtEmployeeId")
	public WebElement employeeId;
	
	@FindBy(xpath="//*[@id='profile-pic']/h1")
	public WebElement profilePic;

	public PersonalDetailsPageElements() {
		PageFactory.initElements(Base.driver, this);
	}
}
