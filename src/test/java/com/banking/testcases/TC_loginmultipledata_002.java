package com.banking.testcases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.banking.pageobject.Loginmultipledata;
import com.banking.utilities.TestDataProvider;

public class TC_loginmultipledata_002 extends Baseclass {

    private static final Logger logger = LogManager.getLogger(TC_loginmultipledata_002.class);

    @Test(dataProvider = "logindata", dataProviderClass = TestDataProvider.class)
    public void Login(String uname, String pass) throws InterruptedException {
        // Log the login attempt with credentials
        logger.info("Attempting to log in with email: " + uname + " and Password: " + pass);

        // Create Loginmultipledata object and perform login
        Loginmultipledata login = new Loginmultipledata(getDriver());  // Use getDriver() to access the WebDriver instance

        login.entermail(uname);
        logger.debug("Entered username: " + uname);

        // Wait for actions or transitions
        Thread.sleep(5000);
        login.enterpass(pass);
        logger.debug("Entered password: " + pass);

        Thread.sleep(5000);  // Giving time for any possible actions or transitions
        login.clicklogin();
        logger.debug("Clicked the login button");

        // Check if alert appears
        if (isAlertpresent()) {
            logger.warn("Alert present after login attempt.");
            getDriver().switchTo().alert().accept();
            getDriver().switchTo().defaultContent();
            Assert.fail("Login failed: Alert appeared.");
        } else {
            logger.info("Login attempt was successful.");
            Assert.assertTrue(true, "Login successful.");
        }
    }

    public boolean isAlertpresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    
    // Now check for the login success or failure based on the page content or error message
    }
}
