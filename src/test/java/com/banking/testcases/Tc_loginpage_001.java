package com.banking.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.banking.pageobject.Loginpage;
import com.banking.utilities.Reporting;


//	@Listeners(com.banking.utilities.Reporter.class)
	public class Tc_loginpage_001 extends Baseclass {
	
		public static WebDriver driver;
		
		
	@Test
	void Loginscreen() throws InterruptedException {
		
		// Start logging for this test case in the Extent Report
        Reporting.test = Reporting.extent.createTest("Tc_loginpage_001: Loginscreen");
		
		Loginpage lp=new Loginpage(getDriver());
	
		
		  Reporting.test.info("Launched the Chrome browser and navigated to the URL");
	/*	
		lp.entermail(uname);
		Thread.sleep(5000);
		
		
		lp.enterpass(pass);
		Thread.sleep(5000);
		
		lp.clicklogin();
		Thread.sleep(5000);*/


		
		/*System.out.println(driver.getCurrentUrl());
		String currentTitle= driver.getTitle();
		logger.info("get the title");
		Reporting.test.info("Page title retrieved: " + currentTitle);
		if(currentTitle.equals("Insurance Broker System")){
			
			Assert.assertTrue(true,"same title");
			logger.info("title is same");
			 Reporting.test.pass("Title matches the expected value. Test Passed.");
			
		}
		else {
			boolean isnotLoggedIn = false;
			Assert.assertTrue(isnotLoggedIn,"not same title");
			logger.info("title is not same");
			 Reporting.test.fail("Title matches the expected value. Test Passed.");
		}*/
	}

}
