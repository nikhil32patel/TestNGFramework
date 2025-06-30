package testCases;

import org.testng.annotations.Test;

import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/* Data is valid --> login success --> test pass and--logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid - login failed - test -pass
*/

public class TC003_LoginDDT extends BaseClass {

	/*
	 * IF DATA PROVIDER METHOD IS SAME CLASS THEN dataProviderClass NO NEED TO
	 * PROVIDE IT IS IN DIFFRENT CLASS THEN dataProviderClass NAME NEED TO PROVIDE
	 * 
	 */
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")
	public void veriyf_loginDDT(String email, String pwd, String exp) {

		logger.info("***** STARTING TC003_LoginDDT *****");
		try {

			// HOME PAGE OBJECT CREATION
			HomePage hpObj = new HomePage(driver);
			hpObj.clickMyAccount();
			hpObj.clickLogin();

			// LOGIN PAGE OBJECT CREATION
			LoginPage lpObj = new LoginPage(driver);
			lpObj.setEmail(email);
			lpObj.setPassword(pwd);
			lpObj.clickLogin();

			// MYACCOUNT PGE OBJECT CREATION
			MyAccountPage maccpObj = new MyAccountPage(driver);
			boolean targetPage = maccpObj.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					Assert.assertTrue(true);
					maccpObj.clickLogout();
				} else {
					Assert.assertTrue(false);

				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					maccpObj.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
			System.out.println("Error is:"+e.getLocalizedMessage());

		}
		logger.info("***** FINISHED TC003_LoginDDT *****");
	}

}
