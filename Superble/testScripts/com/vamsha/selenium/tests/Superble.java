/**
 * 
 */
package com.vamsha.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vamsha.selenium.source.SuperbleRoot;

/**
 * @author Vamsha Shetty
 *
 */
public class Superble{


	public static WebDriver driver = new FirefoxDriver();
	public static String URL = "https://staging.superble.com";

	@BeforeMethod

	public static void login(){
		
		try{

		driver.get(URL);
		//driver.get("http://vanshashetty@yahoo.com:spassw0rd@staging.superble.com");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log("Launching superble on firefox", true);
		}
		catch(Exception e){
			throw e;
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{

		driver.close();
		if(result.getStatus()==ITestResult.FAILURE){
			File Scsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(Scsht, new File("D:\\SuperbleScreen.jpg"));

			Reporter.log("Test Complete, please refer to SuperbleScreen jpg in D drive for failed screenshots", true);
			
			
						
		}


	}

	@Test
	public void loginAndVerifyQ() throws Exception{

		SoftAssert softAssert = new SoftAssert();

		try{

			Thread.sleep(1000);
			SuperbleRoot.signUpScreenLogin();
			Thread.sleep(500);
			SuperbleRoot.search();
			SuperbleRoot.productAnswer();
			SuperbleRoot.productAnswerEdit();
			SuperbleRoot.siteLogout();

			softAssert.assertAll();
			
		}catch(Exception e){
			
			
			softAssert.fail();
			Reporter.log("Errors/exceptions found in testSuite loginAndVerifyQ", true);

			e.printStackTrace();
		}
			driver.close();



	}




}
