package com.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dao.UserLogin;

public class Base_Class {

	
	
	
	public WebDriver driver;
	public ChromeOptions options;
	public WebDriverWait wait;
//	/UbuntuSmokeAutomation/src/main/resources/Browser/chromedriver.exe
	public void BrowserSetUp() throws IOException
	{
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		System.out.println(currentTimestamp);
//		
		String os=System.getProperty("os.name").toLowerCase();
		System.out.println("OS name: "+os);
		String driver_type = os.contains("windows") ? "/chromedriver.exe" :"/var/lib/jenkins/driver/chromedriver";
		final URL resource = Base_Class.class.getResource(driver_type);
		if(os.contains("windows"))
				{
			System.setProperty("webdriver.chrome.driver", resource.getFile());
				}
		else
			System.setProperty("webdriver.chrome.driver", driver_type);
		
	   
		options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1920,1080");
		options.addArguments("remote-debugging-port=9222");
		options.addArguments("incognito");
		options.addArguments("disable-infobars");
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.manage().deleteAllCookies();
		driver.get("https://studio.apigenie.io/");
		String u_name =Utility_Class.GetExcelData(0, 0, 1) ;
		String u_pass = Utility_Class.GetExcelData(0, 1, 1);
		UserLogin user = new UserLogin(driver);
		user.login(u_name,u_pass);
		
	}
	
	
}
