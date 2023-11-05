package Selenium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumTestCases {

    public static void main(String[] args) {
    	ExtentReports extent = new ExtentReports("extent-report.html", true);
    	
    	ExtentTest test_1 = extent.startTest("Login Test", "Test login to the online recharge portal");
        ExtentTest test_2 = extent.startTest("Add Operator Test", "Test adding an operator");
        ExtentTest test_3 = extent.startTest("Add Mobile Number Test", "Test adding a mobile number");
        ExtentTest test_4 = extent.startTest("Add Plan Test", "Test adding a plan");
        ExtentTest test_5 = extent.startTest("Recharge Test", "Test recharge operation");
        ExtentTest test_6 = extent.startTest("Updating User Account Test", "Test updating user account information");
        ExtentTest test_7 = extent.startTest("Change Password Test", "Test changing the user's password");
        ExtentTest test_8 = extent.startTest("Registration Test", "Test registration on the online recharge portal");
        WebDriver driver;
        // Set up your WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "C://Users//Hardik//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");
        driver = new ChromeDriver();
    //Test Case 1: Test login to the online recharge portal
        driver.get("http://localhost:8080/Portal");
        WebElement usernameField = driver.findElement(By.name("user"));
        WebElement passwordField = driver.findElement(By.name("pass"));
        WebElement loginButton = driver.findElement(By.tagName("button"));
        if(isDataAlreadyExists("Hardik123","user_details","username")) {
        // Enter the username and password
        	usernameField.sendKeys("Hardik123");
        	passwordField.sendKeys("Hardik557");
        	 test_1.log(LogStatus.PASS, "Login successful");
        	loginButton.click();
        // Find and click the login button
        }
        else {
        	test_1.log(LogStatus.FAIL, "Login failed");
            // Enter the username and password
            usernameField.sendKeys("Hardik123");
            passwordField.sendKeys("Hardik557");
            // Find and click the login button
            loginButton.click();
        }
        
        extent.endTest(test_1);
        
        
  //Test Case 2:Test adding an operator
        driver.get("http://localhost:8080/Portal/addoperator.jsp");
        // Perform verifications and assertions
        if (isDataAlreadyExists("OperatorName","operator_details","operator_name")) {
        	 // Failed to add the operator
            test_2.log(LogStatus.FAIL, "Failed to add the operator.");
        } else {
        	// The operator is added successfully
        	WebElement operatorNameField = driver.findElement(By.name("opname"));
        	WebElement companyNameField = driver.findElement(By.name("company"));
        	operatorNameField.sendKeys("BSNL");
        	companyNameField.sendKeys("BSNL INDIA");  
        	// Find and click the submit button
        	WebElement OpsubmitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        	OpsubmitButton.click();
            test_2.log(LogStatus.PASS, "Operator added successfully.");
        }
        
        extent.endTest(test_2);
        
   //Test Case 3:Test adding a mobile number
        driver.get("http://localhost:8080/Portal/mobileoperatorlist");
        // Perform verifications and assertions
        if (isDataAlreadyExists("8975633012","mobileno_details","mobile_no")) {
        	 // Failed to add the operator
            test_3.log(LogStatus.FAIL, "Failed to add the mobile number.");
        } else {
        	 // The operator is added successfully
            test_3.log(LogStatus.PASS, "Mobile number added successfully.");
        }
        WebElement mobmobileNumberField = driver.findElement(By.name("mobileno"));
        WebElement ownerNameField = driver.findElement(By.name("ownername"));
        WebElement moboperatorSelect = driver.findElement(By.name("operatorid"));
        WebElement mobsubmitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        // Input data into form fields
        mobmobileNumberField.sendKeys("8975633012");
        ownerNameField.sendKeys("Pratham Pohokar");
        Select selectOperator = new Select(moboperatorSelect);
        selectOperator.selectByVisibleText("BSNL");

        // Submit the form
        mobsubmitButton.click();
        // End the ExtentReports test
        extent.endTest(test_3);
        
    //Test Case 4: Test adding a plan
        driver.get("http://localhost:8080/Portal/planoperatorlist");
        // Verification and assertion
        if (isDataAlreadyExists("Daily_SMS_+_5GB","recharge_plan_details","plan_details")) {
            // Failed to add the plan
            test_4.log(LogStatus.FAIL, "Failed to add the plan.");
        } else {
        	// The plan is added successfully
            test_4.log(LogStatus.PASS, "Plan added successfully.");
        }
        WebElement planoperatorSelect = driver.findElement(By.name("operatorid"));
        WebElement planDetailsField = driver.findElement(By.name("plandetails"));
        WebElement planValidityField = driver.findElement(By.name("planvalidity"));
        WebElement priceField = driver.findElement(By.name("price"));
        WebElement plansubmitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        // Use the Select class to work with the dropdown
        Select operatorDropdown = new Select(planoperatorSelect);

        // Select by visible text (replace "Your Operator Name" with the actual text)
        operatorDropdown.selectByVisibleText("BSNL");

        // Input data into form fields
        planDetailsField.sendKeys("Daily_SMS_+_5GB");
        planValidityField.sendKeys("3_Months");
        priceField.sendKeys("100");

        // Find and click the submit button
        plansubmitButton.click();

        extent.endTest(test_4);
        
      //Test Case 5: Test recharge operation

        // Navigate to the web page
        driver.get("http://localhost:8080/Portal/rechargeoperatorlist");

        // Find and interact with web elements
        // Use the Select class to work with the dropdown
        WebElement rechoperatorSelect =  driver.findElement(By.id("operator"));
        rechoperatorSelect.findElement(By.xpath("//option[. = 'BSNL']")).click();
        driver.findElement(By.id("plan")).click();
        {
          WebElement element = driver.findElement(By.id("plan"));
          Actions builder = new Actions(driver);
          builder.doubleClick(element).perform();
        }
        WebElement planSelect = driver.findElement(By.id("plan"));
        planSelect.findElement(By.xpath("//option[. = 'Daily_SMS_+_5GB3_Months']")).click();

        // Input data into form fields
        driver.findElement(By.name("mobileno")).click();
        {
          WebElement element = driver.findElement(By.name("mobileno"));
          Actions builder = new Actions(driver);
          builder.doubleClick(element).perform();
        }
        driver.findElement(By.name("mobileno")).sendKeys("8975633012");
       	test_5.log(LogStatus.PASS, "Recharge successful.");
       	extent.endTest(test_5);
       // Submit the form
        WebElement rechargeButton = driver.findElement(By.xpath("//input[@type='submit']"));
        rechargeButton.click();
        
     //Test Case 6: Test recharge operation 
        driver.get("http://localhost:8080/Portal/myaccount.jsp");

        // Locate and interact with elements on the page
        driver.findElement(By.name("fullname")).click();
        driver.findElement(By.name("fullname")).sendKeys("Soham raje");
        driver.findElement(By.name("email")).sendKeys("Sohamraje@gmail.com");

        // Submit the form
        WebElement updateInfoButton = driver.findElement(By.xpath("//input[@value='Update Info']"));
        updateInfoButton.click();
        if (isDataAlreadyExists("Soham Raje","user_details","full_name")) {
            // Test passed
            test_6.log(LogStatus.PASS, "User account information updated successfully");
        } else {
            // Test failed
            test_6.log(LogStatus.FAIL, "Failed to update user account information");
        }
        extent.endTest(test_6);
        
      //Test Case 7:  Change Password Test
        driver.get("http://localhost:8080/Portal/myaccount.jsp");
        WebElement oldPasswordField = driver.findElement(By.name("oldpass"));
        oldPasswordField.sendKeys("Hardik557");
        if(isDataAlreadyExists("Hardik557","user_details","password")) {
        	  WebElement newPasswordField = driver.findElement(By.name("newpass"));
              WebElement changePasswordButton = driver.findElement(By.xpath("//input[@value='Change Password']"));
              newPasswordField.sendKeys("Hardik557");
              test_7.log(LogStatus.PASS, "Password changed successfully");
              // Submit the form to change the password
              changePasswordButton.click();
        }
        else {
        	 test_7.log(LogStatus.FAIL, "Failed to change the password");
        }
        extent.endTest(test_7);
        driver.get("http://localhost:8080/Portal/logout");
     //Test Case 8: Test registration on the online recharge portal
        driver.get("http://localhost:8080/Portal/register.jsp");
        	if(isDataAlreadyExists("Pohokar123", "user_details", "username")) {
        		test_8.log(LogStatus.FAIL, "Registration failed");
        	}
        	else {
        		// Locate and interact with the registration form fields
                WebElement regfullNameField = driver.findElement(By.name("fullname"));
                WebElement regemailField = driver.findElement(By.name("email"));
                WebElement regmobileNumberField = driver.findElement(By.name("mobileno"));
                WebElement regusernameField = driver.findElement(By.name("user"));
                WebElement regpasswordField = driver.findElement(By.name("pass"));
                WebElement registerButton = driver.findElement(By.cssSelector(".button"));

                // Fill in the registration details
                regfullNameField.sendKeys("Pratham Pohokar");
                regemailField.sendKeys("pratham.pohokar@gmail.com");
                regmobileNumberField.sendKeys("8390993895");
                regusernameField.sendKeys("Pohokar123");
                regpasswordField.sendKeys("Pohokar456");
                // Click the register button
                registerButton.click();
                test_8.log(LogStatus.PASS, "Registration successful");
			}
        	extent.endTest(test_8);

   // Clean up and close the WebDriver
        driver.quit();

        // Flush Extent Reports to save the report
        extent.flush();
    }
    private static boolean isDataAlreadyExists(String operatorNameField,String database,String fields) {
        // Database query to check if data exists
    		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinerechargeportal", "root", "root");
    				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + database+" WHERE "+fields+ " = ? ")) {
    				preparedStatement.setString(1, operatorNameField);
    				try (ResultSet resultSet = preparedStatement.executeQuery()) {
    						return resultSet.next(); // Data exists if the query returns a result
    					}
    				} catch (SQLException sqlException) {
    						sqlException.printStackTrace();
    					return false; // Error occurred, so assume data doesn't exist
    					}
    	}
}