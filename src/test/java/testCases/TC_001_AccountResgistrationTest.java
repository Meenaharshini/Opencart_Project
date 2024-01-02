package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountResgistrationTest extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void test_account_Registration() throws InterruptedException {
		logger.info("Application logs");
		logger.info("***** Starting TC_001_AccountRegistrationTest *****");
		try {
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked Account page");
			
			hp.clickRegister();
			logger.info("Clicked Register page");
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			
			//regPage.setEmail("davidmark@gmail.com"); //static test case
			
			logger.info("Providing customer data");
			
			//Dynamic test cases
			regPage.setFirstName(randomString().toUpperCase());
			logger.info("Provided FirstName");
			
			regPage.setLastName(randomString().toUpperCase());
			logger.info("Provided LastName");

			regPage.setEmail(randomString()+"@gmail.com");
			logger.info("Provided email");
			
			String password = randomAlphaNumeric();
			regPage.setPassword(password);
			logger.info("Provided Password");
			
			regPage.setPrivacyPolicy();
			logger.info("Clicked privacy policy check box");
			
			regPage.clickContinue();
			logger.info("Clicked on continue");
			
			Thread.sleep(2000);
			
			//Assertions
			String confirm_Msg = regPage.getConfirmationMsg();
			logger.info("Validating expected message");			
			Assert.assertEquals(confirm_Msg, "Your Account Has Been Created!");
			
		}catch(Exception e) {
			logger.info("*Test is failed");
			Assert.fail();  //fails the test with no message.
		}
		logger.info("***** Finished TC_001_AccountRegistrationTest *****");

	}
}
