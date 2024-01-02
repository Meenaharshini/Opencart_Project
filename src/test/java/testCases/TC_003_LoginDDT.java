package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void test_LoginDDT(String[] testData) {
		logger.info("******** Starting TC_003_LoginDDT ********");
		try {
			HomePage hp = new HomePage(driver);
			logger.info("Clicked MyAccount Link");
			hp.clickMyAccount();
			logger.info("Clicked Login Link");
			hp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(testData[0]);
			lp.setPassword(testData[1]);
			logger.info("Clicked Login Button");
			lp.btnLogin();
			
			MyAccountPage ap = new MyAccountPage(driver);
			boolean targetPage = ap.isMyAccountPageExists();
			//Assert.assertEquals(targetPage,true);
			
			//Positive case
			if(testData[2].equals("VALID")) {
				if(targetPage==true) {
					logger.info("Clicked Logout Button");
					ap.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			//Nesgative case
			if(testData[2].equals("INVALID")) {
				if(targetPage==true) {
					MyAccountPage ap1 = new MyAccountPage(driver);
					ap1.clickLogout();
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
			}
			
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("******** Finished LoginDataDrivenTest ********");

	}
}
