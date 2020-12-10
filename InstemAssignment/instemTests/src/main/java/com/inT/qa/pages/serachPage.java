package com.inT.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.inT.qa.base.BaseClass;
import com.inT.qa.util.*;

public class serachPage extends BaseClass
{

	//ObjectRepository
	@FindBy(id = "subjectStatus")
	WebElement subjectStatus;
	
	@FindBy(id = "firstname")
	WebElement firstname;
	
	@FindBy(id = "lastname")
	WebElement lastname;
	
	@FindBy(id = "chkcurrent")
	WebElement chkbxCurrent;
	
	@FindBy(id = "Recorderror") //Creating this object for an error incase wrong details are entered.
	WebElement recorderror;
	
	@FindBy(id = "datepicker")
	WebElement calender;
	
	@FindBy(xpath = "xpath for year next button")
	WebElement yearNextButton;
	
	@FindBy(xpath = "xpath for year previous button")
	WebElement yearPrevButton;
	
	@FindBy(xpath = "xpath for year mid button")
	WebElement yearMidButton;
	
	@FindBy(id = "searchBtn")
	WebElement searchBtn;
	
	@FindBy(xpath = "//div[@class='volunteerTable']")
	WebElement resultTable;
	
	
//	@FindBy(xpath = "//div[@class = 'leaflet-popup-content']")
//	WebElement weatherDetails;
	
	public serachPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String searchPagetitle()
	{
		return driver.getTitle();
	}
	
	public void searchVolunteer(String fname, String lname, String status, String date, String selectchkbox) throws Exception
	{
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		TestUtil.selectvaluefromdropDown(subjectStatus, status);
		TestUtil.datePicker(calender, yearNextButton, yearPrevButton, yearMidButton, date);
		if(chkbxCurrent.isSelected())
		{
			Reporter.log("checkbox is already selected");
		}
		else if(selectchkbox.equalsIgnoreCase("True"))
		{
			chkbxCurrent.click();
			Reporter.log("Checkbox is Selected");
		}
		else{
			Reporter.log("checkbox selection is not required");
		}
		searchBtn.click();
		
		if(recorderror.isDisplayed())
		{
			Reporter.log("Incorrect serach Criteria. verify the details: "+recorderror.getText());
		}
		else{
			Reporter.log("volunteer searched successfully");
		}
		
	}
	
	public void verifySearchTable(String fname)
	{
		int count =1;
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(resultTable));
		if(resultTable.isDisplayed())
		{
			Reporter.log("Volunteer table is displayed based on serach result");
			List<WebElement> rows = resultTable.findElements(By.xpath("//*[@id='']/table/tbody/tr"));
			for(int i =1; i<rows.size();i++) // starting the counter from second row as I am expecting 1st row will contain headers
			{
			    List<WebElement> cells = resultTable.findElements(By.xpath("//*[@id='']/table/tbody/tr[i]/td"));
			    for(WebElement cell : cells){
			    	String cellValue = cell.getText();
				    if(cellValue.equals(fname))
				    {
				    	count = 1;
				    	Reporter.log("volunteer found");
				    }
				    else
				    	count = 0;
			    }
			    assrt.assertEquals(1, count);
			    
			}
			
		}
	}
}
