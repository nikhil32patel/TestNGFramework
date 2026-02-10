package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() {

		logger.info("***** STARTING TC_002_LoginTest *****");

		try {
			// HOME PAGE OBJECT CREATION
			HomePage hpObj = new HomePage(driver);
			hpObj.clickMyAccount();
			hpObj.clickLogin();

			// LOGIN PAGE OBJECT CREATION
			LoginPage lpObj = new LoginPage(driver);
			lpObj.setEmail(p.getProperty("loginId"));
			lpObj.setPassword(p.getProperty("password"));
			lpObj.clickLogin();

			// MYACCOUNT PGE OBJECT CREATION
			MyAccountPage maccpObj = new MyAccountPage(driver);
			boolean targetPage = maccpObj.isMyAccountPageExists();

			Assert.assertEquals(targetPage, true, "Login failed");
			// Assert.assertTrue(targetPage);
		} catch (Exception e) {
			logger.error("TC_002_LoginTest");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);

		}
		logger.info("***** FINISHED TC_002_LoginTest *****");
	}
}
