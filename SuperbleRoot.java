/**
 * 
 */
package com.vamsha.selenium.source;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.vamsha.selenium.tests.Superble;

/**
 * @author Vamsha Shetty
 *
 */
public class SuperbleRoot extends Superble{
	
	public static SoftAssert softAssert = new SoftAssert();
	
	public static void signUpScreenLogin() throws Exception{

		try{


			driver.findElement(By.xpath(".//*[@id='root']/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div/div/button")).click();
			String currWin= driver.getWindowHandle();

			Reporter.log("current window is"+currWin, true);


			driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div/div/div[2]")).click();

			Reporter.log("In login popup", true);


			driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div/div/div[2]/span")).click();

			driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div/div/div[2]/span/a[1]"));


			//login screen using FB creds

			Reporter.log("Shifting to new login window", true);



			for(String popWinLoginCreds:driver.getWindowHandles()){
				driver.switchTo().window(popWinLoginCreds);
			}

			Thread.sleep(2000);

			driver.findElement(By.xpath(".//*[@id='email']")).click();
			driver.findElement(By.xpath(".//*[@id='email']")).clear();
			driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("aristokittle@gmail.com");


			driver.findElement(By.xpath(".//*[@id='pass']")).click();
			driver.findElement(By.xpath(".//*[@id='pass']")).clear();
			driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("spassw0rd");

			driver.findElement(By.xpath(".//*[@id='u_0_0']")).click();
			driver.switchTo().window(currWin);
		}
		catch(Exception e){
			throw e;
		}
	}

	public static void search(){
		driver.get("https://staging.superble.com/search");
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div[1]/div/div[2]/div/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div[1]/div/div[2]/div/div[1]/input")).sendKeys("fnf");
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div[1]/div/div[2]/div/div[1]/input")).sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("html/body/div[4]/div/i")).click();
	}

	public static void productAnswer() throws Exception{
		
		

		clickOnViewMore();
		
		softAssert.assertEquals("What did I use it for?", driver.findElement(By.xpath(".//*[@id='root']/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]")).getText());
		driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div/div/div[2]/div[2]/button")).click();
		Thread.sleep(1000);
		Reporter.log("Logged in again with fb creds while answering the default question", true);
		driver.switchTo().parentFrame();
		
		
		String currUrl = driver.getCurrentUrl();
		driver.get(currUrl);
		
		clickOnViewMore();
		
		
		
		/*WebDriverWait wait = new WebDriverWait(driver, 250);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("viewMore0"))));
		//driver.findElement(By.id("viewMore0")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("viewMore0"))).click().build().perform();*/
		//driver.findElement(By.xpath("html/body/div[4]/div/i")).click();
		closePossibleRandomNotification();
		Thread.sleep(5000);
		driver.findElement(By.id("viewMore0")).click();
		closePossibleRandomNotification();
		driver.findElement(By.xpath("(//textarea[@name='chatMessage'])[2]")).clear();
		Reporter.log("Answering window", true);

		driver.findElement(By.xpath("(//textarea[@name='chatMessage'])[2]")).sendKeys("3rd");
		driver.findElement(By.xpath("//button[@type='button']")).click();
		Reporter.log("Added answer", true);
		driver.findElement(By.cssSelector("i.close.icon")).click();
	}

	public static void productAnswerEdit(){
		WebElement element = driver.findElement(By.xpath(".//*[@id='viewMore0']"));
		Reporter.log("Scrolling to webelement 'viewMore'",true);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)", element);
		Reporter.log("After JavascriptExecutor at view more",true);

		element.click();
		driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div/div[2]/div/div/div/div/div[2]/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div/i")).click();
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.xpath("//input[@value='3rd']")).clear();
		Reporter.log("Editing the answer", true);

		driver.findElement(By.xpath("//input[@value='3rd']")).sendKeys("3rdd");
	}

	public static void siteLogout(){
		driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div/div[2]/div/div/div/div/div[2]/div/div[3]/div/div/div[2]/div/div[2]/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div[3]/div")).click();
		driver.findElement(By.linkText("Log Out")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}
	
	public static void clickOnViewMore(){
		WebElement element = driver.findElement(By.xpath(".//*[@id='viewMore0']"));
		Reporter.log("Scrolling to webelement 'viewMore'",true);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)", element);
		Reporter.log("After JavascriptExecutor at view more",true);

		element.click();
	}
	
	public static void closePossibleRandomNotification()
    {
        boolean exists=false;
        try
        {
            driver.findElement(By.xpath("html/body/div[4]/div/div[1]/div/div/h4"));
            exists = true;
        }

        catch (ElementNotVisibleException e)
        {
            exists = false;
        }

        catch (NoSuchElementException e)
        {
            exists = false;
        }

        if (exists){
            try
            {
        		driver.findElement(By.xpath("html/body/div[4]/div/i")).click();
            }
            catch (ElementNotVisibleException e)
            {
                exists = false;
            }

        }
    }


}
