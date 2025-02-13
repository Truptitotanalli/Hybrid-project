package com.banking.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.banking.testcases.TC_loginmultipledata_002;


public class TestDataProvider {

	
	   @DataProvider(name = "logindata")
	    public Object[][] getData() throws Exception {
	        // Specify path to the Excel file
		   
		   final Logger logger = LogManager.getLogger(TC_loginmultipledata_002.class);
	        String xlpath = System.getProperty("user.dir") + "/src/test/java/com/banking/testdata/Book1.xlsx";
	        
	        // Log the file path and sheet name
	        logger.info("Reading data from Excel file at: " + xlpath);
	        
	        // Get the row count and column count
	        int rownum = XLUtils.getRowCount(xlpath, "sheet1");
	        int colnum = XLUtils.getColumnCount(xlpath, "sheet1", 0); // You can use row 0 to get column count

	        // Create a 2D array to store the data
	        String[][] data = new String[rownum - 1][colnum];  // Start from 1 to skip the header row

	        // Loop through the Excel data and populate the 2D array
	        for (int i = 1; i < rownum; i++) {  // Start from 1 if row 0 has headers
	            for (int j = 0; j < colnum; j++) {
	            	data[i - 1][j] = XLUtils.getCellData(xlpath, "sheet1", i, j);
	                logger.debug("Loaded data from Excel: " + data[i - 1][j]);
	            }
	        }

	        return data;
	    }
	}


