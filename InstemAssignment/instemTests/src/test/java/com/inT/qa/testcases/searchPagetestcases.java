package com.inT.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.inT.qa.base.BaseClass;
import com.inT.qa.pages.loginPage;
import com.inT.qa.pages.serachPage;

public class searchPagetestcases extends BaseClass
{
	serachPage searchpg;
	@BeforeMethod
	public void setup(){
		initialize();// since we have called the constructor above, we will not get null pointer exception for objects used in this method
		searchpg = new serachPage();
	}

	@Test(priority = 1)
	public void verifySerachPageTitle()
	{
		String searchtitle = properties.getProperty("searchpageTitle");
		assrt.assertEquals(searchtitle, searchpg.searchPagetitle());
	}
	
	@Test(priority = 2)
	public void searchVolunteer(String firstname, String lastname, String status, String date, String currentlyInUse) throws Exception
	{
		searchpg.searchVolunteer(firstname, lastname, status, date, currentlyInUse);
	}
	
	//Testcase-3 will verify the table generated and will search for the firstname of the volunteer we used to search the list.
	@Test(priority =3, dependsOnMethods = "searchVolunteer")
	public void verifyResultTable(String fname)
	{
		searchpg.verifySearchTable(fname);
	}
}
