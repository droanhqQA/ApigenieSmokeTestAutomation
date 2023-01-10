package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.connectors.AddingConnector;
import com.dao.UserDAO;
import com.dao.UserLogin;
import com.utils.Base_Class;
import com.utils.TakeScreenshots;

public class HeadFullTestCases extends Base_Class {
	AddingConnector connector;
	
	FileInputStream fs ;
	
	String testName="";
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		testName = testContext.getName();
	    System.out.println(testContext.getName()); 
	}
	@BeforeMethod
	public void setUp() throws IOException
	{
		BrowserSetUp();
					connector = new AddingConnector(driver);
				
				connector.navigatetoConnector();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
	}
	
		@Test(retryAnalyzer = RetryAnalyzer.class)
	public void checkApiOAuth2()
	{

		connector.OAuth2withPKCE(driver);
	}
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void checkApiOAuth1()
	{
		connector.OAuth1(driver);
	}
	@AfterMethod
	public void driverClose(ITestResult result,ITestContext testContext)
	{
		try {
			if(ITestResult.FAILURE==result.getStatus())
			{
			System.out.println(testContext.getName()+" "+result.getMethod().getMethodName());	
			new TakeScreenshots().takeScreenShot(testContext.getName()+"_"+result.getMethod().getMethodName(),"Connectors",driver);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
