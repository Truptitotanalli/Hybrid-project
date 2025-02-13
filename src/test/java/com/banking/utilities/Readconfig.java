package com.banking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Readconfig {
	
	Properties pro; //(this is just a declaration not initialization, it is null by default its not explicitly initialized).
	//Properties pro=new Properties(); // here initialization and declaration is done with parameters
	public Readconfig(){
		File f=new File("./Configuration/config.properties");
		
		try {
			FileInputStream fs=new FileInputStream(f);
			pro=new Properties();
			pro.load(fs);
		}
			catch(Exception e)
			{
				System.out.println("Exception is" +e.getMessage());
			}
	}
		public String getAppUrl() {
		String url=pro.getProperty("url");
		return url;
		}
		public String getUsername() {
			String uname=pro.getProperty("uname");
		return uname;
			}
		
		public String getPassword() {
			String pass=pro.getProperty("pass");
			return pass;
			}
		public String getchromedriver() {
			String chromedriver=pro.getProperty("chromedriver");
			return chromedriver;
			}
		public String getgeckodriver() {
			String geckodriver=pro.getProperty("geckodriver");
			return geckodriver;
		}

}
