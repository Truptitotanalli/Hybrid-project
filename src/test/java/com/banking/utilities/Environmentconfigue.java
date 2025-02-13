package com.banking.utilities;

public class Environmentconfigue {

	

	    // Define different environments and their corresponding URLs
	    private static final String test_URL = "https://demo.guru99.com/insurance/v1/index.php";
	    private static final String STAGING_URL = "https://staging.example.com";
	    private static final String PROD_URL = "https://prod.example.com";

	    // Method to get the URL based on the environment
	    public static String getBaseUrl(String environment) {
	        if (environment == null) {
	            throw new IllegalArgumentException("Environment cannot be null");
	        }

	        switch (environment.toLowerCase()) {
	            case "testing":
	                return test_URL;
	            case "staging":
	                return STAGING_URL;
	            case "prod":
	                return PROD_URL;
	            default:
	                throw new IllegalArgumentException("Invalid environment: " + environment);
	                // Alternatively, you can return TEST_URL as a fallback if you prefer
	        }
	    }
	}

	


