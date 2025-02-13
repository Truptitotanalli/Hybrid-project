package com.banking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reporting extends TestListenerAdapter {

    public static ExtentReports extent;
    public static ExtentTest test;
    private static WebDriver driver; // Ensure the WebDriver instance is passed or available

    @Override
    public void onStart(ITestContext testContext) {
    	
    
    	
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport-" + timeStamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        try {
            sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/Extent-configue.xml");
        } catch (IOException e) {
            System.err.println("Failed to load ExtentReports configuration: " + e.getMessage());
        }

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "User");

        System.out.println("ExtentReports initialized at: " + reportPath);
       
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.pass("Test Passed: " + tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.fail("Test Failed: " + tr.getName());
        test.fail(tr.getThrowable());

        // Capture and attach a screenshot
        try {
            String screenshotPath = captureScreenshot(tr.getName());
            test.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
        } catch (IOException e) {
            System.err.println("Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.skip("Test Skipped: " + tr.getName());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
        System.out.println("ExtentReports flushed and saved.");
    }

    private String captureScreenshot(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "-" + timeStamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);
        FileUtils.copyFile(src, dest);

        return screenshotPath;
    }

    // Setter for WebDriver (if needed)
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
