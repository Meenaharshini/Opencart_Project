package testCases;


import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	@Test(groups= {"Sanity","Master"})
	public void test_Login() {
		logger.info("******* Starting TC_002_LoginTest *********");
		try {
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount");
			
			hp.clickLogin();
			logger.info("Clicked on Login Link");
			
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(rb.getString("username")); 
			lp.setPassword(rb.getString("password"));
			
			logger.info("Provided Username & Password");

			lp.btnLogin();
			logger.info("Clicked on Login Button");
			
			MyAccountPage ap = new MyAccountPage(driver);
			Assert.assertEquals(true, ap.isMyAccountPageExists());
			
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("******* Finished LoginTest *********");

	}

}
