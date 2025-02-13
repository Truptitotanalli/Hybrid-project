package com.banking.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver Idriver;
	
	public Loginpage(WebDriver rdriver){
	Idriver=rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(name="submit")
	WebElement submitButton;
	
	public void entermail(String uname) {
		email.sendKeys(uname);
	}
		
	public void enterpass(String pass) {
		password.sendKeys(pass);
	}
	
	public void clicklogin() {
		submitButton.click();
	}
	
		
}
