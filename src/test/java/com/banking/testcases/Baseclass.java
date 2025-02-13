package com.banking.testcases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.banking.utilities.Environmentconfigue;
import com.banking.utilities.Readconfig;
import com.banking.utilities.Reporting;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Baseclass {

	 // This should be WebDriver, not something else
/*read data from readconfig file and properties files
    Readconfig Rd = new Readconfig();

    public String url = Rd.getAppUrl();
    public String uname = Rd.getUsername();
    public String pass = Rd.getPassword();
 

    // ExtentReports variables
    protected static ExtentReports extent;
    protected static ExtentTest test;
    
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String br) {
        // Initialize WebDriver based on the browser parameter
    	if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", Rd.getchromedriver());
            driver.set(new ChromeDriver());
        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", Rd.getgeckodriver());
            driver.set(new FirefoxDriver());
        }
        Reporting.setDriver(getDriver());
        // Initialize Logger
      
       
        // Launch the application URL
        getDriver().get(url);

        // Initialize ExtentReports
        initializeReport();
    }

    @AfterClass
    public void teardown() {
        // Quit WebDriver
    	 getDriver().quit();

        // Flush ExtentReports
        flushReport();
    }

    private void initializeReport() {
        String reportFilePath = "test-output/sparkreport.html";

        // Check and create the report file if it doesn't exist
        File reportFile = new File(reportFilePath);
        if (!reportFile.exists()) {
            try {
                boolean isCreated = reportFile.createNewFile();
                if (isCreated) {
                    System.out.println("Report file created: " + reportFilePath);
                } else {
                    System.out.println("Failed to create report file.");
                }
            } catch (IOException e) {
                System.out.println("Error while creating the report file: " + e.getMessage());
            }
        }

        // Set up the ExtentHtmlReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setReportName("Banking Application Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info to the report
        extent.setSystemInfo("Application", "Banking Application");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Tester");
    }

    
    // Cleanup method after each test method
    @AfterMethod
    public void cleanup() {
        File file = new File("C:/Users/trupt/eclipse-workspace/project1/Banking/src/test/java/com/banking/testdata/~$Book1.xlsx");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Temporary file deleted successfully.");
            } else {
                System.out.println("Failed to delete temporary file.");
            }
        }
    }
    
    private void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }*/
	
	//This code will read data from excel file , which is parallel test execution
    
   
        private static final Logger logger = LogManager.getLogger(Baseclass.class);
        
        protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        protected ExtentReports extent;
        protected ExtentTest test;
        private String environment;

        // Getter for WebDriver
        public WebDriver getDriver() {
            return driver.get();
        }

        @BeforeClass
        @Parameters({"browser", "environment"})
        public void setup(String br, String environment) {
            logger.info("Starting the setup...");
            
            // Fetch environment variable or use default
            if (environment == null || environment.isEmpty()) {
                this.environment = System.getenv("ENVIRONMENT");
                logger.info("Environment from System.getenv: " + this.environment);
                
                if (this.environment == null) {
                    this.environment = "testing";  // Default to "testing" if not set
                }
            } else {
                this.environment = environment;
            }
            
            logger.info("Using environment passed from TestNG: " + this.environment);

            // Initialize WebDriver based on browser parameter
            if (br.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
                driver.set(new ChromeDriver());
            } else if (br.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
                driver.set(new FirefoxDriver());
            }

            // Maximize the window, set timeouts, and navigate to the URL
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            getDriver().get("https://demo.guru99.com/insurance/v1/index.php");
            //getDriver().get("https://demo.guru99.com");
            // Initialize ExtentReports and Reporting class
            initializeReport();
            
            // Pass WebDriver instance to Reporting class for screenshot capture
            Reporting.setDriver(getDriver());
        }

        @AfterClass
        public void teardown() {
            logger.info("Closing the WebDriver and flushing reports...");

            // Quit WebDriver
            if (getDriver() != null) {
                getDriver().quit();
            }

            // Flush ExtentReports
            flushReport();

            // Delete the generated Excel file (if exists)
            deleteExcelFile();
        }

        private void initializeReport() {
            String reportFilePath = "test-output/ExtentReport-" + System.currentTimeMillis() + ".html";
            
            // Create the report file if it doesn't exist
            File reportFile = new File(reportFilePath);
            if (!reportFile.exists()) {
                try {
                    boolean isCreated = reportFile.createNewFile();
                    if (isCreated) {
                        logger.info("Report file created: " + reportFilePath);
                    } else {
                        logger.error("Failed to create report file.");
                    }
                } catch (IOException e) {
                    logger.error("Error while creating the report file: " + e.getMessage());
                }
            }

            // Set up the ExtentHtmlReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setReportName("Banking Application Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system info to the report
            extent.setSystemInfo("Application", "Banking Application");
            extent.setSystemInfo("Environment", this.environment);
            extent.setSystemInfo("User", "Tester");
        }

        private void flushReport() {
            if (extent != null) {
                extent.flush();
            }
        }

        // Handle screenshot capture on test failure
        @AfterMethod
        public void afterMethod(ITestResult result) {
            if (ITestResult.FAILURE == result.getStatus()) {
                Reporting reporting = new Reporting();
                reporting.onTestFailure(result);  // Trigger the screenshot capture on failure
            }
        }

        // Delete the generated Excel file after test execution
        private void deleteExcelFile() {
            // Define the file path for the Excel file generated during the test
            File file = new File("C:/Users/trupt/eclipse-workspace/project1/Banking/src/test/java/com/banking/testdata/~$Book1.xlsx");

            // Check if the file exists, and if so, delete it
            if (file.exists()) {
                if (file.delete()) {
                    logger.info("Temporary Excel file deleted successfully.");
                } else {
                    logger.error("Failed to delete temporary Excel file.");
                }
            } else {
                logger.info("No temporary Excel file found to delete.");
            }
        }
    }

	


