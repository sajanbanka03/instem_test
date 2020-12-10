package com.inT.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inT.qa.base.BaseClass;
import com.inT.qa.pages.loginPage;

import junit.framework.Assert;

public class loginTestCase extends BaseClass
{
	loginPage login;
	public loginTestCase(){
		super(); //We are calling super class constructor i.e. we are calling BaseClass constructor because we have to initialize properties
		assrt = new SoftAssert();
	}
	
	@BeforeMethod
	public void setup(){
		initialize();// since we have called the constructor above, we will not get null pointer exception for objects used in this method
		login = new loginPage();
	}
	@Test(priority = 0)
	public void verifyloginPagetitle()
	{
		assrt.assertEquals(properties.getProperty("loginpageURL"), login.pageTitle());
	}
	
	@Test(priority = 1)
	public void compareloginTitle(){
		String title = login.loginTitle();
		assrt.assertEquals("Login to ALPHADAS", title);
	}
	
	/* In testcase-2 I am comparing if username & password fields are enabled
	 * If invalid creds are provide, error message of invalid credentials should be displayed. verifying the same.
	 * 
	 */
	@Test(priority = 2)
	public void verifyLogin(String uname, String pwd){ 
		login.login(uname, pwd);
	}
	
	@Test(priority = 3,dependsOnMethods = "verifyLogin")
	public void verifyLandingPage()
	{
		String lndngtitle = properties.getProperty("loginLandingpageTitle"); //After login, the expected landingpage title is stored in an object to compare
		assrt.assertEquals(lndngtitle, driver.getTitle());
	}
	
}
