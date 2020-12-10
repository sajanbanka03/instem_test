package com.inT.qa.base;
import com.inT.qa.util.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class BaseClass 
{
	public static WebDriver driver;
	public static Properties properties;
	static FileInputStream fileInputStream;
	public static SoftAssert assrt;
	
	public BaseClass(){
		try{
			fileInputStream = new FileInputStream("src\\main\\java\\com\\cw\\qa\\config\\config.properties");
			properties = new Properties();
			properties.load(fileInputStream);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initialize(){
		
		
		String browsername = properties.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\D\\Selenium\\chrome85\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\D\\Selenium\\geckodriver\\geckodriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(properties.getProperty("ndtvURL"));
		//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

}
