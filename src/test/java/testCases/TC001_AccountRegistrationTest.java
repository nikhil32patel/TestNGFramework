package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	void verify_account_registration() {

		logger.info("***** STARTING TC001_AccountRegistrationTest *****");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Clicked on MyAccount Link");

			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details");
			// regPage.setUserName("Jhon old xyz");
			regPage.setFirtName(randomString());
			regPage.setLastName(randomString());
			regPage.setEmail(randomString() + "@mail.com");
			regPage.setTelephone("4578415263");

			String pwd = randomString();
			regPage.setPassword(pwd);
			regPage.setConfirmPassword(pwd);
			regPage.setPrivacyPolidy();
			regPage.clickContinue();

			logger.info("Validatin expected message...");
			String confmsg = regPage.getConfirmationMsg();
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertEquals(confmsg, "Your Account Has Been Created!");
				Assert.assertTrue(true);
			} else {
				logger.error("TC001_AccountRegistrationTest Test Failed");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			logger.error("Test Faild");
			logger.info(e.getMessage().toString());
			logger.debug("PLEASE DEBUG THE LOGS");
			System.out.println("Erro message is:" + e.getLocalizedMessage());
			Assert.fail();
		}
		logger.info("***** FINISHED TC001_AccountRegistrationTest *****");
	}
}
