package com.banking.pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



	public class Loginmultipledata {
	    private static final Logger logger = LogManager.getLogger(Loginmultipledata.class);

		WebDriver Idriver;
		
		public Loginmultipledata(WebDriver rdriver){
		Idriver=rdriver;
		PageFactory.initElements(rdriver, this);
		}
		
		@FindBy(id="email")
		WebElement emailid;
		
		@FindBy(id="password")
		WebElement password1;
		
		@FindBy(name="submit")
		WebElement submitlogin;
		
		public void entermail(String uname) {
		 emailid.sendKeys(uname);
		}
			
		public void enterpass(String pass) {
			password1.sendKeys(pass);
		}
		
		public void clicklogin() {
			submitlogin.click();
		}
		
			
	}


